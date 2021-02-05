package com.study.jsp.command;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

public class BModifyInfoCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String)request.getSession().getAttribute("name");
		BDao dao = BDao.getInstance();
		ArrayList<BDto> dtos = dao.list(1, "Name", name);
		request.setAttribute("list", dtos);	
	}

}
