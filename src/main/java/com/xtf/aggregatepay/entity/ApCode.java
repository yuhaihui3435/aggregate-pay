package com.xtf.aggregatepay.entity;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2018-09-04
*/
@Table(name="aggregate_pay_db.AP_CODE_T")
public class ApCode extends com.xtf.aggregatepay.core.BaseEntity  {
	
	private String apCode ;
	private String apKey ;
	private String mercInfo ;
	
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
	
	public String getMercInfo(){
		return  mercInfo;
	}
	public void setMercInfo(String mercInfo ){
		this.mercInfo = mercInfo;
	}
	

}
