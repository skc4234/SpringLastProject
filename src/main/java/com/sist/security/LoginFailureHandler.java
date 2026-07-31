package com.sist.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String errorMsg="";
		try {
			if(exception instanceof BadCredentialsException) {
				// ID / PWD가 틀렸을때 처리
			}
			else if(exception instanceof DisabledException) {
				// 휴면 계정 처리
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
