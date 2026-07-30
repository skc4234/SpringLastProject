package com.sist.vo;

import lombok.Data;
/*
 NO                                        NOT NULL NUMBER
 CNO                                                NUMBER
 NAME                                      NOT NULL VARCHAR2(100)
 TYPE                                      NOT NULL VARCHAR2(200)
 PHONE                                     NOT NULL VARCHAR2(20)
 ADDRESS                                   NOT NULL VARCHAR2(500)
 PRICE                                              VARCHAR2(30)
 SCORE                                              NUMBER(2,1)
 THEME                                     NOT NULL CLOB
 TIME                                               VARCHAR2(100)
 RESERVE                                            VARCHAR2(100)
 PARKING                                   NOT NULL VARCHAR2(200)
 CONTENT                                   NOT NULL CLOB
 POSTER                                    NOT NULL VARCHAR2(260)
 */

@Data
public class FoodVO {
	private int no,cno;
	private double score;
	private String name,address,phone,type,price,theme,time,reserve,parking,content,poster;
}	
