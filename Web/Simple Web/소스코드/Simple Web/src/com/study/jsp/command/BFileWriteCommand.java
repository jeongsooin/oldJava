package com.study.jsp.command;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.study.jsp.dao.BDao;
import com.study.jsp.member.MemberDAO;
import com.study.jsp.member.MemberDTO;

public class BFileWriteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		String fName = (String)request.getSession().getAttribute("name");
		String fTitle = request.getParameter("fTitle");
		String fContent = request.getParameter("fContent");
		String f_name = request.getParameter("f_name");
		String realFolder = "";		
		String saveFolder = "upload";
		String encType = "UTF-8";
		int maxSize = 1024 * 1024 * 10; 
		
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
			Enumeration en = multi.getParameterNames();
			while(en.hasMoreElements()) {
				String name = (String)en.nextElement();
				String value = multi.getParameter(name);
				System.out.println(name + " : " + value);				
			}
			
			en = multi.getFileNames();
			while(en.hasMoreElements()) {
				String name = (String)en.nextElement();
				String originalFile = multi.getOriginalFileName(name);
				String systemFile = multi.getFilesystemName(name);
				String fileType = multi.getContentType(name);
				f_name = systemFile;
				File file = multi.getFile(name);
				System.out.println("파일 이름 : " + name);
				System.out.println("원래 이름 : " + originalFile);
				System.out.println("시스템 파일 : " + systemFile);
				System.out.println("파일 형식 : " + fileType);
				
				if(file != null) {
					System.out.println("파일 크기 : " + file.length() + " byte");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BDao dao = BDao.getInstance();
		dao.writeFile(fName, fTitle, fContent, f_name);
	}
}
