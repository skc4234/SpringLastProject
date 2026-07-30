package com.sist.web;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.vo.*;

import lombok.RequiredArgsConstructor;

import com.sist.service.*;

@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService bService;
	
	@GetMapping("board/list.do")
	public String board_list(String page,Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int start=(curpage*10)-10;
		List<BoardVO> list=bService.boardListData(start);
		int count=bService.boardRowCount();
		
		
		model.addAttribute("main_jsp","../board/list.jsp");
		return "main/main";
	}
}
