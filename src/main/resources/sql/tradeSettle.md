sample
===
* 注释

	select #use("cols")# from trade_settle  where  #use("condition")#

cols
===
	id,channelId,channelSubId,clientId,tradeNo,tradeSettleAmount,carryAmount,carrySubAmount,settleSubRate,settleClientRate,tradeBaseAmount,cat,mat,dat,remark

updateSample
===
	
	id=#id#,channelId=#channelid#,channelSubId=#channelsubid#,clientId=#clientid#,tradeNo=#tradeno#,tradeSettleAmount=#tradesettleamount#,carryAmount=#carryamount#,carrySubAmount=#carrysubamount#,settleSubRate=#settlesubrate#,settleClientRate=#settleclientrate#,tradeBaseAmount=#tradebaseamount#,cat=#cat#,mat=#mat#,dat=#dat#,remark=#remark#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(channelid)){
	 and channelId=#channelid#
	@}
	@if(!isEmpty(channelsubid)){
	 and channelSubId=#channelsubid#
	@}
	@if(!isEmpty(clientid)){
	 and clientId=#clientid#
	@}
	@if(!isEmpty(tradeno)){
	 and tradeNo=#tradeno#
	@}
	@if(!isEmpty(tradesettleamount)){
	 and tradeSettleAmount=#tradesettleamount#
	@}
	@if(!isEmpty(carryamount)){
	 and carryAmount=#carryamount#
	@}
	@if(!isEmpty(carrysubamount)){
	 and carrySubAmount=#carrysubamount#
	@}
	@if(!isEmpty(settlesubrate)){
	 and settleSubRate=#settlesubrate#
	@}
	@if(!isEmpty(settleclientrate)){
	 and settleClientRate=#settleclientrate#
	@}
	@if(!isEmpty(tradebaseamount)){
	 and tradeBaseAmount=#tradebaseamount#
	@}
	@if(!isEmpty(cat)){
	 and cat=#cat#
	@}
	@if(!isEmpty(mat)){
	 and mat=#mat#
	@}
	@if(!isEmpty(dat)){
	 and dat=#dat#
	@}
	@if(!isEmpty(remark)){
	 and remark=#remark#
	@}
	
	