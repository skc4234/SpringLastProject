package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface BoardMapper {
	@Select("SELECT no,name,subject,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,hit "
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
	
	
	// 3. Insert 답변
	
	// 4. Update depth
	
	// 수정
	// 삭제 ==> 트랜잭션
}
