package com.xtf.aggregatepay.entity;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2018-09-04
*/
@Table(name="aggregate_pay_db.MSG_LOG_T")
public class MsgLog extends com.xtf.aggregatepay.core.BaseEntity  {
	
	/*
	ID
	*/
	private Integer id ;
	/*
	乐观锁
	*/
	private Integer revision ;
	/*
	接口类型-addMerchant:进件,scan:扫码交易,queryMerStatus:商户状态查询
	*/
	private String apiType ;
	/*
	创建人
	*/
	private String createdBy ;
	/*
	删除人
	*/
	private String deleteBy ;
	/*
	下游请求报文
	*/
	private String downReqJson ;
	/*
	下游响应报文
	*/
	private String downRespJson ;
	/*
	下游响应结果吗
	*/
	private String downResCode ;
	/*
	下游响应消息
	*/
	private String downResMsg ;
	/*
	商户编号
	*/
	private String mercCode ;
	/*
	订单编号
	*/
	private String tradeCode ;
	/*
	更新人
	*/
	private String updatedBy ;
	/*
	上游请求报文
	*/
	private String upReqJson ;
	/*
	上游响应报文
	*/
	private String upRespJson ;
	/*
	上游响应结果码
	*/
	private String upResCode ;
	/*
	上游响应码消息
	*/
	private String upResMsg ;
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
	
	public MsgLog() {
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
	* 接口类型-addMerchant:进件,scan:扫码交易,queryMerStatus:商户状态查询
	*@return 
	*/
	public String getApiType(){
		return  apiType;
	}
	/**
	* 接口类型-addMerchant:进件,scan:扫码交易,queryMerStatus:商户状态查询
	*@param  apiType
	*/
	public void setApiType(String apiType ){
		this.apiType = apiType;
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
	* 下游请求报文
	*@return 
	*/
	public String getDownReqJson(){
		return  downReqJson;
	}
	/**
	* 下游请求报文
	*@param  downReqJson
	*/
	public void setDownReqJson(String downReqJson ){
		this.downReqJson = downReqJson;
	}
	
	/**
	* 下游响应报文
	*@return 
	*/
	public String getDownRespJson(){
		return  downRespJson;
	}
	/**
	* 下游响应报文
	*@param  downRespJson
	*/
	public void setDownRespJson(String downRespJson ){
		this.downRespJson = downRespJson;
	}
	
	/**
	* 下游响应结果吗
	*@return 
	*/
	public String getDownResCode(){
		return  downResCode;
	}
	/**
	* 下游响应结果吗
	*@param  downResCode
	*/
	public void setDownResCode(String downResCode ){
		this.downResCode = downResCode;
	}
	
	/**
	* 下游响应消息
	*@return 
	*/
	public String getDownResMsg(){
		return  downResMsg;
	}
	/**
	* 下游响应消息
	*@param  downResMsg
	*/
	public void setDownResMsg(String downResMsg ){
		this.downResMsg = downResMsg;
	}
	
	/**
	* 商户编号
	*@return 
	*/
	public String getMercCode(){
		return  mercCode;
	}
	/**
	* 商户编号
	*@param  mercCode
	*/
	public void setMercCode(String mercCode ){
		this.mercCode = mercCode;
	}
	
	/**
	* 订单编号
	*@return 
	*/
	public String getTradeCode(){
		return  tradeCode;
	}
	/**
	* 订单编号
	*@param  tradeCode
	*/
	public void setTradeCode(String tradeCode ){
		this.tradeCode = tradeCode;
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
	* 上游请求报文
	*@return 
	*/
	public String getUpReqJson(){
		return  upReqJson;
	}
	/**
	* 上游请求报文
	*@param  upReqJson
	*/
	public void setUpReqJson(String upReqJson ){
		this.upReqJson = upReqJson;
	}
	
	/**
	* 上游响应报文
	*@return 
	*/
	public String getUpRespJson(){
		return  upRespJson;
	}
	/**
	* 上游响应报文
	*@param  upRespJson
	*/
	public void setUpRespJson(String upRespJson ){
		this.upRespJson = upRespJson;
	}
	
	/**
	* 上游响应结果码
	*@return 
	*/
	public String getUpResCode(){
		return  upResCode;
	}
	/**
	* 上游响应结果码
	*@param  upResCode
	*/
	public void setUpResCode(String upResCode ){
		this.upResCode = upResCode;
	}
	
	/**
	* 上游响应码消息
	*@return 
	*/
	public String getUpResMsg(){
		return  upResMsg;
	}
	/**
	* 上游响应码消息
	*@param  upResMsg
	*/
	public void setUpResMsg(String upResMsg ){
		this.upResMsg = upResMsg;
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
