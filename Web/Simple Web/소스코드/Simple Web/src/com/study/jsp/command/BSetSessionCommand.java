package com.study.jsp.command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BSetSessionCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		session.setAttribute("status", "login");
		session.setAttribute("type", type);
		session.setAttribute("name", name);
		session.setAttribute("id", id);
		System.out.println("Session Validated");
	}
}
