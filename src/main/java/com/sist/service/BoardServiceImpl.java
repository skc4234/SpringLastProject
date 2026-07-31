package com.sist.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	/*
	// 1. 상위 데이터 읽기
	@Select("SELECT group_id,group_step,group_tab "
			+ "FROM springreplyboard "
			+ "WHERE no=#{no}")
	public BoardVO boardParentInfoData(int no);
	
	// 2. Update group_step
	@Update("UPDATE springReplyBoard SET "
			 +"group_step=group_step+1 "
			 +"WHERE group_id=#{group_id} AND group_step>#{group_step}")
	public void boardStepIncrement(@Param("group_id") int group_id,
		@Param("group_step") int group_step);
	
	// 3. Insert 답변
	@Insert("INSERT INTO springReplyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab,root,depth) "
			 +"VALUES(srb_no_seq.nextval,#{name},#{subject},"
			 +"#{content},#{pwd},"
			 +"#{group_id},#{group_step},#{group_tab},#{root},#{depth})")
	public void boardReplyInsert(BoardVO vo);
	
	// 4. Update depth
	@Update("UPDATE springReplyBoard SET "
			 +"depth=depth+1 "
			 +"WHERE no=#{no}")
	public void boardDepthIncrement(int no);
	 */
	
	@Override
	@Transactional
	public void boardReplyInsert(int pno, BoardVO vo) {
		// TODO Auto-generated method stub
		BoardVO pvo=mapper.boardParentInfoData(pno);
		mapper.boardStepIncrement(pvo.getGroup_id(), pvo.getGroup_step());
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(pno);
		vo.setDepth(0);
		mapper.boardReplyInsert(vo);
		mapper.boardDepthIncrement(pno);
	}

	/*               no     gi    gs    gt   root    depth
	 *   AAA         1      1     0     0     0        2
	 *    ㄴBBB       2      1     1     1     1        0
	 *    ㄴCCC       3      1     2     1     1        1
	 *      ㄴDDD     4      1     3     2     3        0
	 *   KKK         5      2     0     0     0        0
	 */
	
	@Override
	@Transactional
	public boolean boardDelete(int no, String pwd) {
		// TODO Auto-generated method stub
		boolean bCheck=false;
		BoardVO vo=mapper.boardInfoData(no);
		String db_pwd=mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck=true;
			if(vo.getDepth()==0) { // 답변이 없는 상태
				mapper.boardDelete(no);
				
			}
			else { // 답변이 있는 상태
				BoardVO bvo=new BoardVO();
				bvo.setNo(no);
				bvo.setSubject("관리자가 삭제한 게시물입니다.");
				bvo.setContent("삭제된 게시물입니다...");
				mapper.boardMsgUpdate(bvo);
			}
			mapper.boardDepthDecrement(vo.getRoot());
		}
			
		return bCheck;
	}

}
