package com.sist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.vo.*;
import com.sist.mapper.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardMapper mapper;
	
	@Override
	public List<BoardVO> boardListData(int start) {
		// TODO Auto-generated method stub
		return mapper.boardListData(start);
	}

	@Override
	public int boardRowCount() {
		// TODO Auto-generated method stub
		return mapper.boardRowCount();
	}

	@Override
	public void boardInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		mapper.boardInsert(vo);
	}

	@Override
	public BoardVO boardDetailData(int no) {
		// TODO Auto-generated method stub
		mapper.boardHitIncrement(no);
		return mapper.boardDetailData(no);
	}

}
