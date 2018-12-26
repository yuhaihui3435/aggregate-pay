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
@Table(name="aggregate_pay_pro_db.channel_sub_info")
@Builder
public class ChannelSubInfo extends com.xtf.aggregatepay.core.BaseEntity  {
	
	private Integer id ;
	/*
	渠道（一级代理商）id
	*/
	private Integer channelid ;
	/*
	代理商编号
	*/
	private String channelsubcode ;
	/*
	email
	*/
	private String channelsubemail ;
	/*
	客户名称
	*/
	private String channelsubname ;
	/*
	联系电话
	*/
	private String channelsubtel ;
	/*
	0:可用，1：禁用
	*/
	private String status ;
	/*
	t1费率
	*/
	private BigDecimal t1rate ;
	/*
	t1费率编号
	*/
	private String t1ratecode ;
	/*
	ts费率
	*/
	private BigDecimal tsrate ;
	/*
	ts费率编号
	*/
	private String tsratecode ;
	/*
	创建时间
	*/
	private Date cAt ;
	/*
	删除时间
	*/
	private Date dAt ;
	@Tolerate
	public ChannelSubInfo() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	/**
	* 渠道（一级代理商）id
	*@return 
	*/
	public Integer getChannelid(){
		return  channelid;
	}
	/**
	* 渠道（一级代理商）id
	*@param  channelid
	*/
	public void setChannelid(Integer channelid ){
		this.channelid = channelid;
	}
	
	/**
	* 代理商编号
	*@return 
	*/
	public String getChannelsubcode(){
		return  channelsubcode;
	}
	/**
	* 代理商编号
	*@param  channelsubcode
	*/
	public void setChannelsubcode(String channelsubcode ){
		this.channelsubcode = channelsubcode;
	}
	
	/**
	* email
	*@return 
	*/
	public String getChannelsubemail(){
		return  channelsubemail;
	}
	/**
	* email
	*@param  channelsubemail
	*/
	public void setChannelsubemail(String channelsubemail ){
		this.channelsubemail = channelsubemail;
	}
	
	/**
	* 客户名称
	*@return 
	*/
	public String getChannelsubname(){
		return  channelsubname;
	}
	/**
	* 客户名称
	*@param  channelsubname
	*/
	public void setChannelsubname(String channelsubname ){
		this.channelsubname = channelsubname;
	}
	
	/**
	* 联系电话
	*@return 
	*/
	public String getChannelsubtel(){
		return  channelsubtel;
	}
	/**
	* 联系电话
	*@param  channelsubtel
	*/
	public void setChannelsubtel(String channelsubtel ){
		this.channelsubtel = channelsubtel;
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
	public BigDecimal getT1rate(){
		return  t1rate;
	}
	/**
	* t1费率
	*@param  t1rate
	*/
	public void setT1rate(BigDecimal t1rate ){
		this.t1rate = t1rate;
	}
	
	/**
	* t1费率编号
	*@return 
	*/
	public String getT1ratecode(){
		return  t1ratecode;
	}
	/**
	* t1费率编号
	*@param  t1ratecode
	*/
	public void setT1ratecode(String t1ratecode ){
		this.t1ratecode = t1ratecode;
	}
	
	/**
	* ts费率
	*@return 
	*/
	public BigDecimal getTsrate(){
		return  tsrate;
	}
	/**
	* ts费率
	*@param  tsrate
	*/
	public void setTsrate(BigDecimal tsrate ){
		this.tsrate = tsrate;
	}
	
	/**
	* ts费率编号
	*@return 
	*/
	public String getTsratecode(){
		return  tsratecode;
	}
	/**
	* ts费率编号
	*@param  tsratecode
	*/
	public void setTsratecode(String tsratecode ){
		this.tsratecode = tsratecode;
	}
	
	/**
	* 创建时间
	*@return 
	*/
	public Date getcAt(){
		return  cAt;
	}
	/**
	* 创建时间
	*@param  cAt
	*/
	public void setcAt(Date cAt ){
		this.cAt = cAt;
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
