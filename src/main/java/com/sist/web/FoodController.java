package com.sist.web;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.service.*;
import com.sist.vo.*;

import lombok.RequiredArgsConstructor;

/*
 *   1. 전송 => 변수
 *   2. 커맨드 객체 => VO (회원가입, 회원수정, 글쓰기)
 *   3. 내장객체
 *   	1) HttpSession
 *   	2) Cookie => 저장 : response
 *                   읽기 : request
 */

@Controller
@RequiredArgsConstructor
public class FoodController {
	private final FoodService fService;
	
	@GetMapping("food/detail_before.do")
	public String food_detail_before(int no,HttpServletResponse response,
		RedirectAttributes ra) {
		// 쿠키 생성
		// 쿠키는 String 값만 저장할 수 있다
		Cookie cookie=new Cookie("food_"+no, String.valueOf(no));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		ra.addAttribute("no",no);
		return "redirect:../food/detail.do";
	}
	
	// 조회수 증가 / 쿠키 저장된 값 출력 ==> history.back() 사용금지
	// <form> => get/post
	// 나머지 태그는 get
	// ajax: get/post
	// axios: axios.get() / axios.post()
	@GetMapping("food/detail.do")
	public String food_detail(int no, Model model) {
		FoodVO vo=fService.foodDetailData(no);
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../food/detail.jsp");
		return "main/main";
	}
}
