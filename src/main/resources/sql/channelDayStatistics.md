sample
===
* 注释

	select #use("cols")# from CHANNEL_DAY_STATISTICS_T  where  #use("condition")#

cols
===
	ID,CHANNEL_CODE,STATISTICS_DAY,TRADE_AMOUNT,TRADE_NUM,SETTLY_TYPE,PROFIT

updateSample
===
	
	ID=#id#,CHANNEL_CODE=#channelCode#,STATISTICS_DAY=#statisticsDay#,TRADE_AMOUNT=#tradeAmount#,TRADE_NUM=#tradeNum#,SETTLY_TYPE=#settlyType#,PROFIT=#profit#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and ID=#id#
	@}
	@if(!isEmpty(channelCode)){
	 and CHANNEL_CODE=#channelCode#
	@}
	@if(!isEmpty(statisticsDay)){
	 and STATISTICS_DAY=#statisticsDay#
	@}
	@if(!isEmpty(tradeAmount)){
	 and TRADE_AMOUNT=#tradeAmount#
	@}
	@if(!isEmpty(tradeNum)){
	 and TRADE_NUM=#tradeNum#
	@}
	@if(!isEmpty(settlyType)){
	 and SETTLY_TYPE=#settlyType#
	@}
	@if(!isEmpty(profit)){
	 and PROFIT=#profit#
	@}
	
	