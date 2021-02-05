package com.study.jsp.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.study.jsp.dao.BDao;
import com.study.jsp.dto.FDto;

public class BFileContentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fId = request.getParameter("fId");
		String fKind = request.getParameter("kind");
		BDao dao = new BDao();
		FDto fdto = dao.fileContentView(fId, fKind);
		String writer = "Noname";
		request.setAttribute("fcontent_view", fdto);
		request.setAttribute("writer", writer);
	}
}