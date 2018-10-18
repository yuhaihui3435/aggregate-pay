sample
===
* 注释

	select #use("cols")# from TRADE_DATA_T  where  #use("condition")#

cols
===
	REVISION,CREATED_BY,CREATED_TIME,UPDATED_BY,UPDATED_TIME,DELETE_TIME,DELETE_BY,ID,CHANNEL_CODE,MERCHANT_NO,AGENT_NO,MER_ORDER,PRODUCT_NAME,TRADE_AMOUNT,CALL_BACK_URL,DOWN_CALL_BACK_URL,TRADE_TYPE,BIZ_TYPE,RES_CODE,RES_MSG,PAY_ORDER_NO,CODEURL,BANK_ORDER,SUB_APPID,SUB_OPENID,FINISH_TIME,TIME_END,ORDER_STATUS,SETTLE_STATUS,PAGE_BACK_URL

updateSample
===
	
	REVISION=#revision#,CREATED_BY=#createdBy#,CREATED_TIME=#createdTime#,UPDATED_BY=#updatedBy#,UPDATED_TIME=#updatedTime#,DELETE_TIME=#deleteTime#,DELETE_BY=#deleteBy#,ID=#id#,CHANNEL_CODE=#channelCode#,MERCHANT_NO=#merchantNo#,AGENT_NO=#agentNo#,MER_ORDER=#merOrder#,PRODUCT_NAME=#productName#,TRADE_AMOUNT=#tradeAmount#,CALL_BACK_URL=#callBackUrl#,DOWN_CALL_BACK_URL=#downCallBackUrl#,TRADE_TYPE=#tradeType#,BIZ_TYPE=#bizType#,RES_CODE=#resCode#,RES_MSG=#resMsg#,PAY_ORDER_NO=#payOrderNo#,CODEURL=#codeurl#,BANK_ORDER=#bankOrder#,SUB_APPID=#subAppid#,SUB_OPENID=#subOpenid#,FINISH_TIME=#finishTime#,TIME_END=#timeEnd#,ORDER_STATUS=#orderStatus#,SETTLE_STATUS=#settleStatus#,PAGE_BACK_URL=#pageBackUrl#

condition
===

	1 = 1  
	@if(!isEmpty(revision)){
	 and REVISION=#revision#
	@}
	@if(!isEmpty(createdBy)){
	 and CREATED_BY=#createdBy#
	@}
	@if(!isEmpty(createdTime)){
	 and CREATED_TIME=#createdTime#
	@}
	@if(!isEmpty(updatedBy)){
	 and UPDATED_BY=#updatedBy#
	@}
	@if(!isEmpty(updatedTime)){
	 and UPDATED_TIME=#updatedTime#
	@}
	@if(!isEmpty(deleteTime)){
	 and DELETE_TIME=#deleteTime#
	@}
	@if(!isEmpty(deleteBy)){
	 and DELETE_BY=#deleteBy#
	@}
	@if(!isEmpty(id)){
	 and ID=#id#
	@}
	@if(!isEmpty(channelCode)){
	 and CHANNEL_CODE=#channelCode#
	@}
	@if(!isEmpty(merchantNo)){
	 and MERCHANT_NO=#merchantNo#
	@}
	@if(!isEmpty(agentNo)){
	 and AGENT_NO=#agentNo#
	@}
	@if(!isEmpty(merOrder)){
	 and MER_ORDER=#merOrder#
	@}
	@if(!isEmpty(productName)){
	 and PRODUCT_NAME=#productName#
	@}
	@if(!isEmpty(tradeAmount)){
	 and TRADE_AMOUNT=#tradeAmount#
	@}
	@if(!isEmpty(callBackUrl)){
	 and CALL_BACK_URL=#callBackUrl#
	@}
	@if(!isEmpty(downCallBackUrl)){
	 and DOWN_CALL_BACK_URL=#downCallBackUrl#
	@}
	@if(!isEmpty(tradeType)){
	 and TRADE_TYPE=#tradeType#
	@}
	@if(!isEmpty(bizType)){
	 and BIZ_TYPE=#bizType#
	@}
	@if(!isEmpty(resCode)){
	 and RES_CODE=#resCode#
	@}
	@if(!isEmpty(resMsg)){
	 and RES_MSG=#resMsg#
	@}
	@if(!isEmpty(payOrderNo)){
	 and PAY_ORDER_NO=#payOrderNo#
	@}
	@if(!isEmpty(codeurl)){
	 and CODEURL=#codeurl#
	@}
	@if(!isEmpty(bankOrder)){
	 and BANK_ORDER=#bankOrder#
	@}
	@if(!isEmpty(subAppid)){
	 and SUB_APPID=#subAppid#
	@}
	@if(!isEmpty(subOpenid)){
	 and SUB_OPENID=#subOpenid#
	@}
	@if(!isEmpty(finishTime)){
	 and FINISH_TIME=#finishTime#
	@}
	@if(!isEmpty(timeEnd)){
	 and TIME_END=#timeEnd#
	@}
	@if(!isEmpty(orderStatus)){
	 and ORDER_STATUS=#orderStatus#
	@}
	@if(!isEmpty(settleStatus)){
	 and SETTLE_STATUS=#settleStatus#
	@}
	@if(!isEmpty(pageBackUrl)){
     and PAGE_BACK_URL=#pageBackUrl#
    @}
	
