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
* gen by beetlsql 2018-12-25
*/
@Table(name="aggregate_pay_pro_db.trade_settle")
@Builder
@Data
public class TradeSettle extends com.xtf.aggregatepay.core.BaseEntity  {
	
	/*
	ID
	*/
	private Integer id ;
	/*
	渠道ID
	*/
	private Integer channelid ;
	/*
	代理商ID
	*/
	private Integer channelsubid ;
	/*
	客户ID
	*/
	private Integer clientid ;
	/*
	客户结算金额
	*/
	private BigDecimal carryamount ;
	/*
	代理商结算金额
	*/
	private BigDecimal carrysubamount ;
	/*
	备注
	*/
	private String remark ;
	/*
	结算时客户的费率（%）
	*/
	private BigDecimal settleclientrate ;
	/*
	结算时代理商的费率（%）
	*/
	private BigDecimal settlesubrate ;
	/*
	商户交易成本
	*/
	private BigDecimal tradebaseamount ;
	/*
	交易编号
	*/
	private String tradeno ;
	/*
	交易金额
	*/
	private BigDecimal tradesettleamount ;
	/*
	创建时间
	*/
	private Date cat ;
	/*
	删除时间
	*/
	private Date dat ;
	/*
	更新时间
	*/
	private Date mat ;
	@Tolerate
	public TradeSettle() {
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
	* 渠道ID
	*@return 
	*/
	public Integer getChannelid(){
		return  channelid;
	}
	/**
	* 渠道ID
	*@param  channelid
	*/
	public void setChannelid(Integer channelid ){
		this.channelid = channelid;
	}
	
	/**
	* 代理商ID
	*@return 
	*/
	public Integer getChannelsubid(){
		return  channelsubid;
	}
	/**
	* 代理商ID
	*@param  channelsubid
	*/
	public void setChannelsubid(Integer channelsubid ){
		this.channelsubid = channelsubid;
	}
	
	/**
	* 客户ID
	*@return 
	*/
	public Integer getClientid(){
		return  clientid;
	}
	/**
	* 客户ID
	*@param  clientid
	*/
	public void setClientid(Integer clientid ){
		this.clientid = clientid;
	}
	
	/**
	* 客户结算金额
	*@return 
	*/
	public BigDecimal getCarryamount(){
		return  carryamount;
	}
	/**
	* 客户结算金额
	*@param  carryamount
	*/
	public void setCarryamount(BigDecimal carryamount ){
		this.carryamount = carryamount;
	}
	
	/**
	* 代理商结算金额
	*@return 
	*/
	public BigDecimal getCarrysubamount(){
		return  carrysubamount;
	}
	/**
	* 代理商结算金额
	*@param  carrysubamount
	*/
	public void setCarrysubamount(BigDecimal carrysubamount ){
		this.carrysubamount = carrysubamount;
	}
	
	/**
	* 备注
	*@return 
	*/
	public String getRemark(){
		return  remark;
	}
	/**
	* 备注
	*@param  remark
	*/
	public void setRemark(String remark ){
		this.remark = remark;
	}
	
	/**
	* 结算时客户的费率（%）
	*@return 
	*/
	public BigDecimal getSettleclientrate(){
		return  settleclientrate;
	}
	/**
	* 结算时客户的费率（%）
	*@param  settleclientrate
	*/
	public void setSettleclientrate(BigDecimal settleclientrate ){
		this.settleclientrate = settleclientrate;
	}
	
	/**
	* 结算时代理商的费率（%）
	*@return 
	*/
	public BigDecimal getSettlesubrate(){
		return  settlesubrate;
	}
	/**
	* 结算时代理商的费率（%）
	*@param  settlesubrate
	*/
	public void setSettlesubrate(BigDecimal settlesubrate ){
		this.settlesubrate = settlesubrate;
	}
	
	/**
	* 商户交易成本
	*@return 
	*/
	public BigDecimal getTradebaseamount(){
		return  tradebaseamount;
	}
	/**
	* 商户交易成本
	*@param  tradebaseamount
	*/
	public void setTradebaseamount(BigDecimal tradebaseamount ){
		this.tradebaseamount = tradebaseamount;
	}
	
	/**
	* 交易编号
	*@return 
	*/
	public String getTradeno(){
		return  tradeno;
	}
	/**
	* 交易编号
	*@param  tradeno
	*/
	public void setTradeno(String tradeno ){
		this.tradeno = tradeno;
	}
	
	/**
	* 交易金额
	*@return 
	*/
	public BigDecimal getTradesettleamount(){
		return  tradesettleamount;
	}
	/**
	* 交易金额
	*@param  tradesettleamount
	*/
	public void setTradesettleamount(BigDecimal tradesettleamount ){
		this.tradesettleamount = tradesettleamount;
	}
	
	/**
	* 创建时间
	*@return 
	*/
	public Date getCat(){
		return  cat;
	}
	/**
	* 创建时间
	*@param  cat
	*/
	public void setCat(Date cat ){
		this.cat = cat;
	}
	
	/**
	* 删除时间
	*@return 
	*/
	public Date getDat(){
		return  dat;
	}
	/**
	* 删除时间
	*@param  dat
	*/
	public void setDat(Date dat ){
		this.dat = dat;
	}
	
	/**
	* 更新时间
	*@return 
	*/
	public Date getMat(){
		return  mat;
	}
	/**
	* 更新时间
	*@param  mat
	*/
	public void setMat(Date mat ){
		this.mat = mat;
	}
	

}
