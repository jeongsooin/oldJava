package com.study.springboot.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.study.springboot.dao.ISimpleBbsDao;
import com.study.springboot.dto.MemberDto;

@Configuration
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	@Autowired
	ISimpleBbsDao dao;
	
	private HttpSession session;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String id = request.getParameter("j_username");
		String pw = request.getParameter("j_password");
		String errormsg = "";
		
		if(exception instanceof BadCredentialsException) {
			loginFailureCount(id);
			errormsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			loginFailureCount(id);
			errormsg="아이디나 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
		} else if (exception instanceof DisabledException) {
			errormsg="계정이 비활성화되었습니다. 관리자에게 문의하세요.";
		} else if (exception instanceof CredentialsExpiredException) {
			errormsg = "비밀번호 유효기간이 만료되었습니다. 관리자에게 문의하세요.";
		}
		
//		MemberDto dto = dao.getMember(id);
//		String name = dto.getName();

		request.setAttribute("username", id);
		request.setAttribute("password", pw);
		request.setAttribute("error_message", errormsg);
		
		session = request.getSession();
		session.setAttribute("username", id);
		session.setAttribute("ValidMem", "yes");
		
		request.getRequestDispatcher("/loginForm?error=true").forward(request, response);	

	}
	
//		
	
	//비밀번호를 3번 이상 틀릴 시 계정 잠금 처리
	protected void loginFailureCount(String username) {
		
		/*
		 * // 틀린 횟수 업데이트 userDao.countFailure(username);
		 * 
		 * //틀린 횟수 조회 int cnt = userDao.checkFailureCount(username);
		 * 
		 * if(cnt==3) { //계정 잠금 처리 userDao.disabledUsername(username); }
		 */
	}


}