sumTradeAmount
===

select sum(TRADE_AMOUNT) from TRADE_DATA_T where  MERCHANT_NO=#merNum# and (ORDER_STATUS ='success'
 ) and  to_days(TIME_END) = to_days(now())  
 
staticsTradeByChannel
===
select ci.*,
(select sum(td.trade_amount) from TRADE_DATA_T td,MER_INFO_T mi where td.ORDER_STATUS='success' 
@if(!isEmpty(staticsDate)){
and DATE_FORMAT(td.TIME_END,'%Y-%m-%d')=#staticsDate# 
@}
@if(!isEmpty(bizType)){
and td.BIZ_TYPE=#bizType# 
@}
and td.MERCHANT_NO=mi.MERC_NUM and mi.SETTLE_WAY=#settleWay#
and FIND_IN_SET   (td.channel_code,(select code from CHANNEL_INFO_T where FIND_IN_SET(id,`getChannelChildList`(ci.id))))) as totalTradeAmount,
(select count(td.id) from TRADE_DATA_T td,MER_INFO_T mi where td.ORDER_STATUS='success' 
@if(!isEmpty(staticsDate)){
and DATE_FORMAT(td.TIME_END,'%Y-%m-%d')=#staticsDate# 
@}
@if(!isEmpty(bizType)){
and td.BIZ_TYPE=#bizType#
@}
and td.MERCHANT_NO=mi.MERC_NUM and mi.SETTLE_WAY=#settleWay#
and  FIND_IN_SET (td.channel_code,(select code from CHANNEL_INFO_T where FIND_IN_SET(id,`getChannelChildList`(ci.id))))) as totalTradeNum 
from CHANNEL_INFO_T ci 

staticsTradeByMerInfo
===
select * from TRADE_DATA_T where 
FIND_IN_SET (channel_code,(select code from CHANNEL_INFO_T where FIND_IN_SET(id,`getChannelChildList`(#channelId#)))) 
and order_status='success' and DATE_FORMAT(TIME_END,'%Y-%m-%d')=#staticsDate#

selectByMerMumAndOrderStatusAndEDate
===
select * from TRADE_DATA_T where MERCHANT_NO=#merNum# 
@if(!isEmpty(orderStatus)){
and ORDER_STATUS=#orderStatus# 
@}
and to_days(TIME_END) = to_days(#eDate#)

sumTradeAmountByDate
===

select sum(TRADE_AMOUNT) from TRADE_DATA_T where  MERCHANT_NO=#merNum# and (ORDER_STATUS ='success'
 ) and  to_days(TIME_END) = to_days(#date#)  
 
 
selectTradeForChannelByInDateAndStatus
===
select 
@pageTag(){
#use("cols")# ,(select merc_Name from MER_INFO_T where MERC_NUM=MERCHANT_NO)as merName
@}
from TRADE_DATA_T where 
 FIND_IN_SET (channel_code,(select code from CHANNEL_INFO_T where FIND_IN_SET(id,`getChannelChildList`(#channelId#)))) 
@if(!isEmpty(status)){
    and ORDER_STATUS=#status# 
@}
@if(!isEmpty(eDate)){
and DATE_FORMAT(CREATED_TIME,'%Y-%m-%d')<=#eDate# 
@}
@if(!isEmpty(sDate)){
and DATE_FORMAT(CREATED_TIME,'%Y-%m-%d')>=#sDate#
@}

order by CREATED_TIME desc
 
