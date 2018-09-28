package com.xtf.aggregatepay.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;


/* 
* 
* gen by beetlsql 2018-09-04
*/
@Table(name="MER_PIC_T")
public class MerPic extends com.xtf.aggregatepay.core.BaseEntity  {
	
	/*
	ID
	*/
	private Integer id ;
	/*
	商户ID
	*/
	private Integer merId ;
	/*
	图片类型-0:营业执照,1:手持身份证,2:银行卡图片,3:身份证正面,4:身份证背面,5:非法人结算授权书,6:商户协议照片,7:门头照,8:组织机构代码照
	*/
	private Integer picType ;
	/*
	乐观锁
	*/
	private Integer revision ;
	/*
	创建人
	*/
	private String createdBy ;
	/*
	删除人
	*/
	private String deleteBy ;
	/*
	通道端返回的图片ID
	*/
	private String picId ;
	/*
	图片路径
	*/
	private String picPath ;
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
	
	public MerPic() {
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
	* 商户ID
	*@return 
	*/
	public Integer getMerId(){
		return  merId;
	}
	/**
	* 商户ID
	*@param  merId
	*/
	public void setMerId(Integer merId ){
		this.merId = merId;
	}
	
	/**
	* 图片类型-0:营业执照,1:手持身份证,2:银行卡图片,3:身份证正面,4:身份证背面,5:非法人结算授权书,6:商户协议照片,7:门头照,8:组织机构代码照
	*@return 
	*/
	public Integer getPicType(){
		return  picType;
	}
	/**
	* 图片类型-0:营业执照,1:手持身份证,2:银行卡图片,3:身份证正面,4:身份证背面,5:非法人结算授权书,6:商户协议照片,7:门头照,8:组织机构代码照
	*@param  picType
	*/
	public void setPicType(Integer picType ){
		this.picType = picType;
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
	* 通道端返回的图片ID
	*@return 
	*/
	public String getPicId(){
		return  picId;
	}
	/**
	* 通道端返回的图片ID
	*@param  picId
	*/
	public void setPicId(String picId ){
		this.picId = picId;
	}
	
	/**
	* 图片路径
	*@return 
	*/
	public String getPicPath(){
		return  picPath;
	}
	/**
	* 图片路径
	*@param  picPath
	*/
	public void setPicPath(String picPath ){
		this.picPath = picPath;
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
