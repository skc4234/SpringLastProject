package com.sist.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.vo.*;
import com.sist.service.*;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService mService;
		
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("member/join.do")
	public String member_join(Model model) {
		model.addAttribute("main_jsp","../member/join.jsp");
		return "main/main";
	}
	
	@PostMapping("member/join_ok.do")
	public String member_join_ok(MemberVO vo) {
		String enPwd=encoder.encode(vo.getUserpwd());
		System.out.println("enPwd: "+enPwd);
		vo.setUserpwd(enPwd);
		// DB 연동
		mService.memberInsert(vo);
		return "redirect:../main/main.do";
	}
	
}
