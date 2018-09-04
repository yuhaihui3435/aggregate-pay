package com.xtf.aggregatepay.entity;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2018-09-04
*/
@Table(name="aggregate_pay_db.MER_INFO_T")
public class MerInfo extends com.xtf.aggregatepay.core.BaseEntity  {
	
	/*
	ID
	*/
	private Integer id ;
	/*
	渠道ID
	*/
	private Integer channelId ;
	/*
	乐观锁
	*/
	private Integer revision ;
	/*
	开户人姓名
	*/
	private String accName ;
	/*
	开户账号
	*/
	private String accNum ;
	/*
	银行账户类型-TO_PRIVATE:对私,TO_PUBLIC:对公
	*/
	private String accType ;
	/*
	详细地址
	*/
	private String addrDetail ;
	/*
	本系统编号
	*/
	private String apCode ;
	/*
	区
	*/
	private String areaCode ;
	/*
	身份证背面ID
	*/
	private String backCardId ;
	/*
	银行卡照ID
	*/
	private String bankCardId ;
	/*
	开户行所在市
	*/
	private String bankCityCode ;
	/*
	银行编码
	*/
	private String bankCode ;
	/*
	开户行网点支行名
	*/
	private String bankNameBranch ;
	/*
	开户行所在省
	*/
	private String bankProvCode ;
	/*
	营业执照编号
	*/
	private String busLicenseNo ;
	/*
	营业执照有效期结束日期
	*/
	private String busLicenseValidityPeroid ;
	/*
	身份证正面照ID
	*/
	private String cardId ;
	/*
	银行卡类型-0:借记卡
	*/
	private String cardType ;
	/*
	市
	*/
	private String cityCode ;
	/*
	创建人
	*/
	private String createdBy ;
	/*
	行业类型
	*/
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
	开户身份证号
	*/
	private String idCardNum ;
	/*
	身份证有效期
	*/
	private String idCardValidityPeroid ;
	/*
	商户进件类型
	*/
	private String incomeType ;
	/*
	证件编号
	*/
	private String legalIdCardNum ;
	/*
	身份证有效期结束日期
	*/
	private String legalIdCardValidityPeroid ;
	/*
	法人
	*/
	private String legalPerson ;
	/*
	法人手机号
	*/
	private String legalPhone ;
	/*
	营业执照ID
	*/
	private String licenseId ;
	/*
	联系人
	*/
	private String linkPerson ;
	/*
	联系人电话
	*/
	private String linkPhone ;
	/*
	门头照ID
	*/
	private String mainPhotoId ;
	/*
	商户名
	*/
	private String mercName ;
	/*
	商户编号
	*/
	private String mercNum ;
	/*
	商户简称
	*/
	private String mercShortName ;
	/*
	商户类型-personal:个人,business:企业
	*/
	private String mercType ;
	/*
	组织机构代码照ID
	*/
	private String orgPhotoId ;
	/*
	银行预留手机号
	*/
	private String phone ;
	/*
	手持身份证照ID
	*/
	private String picPeobleId ;
	/*
	非法人结算授权书ID
	*/
	private String powerId ;
	/*
	产品-ALIPAY:阿里,WECHATPAY:微信,QQPAY:QQ
	*/
	private String product ;
	/*
	商户协议照ID
	*/
	private String protocolPhotoId ;
	/*
	省
	*/
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
	
	public MerInfo() {
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
	* 身份证背面ID
	*@return 
	*/
	public String getBackCardId(){
		return  backCardId;
	}
	/**
	* 身份证背面ID
	*@param  backCardId
	*/
	public void setBackCardId(String backCardId ){
		this.backCardId = backCardId;
	}
	
	/**
	* 银行卡照ID
	*@return 
	*/
	public String getBankCardId(){
		return  bankCardId;
	}
	/**
	* 银行卡照ID
	*@param  bankCardId
	*/
	public void setBankCardId(String bankCardId ){
		this.bankCardId = bankCardId;
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
	* 身份证正面照ID
	*@return 
	*/
	public String getCardId(){
		return  cardId;
	}
	/**
	* 身份证正面照ID
	*@param  cardId
	*/
	public void setCardId(String cardId ){
		this.cardId = cardId;
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
	* 营业执照ID
	*@return 
	*/
	public String getLicenseId(){
		return  licenseId;
	}
	/**
	* 营业执照ID
	*@param  licenseId
	*/
	public void setLicenseId(String licenseId ){
		this.licenseId = licenseId;
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
	* 门头照ID
	*@return 
	*/
	public String getMainPhotoId(){
		return  mainPhotoId;
	}
	/**
	* 门头照ID
	*@param  mainPhotoId
	*/
	public void setMainPhotoId(String mainPhotoId ){
		this.mainPhotoId = mainPhotoId;
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
	* 组织机构代码照ID
	*@return 
	*/
	public String getOrgPhotoId(){
		return  orgPhotoId;
	}
	/**
	* 组织机构代码照ID
	*@param  orgPhotoId
	*/
	public void setOrgPhotoId(String orgPhotoId ){
		this.orgPhotoId = orgPhotoId;
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
	* 手持身份证照ID
	*@return 
	*/
	public String getPicPeobleId(){
		return  picPeobleId;
	}
	/**
	* 手持身份证照ID
	*@param  picPeobleId
	*/
	public void setPicPeobleId(String picPeobleId ){
		this.picPeobleId = picPeobleId;
	}
	
	/**
	* 非法人结算授权书ID
	*@return 
	*/
	public String getPowerId(){
		return  powerId;
	}
	/**
	* 非法人结算授权书ID
	*@param  powerId
	*/
	public void setPowerId(String powerId ){
		this.powerId = powerId;
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
	* 商户协议照ID
	*@return 
	*/
	public String getProtocolPhotoId(){
		return  protocolPhotoId;
	}
	/**
	* 商户协议照ID
	*@param  protocolPhotoId
	*/
	public void setProtocolPhotoId(String protocolPhotoId ){
		this.protocolPhotoId = protocolPhotoId;
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
