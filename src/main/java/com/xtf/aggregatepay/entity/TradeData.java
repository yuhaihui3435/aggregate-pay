package com.xtf.aggregatepay.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.beetl.sql.core.annotatoin.Table;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


/* 
* 
* gen by beetlsql 2018-09-08
*/
@Table(name="TRADE_DATA_T")
@Builder(toBuilder = true)
@Data
public class TradeData extends com.xtf.aggregatepay.core.BaseEntity  {
	
	/*
	ID
	*/
	private Integer id ;
	/*
	乐观锁
	*/
	private Integer revision ;
	/*
	代理商编号
	*/
	private String agentNo ;
	/*
	银行订单号
	*/
	private String bankOrder ;
	/*
	收款方式类型
	*/
	@NotBlank(message = "收款方式必填")
	@Size(max = 32,message = "收款方式最大长度32")
	private String bizType ;
	/*
	上游回调通知地址
	*/
	private String callBackUrl ;
	/*
	渠道编号
	*/
	private String channelCode ;
	/*
	二维码地址
	*/
	private String codeurl ;
	/*
	创建人
	*/
	private String createdBy ;
	/*
	删除人
	*/
	private String deleteBy ;
	/*
	下游回调通知地址
	*/
	@NotBlank(message = "交易结果通知地址必填")
	@Size(max = 512,message = "交易结果通知地址最大长度512")
	private String downCallBackUrl ;
	/*
	上游交易完成时间
	*/
	private String finishTime ;
	/*
	商户编号
	*/
	@NotBlank(message = "商户编号必填")
	private String merchantNo ;
	/*
	订单编号
	*/
	@NotBlank(message = "订单号必填")
	@Size(max= 32,message = "订单编号最大32")
	private String merOrder ;
	/*
	订单状态-success:交易成功,sacn_pay_faild:交易失败,processing:交易处理中,closefailure:交易关闭,refund_success:交易已退款
	*/
	private String orderStatus ;
	/*
	平台支付订单号
	*/
	private String payOrderNo ;
	/*
	产品名
	*/
	@Size(max = 100,min=1,message = "产品名最大不超过100")
	private String productName ;
	/*
	应答吗
	*/
	private String resCode ;
	/*
	应答信息
	*/
	private String resMsg ;
	/*
	结算状态-0:已结清,1:未结算
	*/
	private String settleStatus ;
	/*
	商户的APPID
	*/
	private String subAppid ;
	/*
	客户的用户ID(微信交易返回)
	*/
	private String subOpenid ;
	/*
	交易完成时间
	*/
	private String timeEnd ;
	/*
	交易金额(以分为单位)
	*/
	@NotBlank(message = "交易金额不能为空")
	@Pattern(regexp = "^[1-9]+\\d*$",message = "交易金额格不正确")
	private String tradeAmount ;
	/*
	交易类型
	*/
	private String tradeType ;
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

	private String downCallBackRet;

	private Date downCallBackRetLasttime;
	@Transient
	private String merName;
	private String pageBackUrl;

	private String clientCode;

