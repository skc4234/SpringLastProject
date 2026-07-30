package com.sist.web;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.vo.*;

import lombok.RequiredArgsConstructor;

import com.sist.service.*;

@Controller
@RequiredArgsConstructor
public class GoodsController {
	private final GoodsService gService;
	
	@GetMapping("goods/list.do")
	public String goods_list(String page,Model model,HttpServletRequest request) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int start=(curpage*12)-12;
		List<GoodsVO> list=gService.goodsListData(start);
		int totalpage=gService.goodsTotalPage();
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		
		List<GoodsVO> cList=new ArrayList<GoodsVO>();
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("goods_")) {
					GoodsVO vo=gService.goodsDetailData(Integer.parseInt(cookies[i].getValue()));
					cList.add(vo);
				}
			}
		}
		
		model.addAttribute("cList",cList);
		model.addAttribute("size",cList.size());
		
		model.addAttribute("main_jsp","../goods/list.jsp");
		return "main/main";
	}
	
	@GetMapping("goods/detail_before.do")
	public String goods_detail_before(int no,HttpServletRequest request, HttpServletResponse response,RedirectAttributes ra) {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("goods_"+no)) {
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					break;
				}
			}
		}
		Cookie cookie=new Cookie("goods_"+no,String.valueOf(no));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		ra.addAttribute("no",no);
		return "redirect:../goods/detail.do";
	}
	
	@GetMapping("goods/detail.do")
	public String goods_detail(int no,Model model) {
		GoodsVO vo=gService.goodsDetailData(no);
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../goods/detail.jsp");
		return "main/main";
	}
	
	@GetMapping("goods/cookie_delete.do")
	public String goods_cookie_delete(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				if(cookie.getName().startsWith("goods_")) {
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				
			}
		}
		return "redirect:../goods/list.do";
	}
}
