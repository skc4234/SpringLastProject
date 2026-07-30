package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.sist.service.*;
import com.sist.vo.*;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final FoodService fService;
	
	@GetMapping("main/main.do")
	public String main_main(String page,Model model,HttpServletRequest request) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		final int ROWSIZE=12;
		int start=(curpage*ROWSIZE)-(ROWSIZE-1);
		int end=ROWSIZE*curpage;
		List<FoodVO> list=fService.foodListData(start, end);
		int totalpage=fService.foodTotalPage();
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		
		List<FoodVO> cList=new ArrayList<FoodVO>();
		
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			// 최신순
			for(int i=cookies.length-1; i>=0; i--) {
				if(cookies[i].getName().startsWith("food_")) {
					if(cookies[i].getName().equals("food_null")) {
						continue;
					}
					FoodVO vo=fService.foodDetailData(Integer.parseInt(cookies[i].getValue()));
					cList.add(vo);
				}
			}	
		}
		
		model.addAttribute("cList",cList);
		model.addAttribute("size",cList.size());
		
		/*
		 *   내장 객체 사용처
		 *   - request
		 *   - response
		 *   ==> Cookie, fileupload 등
		 *   
		 *   - session
		 *   ==> 로그인 정보, 보안처리
		 *   
		 *   - RedirectAttributes
		 *   ==> : 리다이렉트 시 파라미터 추가
		 */
		
		model.addAttribute("main_jsp","../main/home.jsp");
		return "main/main";
	}
	
}
