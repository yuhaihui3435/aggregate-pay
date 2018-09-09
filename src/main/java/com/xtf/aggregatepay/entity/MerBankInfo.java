package com.xtf.aggregatepay.entity;

import lombok.experimental.Tolerate;
import org.beetl.sql.core.annotatoin.Table;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


/* 
* 
* gen by beetlsql 2018-09-04
*/
@Table(name="aggregate_pay_db.MER_BANK_INFO_T")
public class MerBankInfo extends com.xtf.aggregatepay.core.BaseEntity  {
	
	/*
	ID
	*/
	private Integer id ;
	/*
	ID
	*/

	private Integer merId ;
	/*
	乐观锁
	*/
	private Integer revision ;
	/*
	开户人姓名
	*/
	@NotBlank(message = "开户人姓名必填")
	@Length(message = "开户人姓名由1-100个字组成",max = 100,min = 1)
	private String accName ;
	/*
	开户账号
	*/
	@NotBlank(message = "开户账号必填")
	@Length(message = "开户人姓名由1-32个字组成",max = 32,min = 1)
	private String accNum ;
	/*
	银行账户类型-TO_PRIVATE:对私,TO_PUBLIC:对公
	*/
	@NotBlank(message = "银行账户类型必填")
	private String accType ;
	/*
	开户行所在市
	*/
	@NotBlank(message = "开户行所在市编号必填")
	private String bankCityCode ;
	/*
	银行编码
	*/
	@NotBlank(message = "银行编码必填")
	private String bankCode ;
	/*
	开户行网点支行名
	*/
	@NotBlank(message = "开户行网点支行名必填")
	@Length(message = "开户行网点支行名由1-100个字组成",max = 100,min = 1)
	private String bankNameBranch ;
	/*
	开户行所在省
	*/
	@NotBlank(message = "开户行所在省编号必填")
	private String bankProvCode ;
	/*
	银行卡类型-0:借记卡
	*/
	private String cardType ;
	/*
	创建人
	*/
	private String createdBy ;
	/*
	删除人
	*/
	private String deleteBy ;
	/*
	开户身份证号
	*/
	@NotBlank(message = "开户身份证必填")
	@Length(message = "开户身份证由15-18个字组成",max = 18,min = 15)
	private String idCardNum ;
	/*
	身份证有效期
	*/
	@NotBlank(message = "身份证有效期必填")
	@Length(message = "身份证有效期长度为10，YYYY-MM-DD格式",max = 11,min = 9)
	private String idCardValidityPeroid ;
	/*
	银行预留手机号
	*/
	@NotBlank(message = "银行预留手机号必填")
	@Length(message = "银行预留手机号长度为11",max = 11,min = 9)
	private String phone ;
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
	public MerBankInfo() {
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
	* ID
	*@return 
	*/
	public Integer getMerId(){
		return  merId;
	}
	/**
	* ID
	*@param  merId
	*/
	public void setMerId(Integer merId ){
		this.merId = merId;
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
	* 开户人姓名
	*@return 
	*/
	public String getAccName(){
		return  accName;
	}
	/**
	* 开户人姓名
	*@param  accName
	*/
	public void setAccName(String accName ){
		this.accName = accName;
	}
	
	/**
	* 开户账号
	*@return 
	*/
	public String getAccNum(){
		return  accNum;
	}
	/**
	* 开户账号
	*@param  accNum
	*/
	public void setAccNum(String accNum ){
		this.accNum = accNum;
	}
	
	/**
	* 银行账户类型-TO_PRIVATE:对私,TO_PUBLIC:对公
	*@return 
	*/
	public String getAccType(){
		return  accType;
	}
	/**
	* 银行账户类型-TO_PRIVATE:对私,TO_PUBLIC:对公
	*@param  accType
	*/
	public void setAccType(String accType ){
		this.accType = accType;
	}
	
	/**
	* 开户行所在市
	*@return 
	*/
	public String getBankCityCode(){
		return  bankCityCode;
	}
	/**
	* 开户行所在市
	*@param  bankCityCode
	*/
	public void setBankCityCode(String bankCityCode ){
		this.bankCityCode = bankCityCode;
	}
	
	/**
	* 银行编码
	*@return 
	*/
	public String getBankCode(){
		return  bankCode;
	}
	/**
	* 银行编码
	*@param  bankCode
	*/
	public void setBankCode(String bankCode ){
		this.bankCode = bankCode;
	}
	
	/**
	* 开户行网点支行名
	*@return 
	*/
	public String getBankNameBranch(){
		return  bankNameBranch;
	}
	/**
	* 开户行网点支行名
	*@param  bankNameBranch
	*/
	public void setBankNameBranch(String bankNameBranch ){
		this.bankNameBranch = bankNameBranch;
	}
	
	/**
	* 开户行所在省
	*@return 
	*/
	public String getBankProvCode(){
		return  bankProvCode;
	}
	/**
	* 开户行所在省
	*@param  bankProvCode
	*/
	public void setBankProvCode(String bankProvCode ){
		this.bankProvCode = bankProvCode;
	}
	
	/**
	* 银行卡类型-0:借记卡
	*@return 
	*/
	public String getCardType(){
		return  cardType;
	}
	/**
	* 银行卡类型-0:借记卡
	*@param  cardType
	*/
	public void setCardType(String cardType ){
		this.cardType = cardType;
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
	* 开户身份证号
	*@return 
	*/
	public String getIdCardNum(){
		return  idCardNum;
	}
	/**
	* 开户身份证号
	*@param  idCardNum
	*/
	public void setIdCardNum(String idCardNum ){
		this.idCardNum = idCardNum;
	}
	
	/**
	* 身份证有效期
	*@return 
	*/
	public String getIdCardValidityPeroid(){
		return  idCardValidityPeroid;
	}
	/**
	* 身份证有效期
	*@param  idCardValidityPeroid
	*/
	public void setIdCardValidityPeroid(String idCardValidityPeroid ){
		this.idCardValidityPeroid = idCardValidityPeroid;
	}
	
	/**
	* 银行预留手机号
	*@return 
	*/
	public String getPhone(){
		return  phone;
	}
	/**
	* 银行预留手机号
	*@param  phone
	*/
	public void setPhone(String phone ){
		this.phone = phone;
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
