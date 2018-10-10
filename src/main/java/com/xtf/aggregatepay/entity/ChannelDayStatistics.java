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
* gen by beetlsql 2018-10-08
*/
@Data
@Builder
@Table(name="CHANNEL_DAY_STATISTICS_T")
public class ChannelDayStatistics extends com.xtf.aggregatepay.core.BaseEntity  {
	
	private Integer id ;
	/*
	交易笔数
	*/
	private Integer tradeNum ;
	/*
	渠道编号
	*/
	private String channelCode ;
	/*
	利润
	*/
	private BigDecimal profit ;
	/*
	结算方式
	*/
	private String settlyType ;
	/*
	交易金额
	*/
	private BigDecimal tradeAmount ;
	/*
	统计日期
	*/
	private Date statisticsDay ;
	@Tolerate
	public ChannelDayStatistics() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	/**
	* 交易笔数
	*@return 
	*/
	public Integer getTradeNum(){
		return  tradeNum;
	}
	/**
	* 交易笔数
	*@param  tradeNum
	*/
	public void setTradeNum(Integer tradeNum ){
		this.tradeNum = tradeNum;
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
	* 利润
	*@return 
	*/
	public BigDecimal getProfit(){
		return  profit;
	}
	/**
	* 利润
	*@param  profit
	*/
	public void setProfit(BigDecimal profit ){
		this.profit = profit;
	}
	
	/**
	* 结算方式
	*@return 
	*/
	public String getSettlyType(){
		return  settlyType;
	}
	/**
	* 结算方式
	*@param  settlyType
	*/
	public void setSettlyType(String settlyType ){
		this.settlyType = settlyType;
	}
	
	/**
	* 交易金额
	*@return 
	*/
	public BigDecimal getTradeAmount(){
		return  tradeAmount;
	}
	/**
	* 交易金额
	*@param  tradeAmount
	*/
	public void setTradeAmount(BigDecimal tradeAmount ){
		this.tradeAmount = tradeAmount;
	}
	
	/**
	* 统计日期
	*@return 
	*/
	public Date getStatisticsDay(){
		return  statisticsDay;
	}
	/**
	* 统计日期
	*@param  statisticsDay
	*/
	public void setStatisticsDay(Date statisticsDay ){
		this.statisticsDay = statisticsDay;
	}
	

}
