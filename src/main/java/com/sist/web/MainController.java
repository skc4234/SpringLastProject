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
			for(int i=cookies.length-1; i>=0; i--) {
				if(cookies[i].getName().startsWith("food_")) {
					if(cookies[i].getName().equals("food_null")) {
						continue;
					}
					//FoodVO vo=fService.foodDetailData(Integer.parseInt(cookies[i].getValue()));
					FoodVO vo=fService.foodCookieData(Integer.parseInt(cookies[i].getValue()));
					cList.add(vo);
				}
			}	
		}
		
		model.addAttribute("cList",cList);
		model.addAttribute("size",cList.size());
		
		//List<FoodVO> fList=fService.foodHit7Data();
		//model.addAttribute("fList",fList);
		
		model.addAttribute("main_jsp","../main/home.jsp");
		return "main/main";
	}
	
}
