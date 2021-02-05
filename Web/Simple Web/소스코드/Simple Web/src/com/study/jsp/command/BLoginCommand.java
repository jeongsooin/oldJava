package com.study.jsp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.member.MemberDAO;
import com.study.jsp.member.MemberDTO;

public class BLoginCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		MemberDAO dao = MemberDAO.getInstance();
		
		int checkNum = dao.userCheck(id, pw);
		
		if(checkNum == -1) {
			session.setAttribute("status", null);
		} else if(checkNum == 0)  {
			session.setAttribute("status", null);
		} else if(checkNum == 1) {
			MemberDTO dto = dao.getMember(id);					
			if(dto == null) {
				session.setAttribute("status", null);
			} else {
				String name = dto.getName();
				if(name.equals("Admin"))
					session.setAttribute("IsAdmin", "true");
				else
					session.setAttribute("IsAdmin", null);
				session.setAttribute("status", "login");
				session.setAttribute("type", "native");
				session.setAttribute("name", name);
				session.setAttribute("id", id);
				System.out.println("Session Validated");
			}
		}
	}
}
