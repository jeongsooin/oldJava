package com.study.jsp.frontcontroller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.BBoardWriteNoticeCommand;
import com.study.jsp.command.BCommand;
import com.study.jsp.command.BContentCommand;
import com.study.jsp.command.BDeleteCommand;
import com.study.jsp.command.BFileContentCommand;
import com.study.jsp.command.BFileWriteCommand;
import com.study.jsp.command.BLibraryListCommand;
import com.study.jsp.command.BListCommand;
import com.study.jsp.command.BLoginCommand;
import com.study.jsp.command.BMemberDeleteCommand;
import com.study.jsp.command.BModifyCommand;
import com.study.jsp.command.BModifyInfoCommand;
import com.study.jsp.command.BNoticeListCommand;
import com.study.jsp.command.BReplyCommand;
import com.study.jsp.command.BReplyViewCommand;
import com.study.jsp.command.BSetSessionCommand;
import com.study.jsp.command.BWriteCommand;
import com.study.jsp.command.BWriteNoticeCommand;

@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		actoinDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actoinDo(request, response);
	}

	private void actoinDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		BCommand command = null;
		
		String uri = request.getRequestURI();	
		String conPath = request.getContextPath();	
		String com = uri.substring(conPath.length());
		
		HttpSession session = null;
		session = request.getSession();
		int curPage = 1;
		if (session.getAttribute("cpage") != null)
			curPage = (int)session.getAttribute("cpage");
	
		if(com.equals("/write_view.do")) {	
	 		viewPage = "write_view.jsp"; 			
		} else if (com.equals("/write.do")) {
			command = new BWriteCommand();
			command.execute(request, response);
			viewPage = "list.do";
		} else if (com.equals("/list.do")) {
			command = new BListCommand();
			command.execute(request, response);
			viewPage = "list.jsp";
		} else if (com.equals("/content_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "content_view.jsp";
		} else if (com.equals("/file_content_view.do")) {
			command = new BFileContentCommand();
			command.execute(request, response);
			viewPage = "file_content_view.jsp";
		} else if (com.equals("/modify_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "modify_view.jsp";
		} else if (com.equals("/modify.do")) {
			command = new BModifyCommand();
			command.execute(request, response);
			viewPage = "list.do?page="+curPage;
		} else if (com.equals("/delete.do")) {
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "list.do?page="+curPage;
		} else if (com.equals("/reply_view.do")) {
			command = new BReplyViewCommand();
			command.execute(request, response);
			viewPage = "reply_view.jsp";
		} else if (com.equals("/reply.do")) {
			command = new BReplyCommand();
			command.execute(request, response);
			viewPage = "list.do?page="+curPage;
		} else if (com.equals("/login.do")) {
			command = new BLoginCommand();
			command.execute(request, response);
			viewPage = "main.jsp";
		} else if (com.equals("/setSession.do")) {
			System.out.println("setSession");
			command = new BSetSessionCommand();
			command.execute(request, response);
			viewPage = "main.jsp";
		} else if (com.equals("/memberdelete.do")) {
			command = new BMemberDeleteCommand();
			command.execute(request, response);
			viewPage = "cover.jsp";
		} else if (com.equals("/noticelist.do")) {
			command = new BNoticeListCommand();
			command.execute(request, response);
			viewPage = "noticelist.jsp";
		} else if (com.equals("/libraryList.do")) {
			command = new BLibraryListCommand();
			command.execute(request, response);
			viewPage = "libraryList.jsp";
		} else if (com.equals("/writeNotice.do")) {
			command = new BWriteNoticeCommand();
			command.execute(request, response);
			viewPage = "noticelist.do";
		} else if (com.equals("/writeBoardNotice.do")) {
			command = new BBoardWriteNoticeCommand();
			command.execute(request, response);
			viewPage = "list.do";
		} else if (com.equals("/fileUpload.do")) {
			command = new BFileWriteCommand();
			command.execute(request, response);
			viewPage = "libraryList.do";
		} else if (com.equals("/modifyinfo.do")) {
			command = new BModifyInfoCommand();
			command.execute(request, response);
			viewPage = "modify.jsp";
		}		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
