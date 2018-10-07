sample
===
* 注释

	select #use("cols")# from CHANNEL_BROKERAGE_T  where  #use("condition")#

cols
===
	REVISION,CREATED_BY,CREATED_TIME,UPDATED_BY,UPDATED_TIME,DELETE_TIME,DELETE_BY,ID,CHANNEL_CODE,BROKERAGE_DAY,BROKERAGE_AMOUNT,SETTLE_TIME,SETTLE_AMOUNT,BANK_PAY_ORDER,PAY_PIC,PAY_TIME

updateSample
===
	
	REVISION=#revision#,CREATED_BY=#createdBy#,CREATED_TIME=#createdTime#,UPDATED_BY=#updatedBy#,UPDATED_TIME=#updatedTime#,DELETE_TIME=#deleteTime#,DELETE_BY=#deleteBy#,ID=#id#,CHANNEL_CODE=#channelCode#,BROKERAGE_DAY=#brokerageDay#,BROKERAGE_AMOUNT=#brokerageAmount#,SETTLE_TIME=#settleTime#,SETTLE_AMOUNT=#settleAmount#,BANK_PAY_ORDER=#bankPayOrder#,PAY_PIC=#payPic#,PAY_TIME=#payTime#

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
	@if(!isEmpty(brokerageDay)){
	 and BROKERAGE_DAY=#brokerageDay#
	@}
	@if(!isEmpty(brokerageAmount)){
	 and BROKERAGE_AMOUNT=#brokerageAmount#
	@}
	@if(!isEmpty(settleTime)){
	 and SETTLE_TIME=#settleTime#
	@}
	@if(!isEmpty(settleAmount)){
	 and SETTLE_AMOUNT=#settleAmount#
	@}
	@if(!isEmpty(bankPayOrder)){
	 and BANK_PAY_ORDER=#bankPayOrder#
	@}
	@if(!isEmpty(payPic)){
	 and PAY_PIC=#payPic#
	@}
	@if(!isEmpty(payTime)){
	 and PAY_TIME=#payTime#
	@}
	
	