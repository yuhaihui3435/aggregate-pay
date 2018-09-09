package com.xtf.aggregatepay.entity;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

import lombok.Builder;
import org.beetl.sql.core.annotatoin.Table;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


/* 
* 
* gen by beetlsql 2018-09-04
*/
@Table(name="aggregate_pay_db.MER_INFO_T")
@Builder
public class MerInfo extends com.xtf.aggregatepay.core.BaseEntity  {
	
	/*
	ID
	*/
	private Integer id ;
	/*
	乐观锁
	*/
	private Integer revision ;
	/*
	详细地址
	*/
	@NotBlank(message = "详细地址必填")
	@Length(message = "详细地址长度最大不超过200个数字",max = 200,min = 1)
	private String addrDetail ;
	/*
	本系统编号
	*/
	@NotBlank(message = "apCode必填")
	private String apCode ;
	/*
	本系统商户号
	*/
	private String apMercCode ;
	/*
	区
	*/
	@NotBlank(message = "区必填")
	private String areaCode ;
	/*
	营业执照编号
	*/
	@NotBlank(message = "营业执照号必填")
	@Length(message = "营业执照号长度不能超过50个字",max = 50,min = 1)
	private String busLicenseNo ;
	/*
	营业执照有效期结束日期
	*/
	@NotBlank(message = "营业执照有效期必填")
	@Length(message = "营业执照有效期长度为10，格式为YYYY-MM-DD",max = 11,min = 9)
	private String busLicenseValidityPeroid ;
	/*
	渠道编号
	*/
	@NotBlank(message = "渠道编号必填")
	private String channelCode ;
	/*
	市
	*/
	@NotBlank(message = "市必填")
	private String cityCode ;
	/*
	创建人
	*/
	private String createdBy ;
	/*
	行业类型
	*/
	@NotBlank(message = "行业类型必填")
	private String customMccType ;
	/*
	数据状态:0:正常,1:停用
	*/
	private String dataStatus ;
	/*
	删除人
	*/
	private String deleteBy ;
	/*
	EMAIL
	*/
	private String email ;
	/*
	商户进件类型
	*/
	private String incomeType ;
	/*
	证件编号
	*/
	@NotBlank(message = "身份证号必填")
	@Length(message = "身份证长度由15-18位字组成",max = 18 ,min = 15)
	private String legalIdCardNum ;
	/*
	身份证有效期结束日期
	*/
	@NotBlank(message = "身份证有效期结束日期必填")
	@Length(message = "身份证有效期结束日期长度为10，YYYY-MM-DD格式",max = 11,min = 9)
	private String legalIdCardValidityPeroid ;
	/*
	法人
	*/
	@NotBlank(message = "法人必填")
	@Length(message = "法人由1-32个字组成",max = 32,min = 1)
	private String legalPerson ;
	/*
	法人手机号
	*/
	@NotBlank(message = "法人手机号必填")
	@Length(message = "手机号由11个数字组成",max = 12,min = 10)
	private String legalPhone ;
	/*
	联系人
	*/
	@NotBlank(message = "联系人必填")
	@Length(message = "联系人最大长度不能超过",max = 32,min = 1)
	private String linkPerson ;
	/*
	联系人电话
	*/
	@NotBlank(message = "联系人手机号必填")
	@Length(message = "联系人手机号由11个数字组成",max = 12,min = 10)
	private String linkPhone ;
	/*
	商户名
	*/
	@NotBlank(message = "商户名称必填")
	@Length(message = "商户名称由5-50个字组成",max = 50,min = 5)
	private String mercName ;
	/*
	商户编号
	*/
	private String mercNum ;
	/*
	商户简称
	*/
	@NotBlank(message = "商户简称号必填")
	@Length(message = "商户简称1-15个字组成",max = 15,min = 1)
	private String mercShortName ;
	/*
	商户类型-personal:个人,business:企业
	*/
	@NotBlank(message = "商户类型必填")
	@Length(message = "商户类型由2-32个字组成",max = 32,min = 2)
	private String mercType ;
	/*
	产品-ALIPAY:阿里,WECHATPAY:微信,QQPAY:QQ
	*/
	private String product ;
	/*
	省
	*/
	@NotBlank(message = "省必填")
	private String provCode ;
	/*
	费率
	*/
	private String rate ;
	/*
	费率编号
	*/
	private String rateCode ;
	/*
	结算方式-T1:T1,Ts:(T0业务)
	*/
	private String settleWay ;
	/*
	状态-SHZ:审核中,SHTG:审核通过,DDSH:等待审核,SHJJ:审核未通过
	*/
	private String status ;
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

