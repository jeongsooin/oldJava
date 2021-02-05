package com.study.jsp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.BDao;
import com.study.jsp.member.MemberDAO;
import com.study.jsp.member.MemberDTO;

public class BBoardWriteNoticeCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		HttpSession session = request.getSession();			
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		String type = (String)session.getAttribute("type");
		String bName = null;
		int bNumber = 0;
		if(type.equals("native")) {
			MemberDAO dao = MemberDAO.getInstance();
			MemberDTO dto = dao.getMember(id);
			bName = dto.getName();
		} else
			bName = name;
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		BDao bdao = BDao.getInstance();
		bdao.writeNotice(bName, bTitle, bContent, bNumber);
	}

}
