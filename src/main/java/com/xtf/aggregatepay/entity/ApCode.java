package com.xtf.aggregatepay.entity;

import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2018-09-09
*/
@Table(name="AP_CODE_T")
public class ApCode extends com.xtf.aggregatepay.core.BaseEntity  {
	
	private String apCode ;
	private String apKey ;
	private String channelCode ;
	
	public ApCode() {
	}
	
	public String getApCode(){
		return  apCode;
	}
	public void setApCode(String apCode ){
		this.apCode = apCode;
	}
	
	public String getApKey(){
		return  apKey;
	}
	public void setApKey(String apKey ){
		this.apKey = apKey;
	}
	
	public String getChannelCode(){
		return  channelCode;
	}
	public void setChannelCode(String channelCode ){
		this.channelCode = channelCode;
	}
	

}
