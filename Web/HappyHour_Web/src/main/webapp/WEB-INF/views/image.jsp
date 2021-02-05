<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.io.IOException" %>
<%@ page import="org.springframework.util.ResourceUtils" %>
<%@ page import="java.io.File" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="org.springframework.web.multipart.MultipartHttpServletRequest" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.*" %>
<%@ page import="org.springframework.web.multipart.MultipartFile" %>

<%
/*String path = ResourceUtils.getFile("classpath:static/upload/").toPath().toString();
System.out.println(path);


int sizeLimit = 5 * 1024 * 1024 ; // 5메가까지 제한 넘어서면 예외발생
MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, new DefaultFileRenamePolicy());
Enumeration files = multi.getFileNames();*/

try {
	// 서버의 물리적경로 가져오기
	String path = ResourceUtils.getFile("classpath:static/upload/").toPath().toString();

	System.out.println(path);
    /*
	 * 파일업로드 위한 MultipartHttpServletRequest객체 생성
	 * 객체 생성과 동시에 파일업로드 완료됨. 
	 * 나머지 폼값은 Multipart가 통째로 받아서 처리한다.
	 */
	MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
	
	// 업로드폼의 file속성 필드의 이름을 모두 읽음
	Iterator<String> itr = mhsr.getFileNames();
	
	MultipartFile mfile = null;			
	String fileName = "";		
	
	// 파일 하나의 정보를 저장하기 위한 List타입의 변수(원본파일명, 저장된파일명 등)
	List resultList = new ArrayList();
				
	// 폼값받기 : 제목
	String title = mhsr.getParameter("title");
		
	// 업로드폼의 file속성의 필드의 갯수만큼 반복
	while (itr.hasNext()) {
		
		// userfile1, userfile2....출력됨
		fileName = (String)itr.next();
		//System.out.println(fileName);	
		
		// 서버로 업로드된 임시파일명 가져옴
		mfile = mhsr.getFile(fileName);
		//System.out.println(mfile);//CommonsMultipartFile@1366c0b 형태임
		
		// 한글깨짐방지 처리 후 업로드된 파일명을 가져온다.
		String originalName = 
			mfile.getOriginalFilename();
//		    new String(mfile.getOriginalFilename().getBytes(),"UTF-8"); // Linux
		System.out.println("upload:"+originalName);
		
		// 파일명이 공백이라면 while문의 처음으로 돌아간다.
		if("".equals(originalName)){
			continue;
		}
		//System.out.println("originalName:"+originalName);

		// 파일명에서 확장자 가져오기
//		String ext = originalName.substring(originalName.lastIndexOf('.'));
		
		// 파일명을 UUID로 생성된값으로 변경함.
//		String saveFileName = getUuid() + ext;
		String saveFileName = originalName;
		
		// 설정한 경로에 파일저장
		File serverFullName = new File(path + File.separator + saveFileName);
		
		// 업로드한 파일을 지정한 파일에 저장한다.
		mfile.transferTo(serverFullName);
		
		Map file = new HashMap();
		file.put("originalName", originalName);    //원본파일명
		file.put("saveFileName", saveFileName);    //저장된파일명
		file.put("serverFullName", serverFullName);//서버에 저장된 전체경로 및 파일명
		file.put("title", title);                  //타이틀
		
		// 위 파일의 정보를 담은 Map을 List에 저장
		resultList.add(file);
	}
	
	} catch(Exception e){
		
	}

		

%>