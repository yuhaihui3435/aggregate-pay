package com.xtf.aggregatepay.entity;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.experimental.Tolerate;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2018-12-25
*/
@Table(name="aggregate_pay_pro_db.client_info_t")
@Builder
public class ClientInfo extends com.xtf.aggregatepay.core.BaseEntity  {
	
	private Integer id ;
	/*
	渠道ID
	*/
	private Integer channelId ;
	/*
	代理商（二级）ID
	*/
	private Integer channelSubId ;
	/*
	客户编号
	*/
	private String clientCode ;
	/*
	email
	*/
	private String clientEmail ;
	/*
	客户名称
	*/
	private String clientName ;
	/*
	联系电话
	*/
	private String clientTel ;
	/*
	0:可用，1：禁用
	*/
	private String status ;
	/*
	t1费率
	*/
	private BigDecimal t1Rate ;
	/*
	ts费率
	*/
	private BigDecimal tsRate ;
	/*
	删除时间
	*/
	private Date dAt ;
	@Tolerate
	public ClientInfo() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	/**
	* 渠道ID
	*@return 
	*/
	public Integer getChannelId(){
		return  channelId;
	}
	/**
	* 渠道ID
	*@param  channelId
	*/
	public void setChannelId(Integer channelId ){
		this.channelId = channelId;
	}
	
	/**
	* 代理商（二级）ID
	*@return 
	*/
	public Integer getChannelSubId(){
		return  channelSubId;
	}
	/**
	* 代理商（二级）ID
	*@param  channelSubId
	*/
	public void setChannelSubId(Integer channelSubId ){
		this.channelSubId = channelSubId;
	}
	
	/**
	* 客户编号
	*@return 
	*/
	public String getClientCode(){
		return  clientCode;
	}
	/**
	* 客户编号
	*@param  clientCode
	*/
	public void setClientCode(String clientCode ){
		this.clientCode = clientCode;
	}
	
	/**
	* email
	*@return 
	*/
	public String getClientEmail(){
		return  clientEmail;
	}
	/**
	* email
	*@param  clientEmail
	*/
	public void setClientEmail(String clientEmail ){
		this.clientEmail = clientEmail;
	}
	
	/**
	* 客户名称
	*@return 
	*/
	public String getClientName(){
		return  clientName;
	}
	/**
	* 客户名称
	*@param  clientName
	*/
	public void setClientName(String clientName ){
		this.clientName = clientName;
	}
	
	/**
	* 联系电话
	*@return 
	*/
	public String getClientTel(){
		return  clientTel;
	}
	/**
	* 联系电话
	*@param  clientTel
	*/
	public void setClientTel(String clientTel ){
		this.clientTel = clientTel;
	}
	
	/**
	* 0:可用，1：禁用
	*@return 
	*/
	public String getStatus(){
		return  status;
	}
	/**
	* 0:可用，1：禁用
	*@param  status
	*/
	public void setStatus(String status ){
		this.status = status;
	}
	
	/**
	* t1费率
	*@return 
	*/
	public BigDecimal getT1Rate(){
		return  t1Rate;
	}
	/**
	* t1费率
	*@param  t1Rate
	*/
	public void setT1Rate(BigDecimal t1Rate ){
		this.t1Rate = t1Rate;
	}
	
	/**
	* ts费率
	*@return 
	*/
	public BigDecimal getTsRate(){
		return  tsRate;
	}
	/**
	* ts费率
	*@param  tsRate
	*/
	public void setTsRate(BigDecimal tsRate ){
		this.tsRate = tsRate;
	}
	
	/**
	* 删除时间
	*@return 
	*/
	public Date getdAt(){
		return  dAt;
	}
	/**
	* 删除时间
	*@param  dAt
	*/
	public void setdAt(Date dAt ){
		this.dAt = dAt;
	}
	

}
