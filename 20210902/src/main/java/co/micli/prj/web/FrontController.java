package co.micli.prj.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micli.prj.command.Charts;
import co.micli.prj.command.HomeCommand;
import co.micli.prj.command.LoginForm;
import co.micli.prj.command.TableCommand;
import co.micli.prj.common.Command;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
       
    
    public FrontController() {
        super();
        
    }

	
	public void init(ServletConfig config) throws ServletException {
		map.put("/home.do", new HomeCommand()); //홈
		map.put("/tables.do", new TableCommand()); //테이블 보여주기
		map.put("/charts.do", new Charts());  //차트보여주기
		map.put("/loginForm.do", new LoginForm());  //로그인
		
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		String viewPage = null;
		
		Command command = map.get(page);
		viewPage = command.execute(request, response);
		
		if(!viewPage.endsWith(".do")) {
			viewPage = viewPage + ".tiles";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
