package com.sist.vo;
/*
NO         NOT NULL NUMBER         
NAME       NOT NULL VARCHAR2(51)   
SUBJECT    NOT NULL VARCHAR2(2000) 
CONTENT    NOT NULL CLOB           
PWD        NOT NULL VARCHAR2(10)   
REGDATE             DATE           
HIT                 NUMBER         
GROUP_ID            NUMBER         
GROUP_STEP          NUMBER         
GROUP_TAB           NUMBER         
ROOT                NUMBER         
DEPTH               NUMBER 
                   desc    asc
                 최상위 그룹  순서   들여쓰기  해당질문    답변개수
             no     gi     gs    gt     root    depth
    AAAA      1      1      0     0       0       2
    => EEEE   5      1      1     1       1       0
    => BBBB   2      1      2     1       1       2
      => DDD  4      1      3     2       2       0   최신순
      => CCC  3      1      4     2       2       0
    
    
    gi desc and gs asc
    답변 추가될때 root보다 같거나 큰 행들은 gs++, 해당 root_id depth++ 
    
 */
import java.util.*;
import lombok.Data;

@Data
public class BoardVO {
	private int no,hit,group_id,group_step,group_tab,root,depth;
	private String name,subject,content,pwd,dbday;
	private Date regdate;
}
