sample
===
* 注释

	select #use("cols")# from channel_sub_info  where  #use("condition")#

cols
===
	id,channelId,channelSubCode,channelSubName,channelSubTel,channelSubEmail,status,t1RateCode,tsRateCode,t1Rate,tsRate,c_at,d_at

updateSample
===
	
	id=#id#,channelId=#channelid#,channelSubCode=#channelsubcode#,channelSubName=#channelsubname#,channelSubTel=#channelsubtel#,channelSubEmail=#channelsubemail#,status=#status#,t1RateCode=#t1ratecode#,tsRateCode=#tsratecode#,t1Rate=#t1rate#,tsRate=#tsrate#,c_at=#cAt#,d_at=#dAt#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(channelid)){
	 and channelId=#channelid#
	@}
	@if(!isEmpty(channelsubcode)){
	 and channelSubCode=#channelsubcode#
	@}
	@if(!isEmpty(channelsubname)){
	 and channelSubName=#channelsubname#
	@}
	@if(!isEmpty(channelsubtel)){
	 and channelSubTel=#channelsubtel#
	@}
	@if(!isEmpty(channelsubemail)){
	 and channelSubEmail=#channelsubemail#
	@}
	@if(!isEmpty(status)){
	 and status=#status#
	@}
	@if(!isEmpty(t1ratecode)){
	 and t1RateCode=#t1ratecode#
	@}
	@if(!isEmpty(tsratecode)){
	 and tsRateCode=#tsratecode#
	@}
	@if(!isEmpty(t1rate)){
	 and t1Rate=#t1rate#
	@}
	@if(!isEmpty(tsrate)){
	 and tsRate=#tsrate#
	@}
	@if(!isEmpty(cAt)){
	 and c_at=#cAt#
	@}
	@if(!isEmpty(dAt)){
	 and d_at=#dAt#
	@}
	
	