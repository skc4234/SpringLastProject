package com.sist.service;

import org.springframework.stereotype.Service;

import com.sist.vo.MemberVO;
import com.sist.mapper.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberMapper mapper;
	@Override
	public void memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub
		mapper.memberInsert(vo);
	}

}
