package com.xtf.aggregatepay.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.beetl.sql.core.annotatoin.Table;

import java.math.BigDecimal;
import java.util.Date;


/* 
* 
* gen by beetlsql 2018-09-08
*/
@Table(name="CHANNEL_INFO_T")
@Builder(toBuilder = true)
@Data
public class ChannelInfo extends com.xtf.aggregatepay.core.BaseEntity  {
	
	/*
	ID
	*/
	private Integer id ;
	/*
	上级渠道ID,0标识顶级渠道
	*/
	private Integer pid ;
	/*
	乐观锁
	*/
	private Integer revision ;
	/*
	银行账户类型-TO_PUBLIC:对公,TO_PRIVATE:对私
	*/
	private String accType ;
	/*
	开户行身份证号
	*/
	private String baknIdCard ;
	/*
	开户行账号
	*/
	private String bankAccount ;
	/*
	开户名
	*/
	private String bankAccountName ;
	/*
	开户行号
	*/
	private String bankCode ;
	/*
	开户行名
	*/
	private String bankName ;
	/*
	银行预留手机号
	*/
	private String bankTel ;
	/*
	渠道下商户单日最大限额
	*/
	private BigDecimal ceilingOfDay ;
	/*
	渠道下商户单笔最大限额
	*/
	private BigDecimal ceilingOfSingle ;
	/*
	编号
	*/
	private String code ;
	/*
	创建人
	*/
	private String createdBy ;
	/*
	删除人
	*/
	private String deleteBy ;
	/*
	EMAIL
	*/
	private String email ;
	/*
	身份证有效期
	*/
	private String idCardValidityPeroid ;
	/*
	名称
	*/
	private String name ;
	/*
	状态-0:正常;1:停用
	*/
	private String status ;
	/*
	费率整数
	*/
	private String t1Rate ;
	/*
	费率编码
	*/
	private String t1RateCode ;
	/*
	联系电话
	*/
	private String tel ;
	/*
	T0费率
	*/
	private String tsRate ;
	/*
	T0费率编号
	*/
	private String tsRateCode ;
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

	private String type;

	private BigDecimal minimumLimit;

	private String startAt;

	private String endAt;

	private String online;

	@Tolerate
	public ChannelInfo() {
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
	* 上级渠道ID,0标识顶级渠道
	*@return 
	*/
	public Integer getPid(){
		return  pid;
	}
	/**
	* 上级渠道ID,0标识顶级渠道
	*@param  pid
	*/
	public void setPid(Integer pid ){
		this.pid = pid;
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
	* 银行账户类型-TO_PUBLIC:对公,TO_PRIVATE:对私
	*@return 
	*/
	public String getAccType(){
		return  accType;
	}
	/**
	* 银行账户类型-TO_PUBLIC:对公,TO_PRIVATE:对私
	*@param  accType
	*/
	public void setAccType(String accType ){
		this.accType = accType;
	}
	
	/**
	* 开户行身份证号
	*@return 
	*/
	public String getBaknIdCard(){
		return  baknIdCard;
	}
	/**
	* 开户行身份证号
	*@param  baknIdCard
	*/
	public void setBaknIdCard(String baknIdCard ){
		this.baknIdCard = baknIdCard;
	}
	
	/**
	* 开户行账号
	*@return 
	*/
	public String getBankAccount(){
		return  bankAccount;
	}
	/**
	* 开户行账号
	*@param  bankAccount
	*/
	public void setBankAccount(String bankAccount ){
		this.bankAccount = bankAccount;
	}
	
	/**
	* 开户名
	*@return 
	*/
	public String getBankAccountName(){
		return  bankAccountName;
	}
	/**
	* 开户名
	*@param  bankAccountName
	*/
	public void setBankAccountName(String bankAccountName ){
		this.bankAccountName = bankAccountName;
	}
	
	/**
	* 开户行号
	*@return 
	*/
	public String getBankCode(){
		return  bankCode;
	}
	/**
	* 开户行号
	*@param  bankCode
	*/
	public void setBankCode(String bankCode ){
		this.bankCode = bankCode;
	}
	
	/**
	* 开户行名
	*@return 
	*/
	public String getBankName(){
		return  bankName;
	}
	/**
	* 开户行名
	*@param  bankName
	*/
	public void setBankName(String bankName ){
		this.bankName = bankName;
	}
	
	/**
	* 银行预留手机号
	*@return 
	*/
	public String getBankTel(){
		return  bankTel;
	}
	/**
	* 银行预留手机号
	*@param  bankTel
	*/
	public void setBankTel(String bankTel ){
		this.bankTel = bankTel;
	}
	
	/**
	* 渠道下商户单日最大限额
	*@return 
	*/
	public BigDecimal getCeilingOfDay(){
		return  ceilingOfDay;
	}
	/**
	* 渠道下商户单日最大限额
	*@param  ceilingOfDay
	*/
	public void setCeilingOfDay(BigDecimal ceilingOfDay ){
		this.ceilingOfDay = ceilingOfDay;
	}
	
	/**
	* 渠道下商户单笔最大限额
	*@return 
	*/
	public BigDecimal getCeilingOfSingle(){
		return  ceilingOfSingle;
	}
	/**
	* 渠道下商户单笔最大限额
	*@param  ceilingOfSingle
	*/
	public void setCeilingOfSingle(BigDecimal ceilingOfSingle ){
		this.ceilingOfSingle = ceilingOfSingle;
	}
	
	/**
	* 编号
	*@return 
	*/
	public String getCode(){
		return  code;
	}
	/**
	* 编号
	*@param  code
	*/
	public void setCode(String code ){
		this.code = code;
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
	* EMAIL
	*@return 
	*/
	public String getEmail(){
		return  email;
	}
	/**
	* EMAIL
	*@param  email
	*/
	public void setEmail(String email ){
		this.email = email;
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
	* 名称
	*@return 
	*/
	public String getName(){
		return  name;
	}
	/**
	* 名称
	*@param  name
	*/
	public void setName(String name ){
		this.name = name;
	}
	
	/**
	* 状态-0:正常;1:停用
	*@return 
	*/
	public String getStatus(){
		return  status;
	}
	/**
	* 状态-0:正常;1:停用
	*@param  status
	*/
	public void setStatus(String status ){
		this.status = status;
	}
	
	/**
	* 费率整数
	*@return 
	*/
	public String getT1Rate(){
		return  t1Rate;
	}
	/**
	* 费率整数
	*@param  t1Rate
	*/
	public void setT1Rate(String t1Rate ){
		this.t1Rate = t1Rate;
	}
	
	/**
	* 费率编码
	*@return 
	*/
	public String getT1RateCode(){
		return  t1RateCode;
	}
	/**
	* 费率编码
	*@param  t1RateCode
	*/
	public void setT1RateCode(String t1RateCode ){
		this.t1RateCode = t1RateCode;
	}
	
	/**
	* 联系电话
	*@return 
	*/
	public String getTel(){
		return  tel;
	}
	/**
	* 联系电话
	*@param  tel
	*/
	public void setTel(String tel ){
		this.tel = tel;
	}
	
	/**
	* T0费率
	*@return 
	*/
	public String getTsRate(){
		return  tsRate;
	}
	/**
	* T0费率
	*@param  tsRate
	*/
	public void setTsRate(String tsRate ){
		this.tsRate = tsRate;
	}
	
	/**
	* T0费率编号
	*@return 
	*/
	public String getTsRateCode(){
		return  tsRateCode;
	}
	/**
	* T0费率编号
	*@param  tsRateCode
	*/
	public void setTsRateCode(String tsRateCode ){
		this.tsRateCode = tsRateCode;
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
