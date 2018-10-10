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
* gen by beetlsql 2018-10-07
*/
@Table(name="CHANNEL_BROKERAGE_T")
@Builder
@Data
public class ChannelBrokerage extends com.xtf.aggregatepay.core.BaseEntity  {
	
	/*
	ID
	*/
	private Integer id ;
	/*
	乐观锁
	*/
	private Integer revision ;
	/*
	银行支付号
	*/
	private String bankPayOrder ;
	/*
	佣金金额
	*/
	private String brokerageAmount ;
	/*
	佣金日期
	*/
	private String brokerageDay ;
	/*
	渠道编号
	*/
	private String channelCode ;
	/*
	创建人
	*/
	private String createdBy ;
	/*
	删除人
	*/
	private String deleteBy ;
	/*
	银行支付凭证图片
	*/
	private String payPic ;
	/*
	支付时间
	*/
	private String payTime ;
	/*
	结算金额
	*/
	private String settleAmount ;
	/*
	结算时间
	*/
	private String settleTime ;
	/*
	更新人
	*/
	private String updatedBy ;
	/*
	创建时间
	*/
	private Date createdTime ;
	/*
	删除时间
	*/
	private Date deleteTime ;
	/*
	更新时间
	*/
	private Date updatedTime ;
	@Tolerate
	public ChannelBrokerage() {
	}
	
	/**
	* ID
	*@return 
	*/
	public Integer getId(){
		return  id;
	}
	/**
	* ID
	*@param  id
	*/
	public void setId(Integer id ){
		this.id = id;
	}
	
	/**
	* 乐观锁
	*@return 
	*/
	public Integer getRevision(){
		return  revision;
	}
	/**
	* 乐观锁
	*@param  revision
	*/
	public void setRevision(Integer revision ){
		this.revision = revision;
	}
	
	/**
	* 银行支付号
	*@return 
	*/
	public String getBankPayOrder(){
		return  bankPayOrder;
	}
	/**
	* 银行支付号
	*@param  bankPayOrder
	*/
	public void setBankPayOrder(String bankPayOrder ){
		this.bankPayOrder = bankPayOrder;
	}
	
	/**
	* 佣金金额
	*@return 
	*/
	public String getBrokerageAmount(){
		return  brokerageAmount;
	}
	/**
	* 佣金金额
	*@param  brokerageAmount
	*/
	public void setBrokerageAmount(String brokerageAmount ){
		this.brokerageAmount = brokerageAmount;
	}
	
	/**
	* 佣金日期
	*@return 
	*/
	public String getBrokerageDay(){
		return  brokerageDay;
	}
	/**
	* 佣金日期
	*@param  brokerageDay
	*/
	public void setBrokerageDay(String brokerageDay ){
		this.brokerageDay = brokerageDay;
	}
	
	/**
	* 渠道编号
	*@return 
	*/
	public String getChannelCode(){
		return  channelCode;
	}
	/**
	* 渠道编号
	*@param  channelCode
	*/
	public void setChannelCode(String channelCode ){
		this.channelCode = channelCode;
	}
	
	/**
	* 创建人
	*@return 
	*/
	public String getCreatedBy(){
		return  createdBy;
	}
	/**
	* 创建人
	*@param  createdBy
	*/
	public void setCreatedBy(String createdBy ){
		this.createdBy = createdBy;
	}
	
	/**
	* 删除人
	*@return 
	*/
	public String getDeleteBy(){
		return  deleteBy;
	}
	/**
	* 删除人
	*@param  deleteBy
	*/
	public void setDeleteBy(String deleteBy ){
		this.deleteBy = deleteBy;
	}
	
	/**
	* 银行支付凭证图片
	*@return 
	*/
	public String getPayPic(){
		return  payPic;
	}
	/**
	* 银行支付凭证图片
	*@param  payPic
	*/
	public void setPayPic(String payPic ){
		this.payPic = payPic;
	}
	
	/**
	* 支付时间
	*@return 
	*/
	public String getPayTime(){
		return  payTime;
	}
	/**
	* 支付时间
	*@param  payTime
	*/
	public void setPayTime(String payTime ){
		this.payTime = payTime;
	}
	
	/**
	* 结算金额
	*@return 
	*/
	public String getSettleAmount(){
		return  settleAmount;
	}
	/**
	* 结算金额
	*@param  settleAmount
	*/
	public void setSettleAmount(String settleAmount ){
		this.settleAmount = settleAmount;
	}
	
	/**
	* 结算时间
	*@return 
	*/
	public String getSettleTime(){
		return  settleTime;
	}
	/**
	* 结算时间
	*@param  settleTime
	*/
	public void setSettleTime(String settleTime ){
		this.settleTime = settleTime;
	}
	
	/**
	* 更新人
	*@return 
	*/
	public String getUpdatedBy(){
		return  updatedBy;
	}
	/**
	* 更新人
	*@param  updatedBy
	*/
	public void setUpdatedBy(String updatedBy ){
		this.updatedBy = updatedBy;
	}
	
	/**
	* 创建时间
	*@return 
	*/
	public Date getCreatedTime(){
		return  createdTime;
	}
	/**
	* 创建时间
	*@param  createdTime
	*/
	public void setCreatedTime(Date createdTime ){
		this.createdTime = createdTime;
	}
	
	/**
	* 删除时间
	*@return 
	*/
	public Date getDeleteTime(){
		return  deleteTime;
	}
	/**
	* 删除时间
	*@param  deleteTime
	*/
	public void setDeleteTime(Date deleteTime ){
		this.deleteTime = deleteTime;
	}
	
	/**
	* 更新时间
	*@return 
	*/
	public Date getUpdatedTime(){
		return  updatedTime;
	}
	/**
	* 更新时间
	*@param  updatedTime
	*/
	public void setUpdatedTime(Date updatedTime ){
		this.updatedTime = updatedTime;
	}
	

}
