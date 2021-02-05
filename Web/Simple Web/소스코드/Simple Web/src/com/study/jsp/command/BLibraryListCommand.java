package com.study.jsp.command;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.study.jsp.dao.BDao;
import com.study.jsp.dto.FDto;

public class BLibraryListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nPage = 1;
		String option = "";
		String word = "";
		try {
			String sPage = request.getParameter("page");
			option = request.getParameter("option");
			word = request.getParameter("word");
			nPage = Integer.parseInt(sPage);
		} catch (Exception e){ 	
		}
		
		BDao dao = BDao.getInstance();
		BPageInfo pinfo = dao.libraryPage(nPage);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = request.getSession();
		if( session.getAttribute("status") != null ) {
			session.setAttribute("login", true);
		}
		else {
			session.setAttribute("login", false);
		}
		session.setAttribute("cpage", nPage);
		Date today = new Date();
		Timestamp now = new Timestamp(today.getTime());
		long now_long = now.getTime();
		ArrayList<FDto> dtos = dao.libraryList(nPage, option, word);
		request.setAttribute("list", dtos);	
		request.setAttribute("now", now_long);
	}

}
