package com.sist.service;

import org.springframework.stereotype.Service;
import com.sist.vo.*;
import java.util.*;
import com.sist.mapper.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {
	private final GoodsMapper mapper;
	
	@Override
	public List<GoodsVO> goodsListData(int start) {
		// TODO Auto-generated method stub
		return mapper.goodsListData(start);
	}

	@Override
	public int goodsTotalPage() {
		// TODO Auto-generated method stub
		return mapper.goodsTotalPage();
	}

	@Override
	public GoodsVO goodsDetailData(int no) {
		// TODO Auto-generated method stub
		return mapper.goodsDetailData(no);
	}

}
