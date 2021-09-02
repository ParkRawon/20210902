package co.micli.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micli.prj.common.Command;

public class TableCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 테스트페이지돌려주기
		return "home/tables";
	}

}