	@Tolerate
	public TradeData() {
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
	* 代理商编号
	*@return 
	*/
	public String getAgentNo(){
		return  agentNo;
	}
	/**
	* 代理商编号
	*@param  agentNo
	*/
	public void setAgentNo(String agentNo ){
		this.agentNo = agentNo;
	}
	
	/**
	* 银行订单号
	*@return 
	*/
	public String getBankOrder(){
		return  bankOrder;
	}
	/**
	* 银行订单号
	*@param  bankOrder
	*/
	public void setBankOrder(String bankOrder ){
		this.bankOrder = bankOrder;
	}
	
	/**
	* 收款方式类型
	*@return 
	*/
	public String getBizType(){
		return  bizType;
	}
	/**
	* 收款方式类型
	*@param  bizType
	*/
	public void setBizType(String bizType ){
		this.bizType = bizType;
	}
	
	/**
	* 上游回调通知地址
	*@return 
	*/
	public String getCallBackUrl(){
		return  callBackUrl;
	}
	/**
	* 上游回调通知地址
	*@param  callBackUrl
	*/
	public void setCallBackUrl(String callBackUrl ){
		this.callBackUrl = callBackUrl;
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
	* 二维码地址
	*@return 
	*/
	public String getCodeurl(){
		return  codeurl;
	}
	/**
	* 二维码地址
	*@param  codeurl
	*/
	public void setCodeurl(String codeurl ){
		this.codeurl = codeurl;
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
	* 下游回调通知地址
	*@return 
	*/
	public String getDownCallBackUrl(){
		return  downCallBackUrl;
	}
	/**
	* 下游回调通知地址
	*@param  downCallBackUrl
	*/
	public void setDownCallBackUrl(String downCallBackUrl ){
		this.downCallBackUrl = downCallBackUrl;
	}
	
	/**
	* 上游交易完成时间
	*@return 
	*/
	public String getFinishTime(){
		return  finishTime;
	}
	/**
	* 上游交易完成时间
	*@param  finishTime
	*/
	public void setFinishTime(String finishTime ){
		this.finishTime = finishTime;
	}
	
	/**
	* 商户编号
	*@return 
	*/
	public String getMerchantNo(){
		return  merchantNo;
	}
	/**
	* 商户编号
	*@param  merchantNo
	*/
	public void setMerchantNo(String merchantNo ){
		this.merchantNo = merchantNo;
	}
	
	/**
	* 订单编号
	*@return 
	*/
	public String getMerOrder(){
		return  merOrder;
	}
	/**
	* 订单编号
	*@param  merOrder
	*/
	public void setMerOrder(String merOrder ){
		this.merOrder = merOrder;
	}
	
	/**
	* 订单状态-success:交易成功,sacn_pay_faild:交易失败,processing:交易处理中,closefailure:交易关闭,refund_success:交易已退款
	*@return 
	*/
	public String getOrderStatus(){
		return  orderStatus;
	}
	/**
	* 订单状态-success:交易成功,sacn_pay_faild:交易失败,processing:交易处理中,closefailure:交易关闭,refund_success:交易已退款
	*@param  orderStatus
	*/
	public void setOrderStatus(String orderStatus ){
		this.orderStatus = orderStatus;
	}
	
	/**
	* 平台支付订单号
	*@return 
	*/
	public String getPayOrderNo(){
		return  payOrderNo;
	}
	/**
	* 平台支付订单号
	*@param  payOrderNo
	*/
	public void setPayOrderNo(String payOrderNo ){
		this.payOrderNo = payOrderNo;
	}
	
	/**
	* 产品名
	*@return 
	*/
	public String getProductName(){
		return  productName;
	}
	/**
	* 产品名
	*@param  productName
	*/
	public void setProductName(String productName ){
		this.productName = productName;
	}
	
	/**
	* 应答吗
	*@return 
	*/
	public String getResCode(){
		return  resCode;
	}
	/**
	* 应答吗
	*@param  resCode
	*/
	public void setResCode(String resCode ){
		this.resCode = resCode;
	}
	
	/**
	* 应答信息
	*@return 
	*/
	public String getResMsg(){
		return  resMsg;
	}
	/**
	* 应答信息
	*@param  resMsg
	*/
	public void setResMsg(String resMsg ){
		this.resMsg = resMsg;
	}
	
	/**
	* 结算状态-0:已结清,1:未结算
	*@return 
	*/
	public String getSettleStatus(){
		return  settleStatus;
	}
	/**
	* 结算状态-0:已结清,1:未结算
	*@param  settleStatus
	*/
	public void setSettleStatus(String settleStatus ){
		this.settleStatus = settleStatus;
	}
	
	/**
	* 商户的APPID
	*@return 
	*/
	public String getSubAppid(){
		return  subAppid;
	}
	/**
	* 商户的APPID
	*@param  subAppid
	*/
	public void setSubAppid(String subAppid ){
		this.subAppid = subAppid;
	}
	
	/**
	* 客户的用户ID(微信交易返回)
	*@return 
	*/
	public String getSubOpenid(){
		return  subOpenid;
	}
	/**
	* 客户的用户ID(微信交易返回)
	*@param  subOpenid
	*/
	public void setSubOpenid(String subOpenid ){
		this.subOpenid = subOpenid;
	}
	
	/**
	* 交易完成时间
	*@return 
	*/
	public String getTimeEnd(){
		return  timeEnd;
	}
	/**
	* 交易完成时间
	*@param  timeEnd
	*/
	public void setTimeEnd(String timeEnd ){
		this.timeEnd = timeEnd;
	}
	
	/**
	* 交易金额(以分为单位)
	*@return 
	*/
	public String getTradeAmount(){
		return  tradeAmount;
	}
	/**
	* 交易金额(以分为单位)
	*@param  tradeAmount
	*/
	public void setTradeAmount(String tradeAmount ){
		this.tradeAmount = tradeAmount;
	}
	
	/**
	* 交易类型
	*@return 
	*/
	public String getTradeType(){
		return  tradeType;
	}
	/**
	* 交易类型
	*@param  tradeType
	*/
	public void setTradeType(String tradeType ){
		this.tradeType = tradeType;
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

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
}
