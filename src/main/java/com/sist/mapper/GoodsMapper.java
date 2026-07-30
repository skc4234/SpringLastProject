package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
/*
NO                                        NOT NULL NUMBER
GOODS_NAME                                NOT NULL VARCHAR2(1000)
GOODS_SUB                                          VARCHAR2(1000)
GOODS_PRICE                               NOT NULL VARCHAR2(50)
GOODS_DISCOUNT                                     NUMBER
GOODS_FIRST_PRICE                                  VARCHAR2(20)
GOODS_DELIVERY                            NOT NULL VARCHAR2(20)
GOODS_POSTER                                       VARCHAR2(260)
*/
public interface GoodsMapper {
	@Select("SELECT no,goods_name,goods_price,goods_poster "
			+ "FROM goods_all "
			+ "ORDER BY no ASC "
			+ "OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY")
	public List<GoodsVO> goodsListData(int start);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all")
	public int goodsTotalPage();
	
	@Select("SELECT no,goods_name,goods_sub,goods_price,goods_discount,goods_first_price,"
			+ "goods_delivery,goods_poster "
			+ "FROM goods_all "
			+ "WHERE no=#{no}")
	public GoodsVO goodsDetailData(int no);
}
