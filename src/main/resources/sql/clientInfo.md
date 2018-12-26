sample
===
* 注释

	select #use("cols")# from client_info_t  where  #use("condition")#

cols
===
	id,channel_id,channel_sub_id,client_code,client_name,client_tel,client_email,status,d_at,t1_rate,ts_rate

updateSample
===
	
	id=#id#,channel_id=#channelId#,channel_sub_id=#channelSubId#,client_code=#clientCode#,client_name=#clientName#,client_tel=#clientTel#,client_email=#clientEmail#,status=#status#,d_at=#dAt#,t1_rate=#t1Rate#,ts_rate=#tsRate#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(channelId)){
	 and channel_id=#channelId#
	@}
	@if(!isEmpty(channelSubId)){
	 and channel_sub_id=#channelSubId#
	@}
	@if(!isEmpty(clientCode)){
	 and client_code=#clientCode#
	@}
	@if(!isEmpty(clientName)){
	 and client_name=#clientName#
	@}
	@if(!isEmpty(clientTel)){
	 and client_tel=#clientTel#
	@}
	@if(!isEmpty(clientEmail)){
	 and client_email=#clientEmail#
	@}
	@if(!isEmpty(status)){
	 and status=#status#
	@}
	@if(!isEmpty(dAt)){
	 and d_at=#dAt#
	@}
	@if(!isEmpty(t1Rate)){
	 and t1_rate=#t1Rate#
	@}
	@if(!isEmpty(tsRate)){
	 and ts_rate=#tsRate#
	@}
	
	