	private String appMercCode;
	
//	public MerInfo() {
//	}

	public String getAppMercCode() {
		return appMercCode;
	}

	public void setAppMercCode(String appMercCode) {
		this.appMercCode = appMercCode;
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
	* 详细地址
	*@return 
	*/
	public String getAddrDetail(){
		return  addrDetail;
	}
	/**
	* 详细地址
	*@param  addrDetail
	*/
	public void setAddrDetail(String addrDetail ){
		this.addrDetail = addrDetail;
	}
	
	/**
	* 本系统编号
	*@return 
	*/
	public String getApCode(){
		return  apCode;
	}
	/**
	* 本系统编号
	*@param  apCode
	*/
	public void setApCode(String apCode ){
		this.apCode = apCode;
	}
	
	/**
	* 本系统商户号
	*@return 
	*/
	public String getApMercCode(){
		return  apMercCode;
	}
	/**
	* 本系统商户号
	*@param  apMercCode
	*/
	public void setApMercCode(String apMercCode ){
		this.apMercCode = apMercCode;
	}
	
	/**
	* 区
	*@return 
	*/
	public String getAreaCode(){
		return  areaCode;
	}
	/**
	* 区
	*@param  areaCode
	*/
	public void setAreaCode(String areaCode ){
		this.areaCode = areaCode;
	}
	
	/**
	* 营业执照编号
	*@return 
	*/
	public String getBusLicenseNo(){
		return  busLicenseNo;
	}
	/**
	* 营业执照编号
	*@param  busLicenseNo
	*/
	public void setBusLicenseNo(String busLicenseNo ){
		this.busLicenseNo = busLicenseNo;
	}
	
	/**
	* 营业执照有效期结束日期
	*@return 
	*/
	public String getBusLicenseValidityPeroid(){
		return  busLicenseValidityPeroid;
	}
	/**
	* 营业执照有效期结束日期
	*@param  busLicenseValidityPeroid
	*/
	public void setBusLicenseValidityPeroid(String busLicenseValidityPeroid ){
		this.busLicenseValidityPeroid = busLicenseValidityPeroid;
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
	* 市
	*@return 
	*/
	public String getCityCode(){
		return  cityCode;
	}
	/**
	* 市
	*@param  cityCode
	*/
	public void setCityCode(String cityCode ){
		this.cityCode = cityCode;
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
	* 行业类型
	*@return 
	*/
	public String getCustomMccType(){
		return  customMccType;
	}
	/**
	* 行业类型
	*@param  customMccType
	*/
	public void setCustomMccType(String customMccType ){
		this.customMccType = customMccType;
	}
	
	/**
	* 数据状态:0:正常,1:停用
	*@return 
	*/
	public String getDataStatus(){
		return  dataStatus;
	}
	/**
	* 数据状态:0:正常,1:停用
	*@param  dataStatus
	*/
	public void setDataStatus(String dataStatus ){
		this.dataStatus = dataStatus;
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
	* 商户进件类型
	*@return 
	*/
	public String getIncomeType(){
		return  incomeType;
	}
	/**
	* 商户进件类型
	*@param  incomeType
	*/
	public void setIncomeType(String incomeType ){
		this.incomeType = incomeType;
	}
	
	/**
	* 证件编号
	*@return 
	*/
	public String getLegalIdCardNum(){
		return  legalIdCardNum;
	}
	/**
	* 证件编号
	*@param  legalIdCardNum
	*/
	public void setLegalIdCardNum(String legalIdCardNum ){
		this.legalIdCardNum = legalIdCardNum;
	}
	
	/**
	* 身份证有效期结束日期
	*@return 
	*/
	public String getLegalIdCardValidityPeroid(){
		return  legalIdCardValidityPeroid;
	}
	/**
	* 身份证有效期结束日期
	*@param  legalIdCardValidityPeroid
	*/
	public void setLegalIdCardValidityPeroid(String legalIdCardValidityPeroid ){
		this.legalIdCardValidityPeroid = legalIdCardValidityPeroid;
	}
	
	/**
	* 法人
	*@return 
	*/
	public String getLegalPerson(){
		return  legalPerson;
	}
	/**
	* 法人
	*@param  legalPerson
	*/
	public void setLegalPerson(String legalPerson ){
		this.legalPerson = legalPerson;
	}
	
	/**
	* 法人手机号
	*@return 
	*/
	public String getLegalPhone(){
		return  legalPhone;
	}
	/**
	* 法人手机号
	*@param  legalPhone
	*/
	public void setLegalPhone(String legalPhone ){
		this.legalPhone = legalPhone;
	}
	
	/**
	* 联系人
	*@return 
	*/
	public String getLinkPerson(){
		return  linkPerson;
	}
	/**
	* 联系人
	*@param  linkPerson
	*/
	public void setLinkPerson(String linkPerson ){
		this.linkPerson = linkPerson;
	}
	
	/**
	* 联系人电话
	*@return 
	*/
	public String getLinkPhone(){
		return  linkPhone;
	}
	/**
	* 联系人电话
	*@param  linkPhone
	*/
	public void setLinkPhone(String linkPhone ){
		this.linkPhone = linkPhone;
	}
	
	/**
	* 商户名
	*@return 
	*/
	public String getMercName(){
		return  mercName;
	}
	/**
	* 商户名
	*@param  mercName
	*/
	public void setMercName(String mercName ){
		this.mercName = mercName;
	}
	
	/**
	* 商户编号
	*@return 
	*/
	public String getMercNum(){
		return  mercNum;
	}
	/**
	* 商户编号
	*@param  mercNum
	*/
	public void setMercNum(String mercNum ){
		this.mercNum = mercNum;
	}
	
	/**
	* 商户简称
	*@return 
	*/
	public String getMercShortName(){
		return  mercShortName;
	}
	/**
	* 商户简称
	*@param  mercShortName
	*/
	public void setMercShortName(String mercShortName ){
		this.mercShortName = mercShortName;
	}
	
	/**
	* 商户类型-personal:个人,business:企业
	*@return 
	*/
	public String getMercType(){
		return  mercType;
	}
	/**
	* 商户类型-personal:个人,business:企业
	*@param  mercType
	*/
	public void setMercType(String mercType ){
		this.mercType = mercType;
	}
	
	/**
	* 产品-ALIPAY:阿里,WECHATPAY:微信,QQPAY:QQ
	*@return 
	*/
	public String getProduct(){
		return  product;
	}
	/**
	* 产品-ALIPAY:阿里,WECHATPAY:微信,QQPAY:QQ
	*@param  product
	*/
	public void setProduct(String product ){
		this.product = product;
	}
	
	/**
	* 省
	*@return 
	*/
	public String getProvCode(){
		return  provCode;
	}
	/**
	* 省
	*@param  provCode
	*/
	public void setProvCode(String provCode ){
		this.provCode = provCode;
	}
	
	/**
	* 费率
	*@return 
	*/
	public String getRate(){
		return  rate;
	}
	/**
	* 费率
	*@param  rate
	*/
	public void setRate(String rate ){
		this.rate = rate;
	}
	
	/**
	* 费率编号
	*@return 
	*/
	public String getRateCode(){
		return  rateCode;
	}
	/**
	* 费率编号
	*@param  rateCode
	*/
	public void setRateCode(String rateCode ){
		this.rateCode = rateCode;
	}
	
	/**
	* 结算方式-T1:T1,Ts:(T0业务)
	*@return 
	*/
	public String getSettleWay(){
		return  settleWay;
	}
	/**
	* 结算方式-T1:T1,Ts:(T0业务)
	*@param  settleWay
	*/
	public void setSettleWay(String settleWay ){
		this.settleWay = settleWay;
	}
	
	/**
	* 状态-SHZ:审核中,SHTG:审核通过,DDSH:等待审核,SHJJ:审核未通过
	*@return 
	*/
	public String getStatus(){
		return  status;
	}
	/**
	* 状态-SHZ:审核中,SHTG:审核通过,DDSH:等待审核,SHJJ:审核未通过
	*@param  status
	*/
	public void setStatus(String status ){
		this.status = status;
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
