package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface BoardMapper {
	@Select("SELECT no,name,subject,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,hit,group_tab "
			+ "FROM springreplyboard "
			+ "ORDER BY group_id DESC, group_step ASC "
			+ "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<BoardVO> boardListData(int start);
	
	@Select("SELECT COUNT(*) FROM springreplyboard")
	public int boardRowCount();
	
	@Insert("INSERT INTO springreplyboard(no,name,subject,content,pwd,group_id) "
			+ "VALUES (srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},"
			+ "(SELECT NVL(MAX(group_id)+1,1) FROM springreplyboard))")
	public void boardInsert(BoardVO vo);
	
	// 상세보기
	@Update("UPDATE springreplyboard SET hit=hit+1 WHERE no=#{no}")
	public void boardHitIncrement(int no);
	
	@Select("SELECT no,name,subject,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,hit,content "
			+ "FROM springreplyboard "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	// 트랜잭션 ==> SELECT+INSERT 단일문장 X / INSERT,UPDATE,DELETE 여러문장
	// 답변 ==> 트랜잭션
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
	
	// 수정
	// 삭제 ==> 트랜잭션
	// 1. 정보 읽기
	@Select("SELECT root,depth FROM springreplyboard "
			+ "WHERE no=#{no}")
	public BoardVO boardInfoData(int no);
	
	// 2. 비밀번호 체크
	@Select("SELECT pwd FROM springreplyboard "
			+ "WHERE no=#{no}")
	public String boardGetPassword(int no);
	
	// 3. 결과
	// depth > 0 이면 답변이 있음
	// 답변이 있는 경우 => 게시물 제목만 변경
	@Update("UPDATE springreplyboard SET subject=#{subject},content=#{content} "
			+ "WHERE no=#{no}")
	public void boardMsgUpdate(BoardVO vo);
	
	// 답변이 없는 경우 => 게시물 삭제
	@Delete("DELETE FROM springreplyboard WHERE no=#{no}")
	public void boardDelete(int no);
	
	// 4. 상위 게시물 depth 변경
	@Update("UPDATE springreplyboard SET depth=depth-1 "
			+ "WHERE no=#{no}")
	public void boardDepthDecrement(int no);
}
