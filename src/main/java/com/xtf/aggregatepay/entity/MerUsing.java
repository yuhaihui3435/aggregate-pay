package com.xtf.aggregatepay.entity;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2018-10-13
*/
@Table(name="MER_USING_T")
@Data
@Builder
public class MerUsing extends com.xtf.aggregatepay.core.BaseEntity  {
	
	private Integer id ;
	private String merNo ;
	private String orderNo ;
	private Date useTime ;
	@Tolerate
	public MerUsing() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public String getMerNo(){
		return  merNo;
	}
	public void setMerNo(String merNo ){
		this.merNo = merNo;
	}
	
	public String getOrderNo(){
		return  orderNo;
	}
	public void setOrderNo(String orderNo ){
		this.orderNo = orderNo;
	}
	
	public Date getUseTime(){
		return  useTime;
	}
	public void setUseTime(Date useTime ){
		this.useTime = useTime;
	}
	

}
