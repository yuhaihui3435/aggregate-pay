sample
===
* 注释

	select #use("cols")# from AP_CODE_T  where  #use("condition")#

cols
===
	AP_CODE,AP_KEY,CHANNEL_CODE

updateSample
===
	
	AP_CODE=#apCode#,AP_KEY=#apKey#,CHANNEL_CODE=#channelCode#

condition
===

	1 = 1  
	@if(!isEmpty(apCode)){
	 and AP_CODE=#apCode#
	@}
	@if(!isEmpty(apKey)){
	 and AP_KEY=#apKey#
	@}
	@if(!isEmpty(channelCode)){
	 and CHANNEL_CODE=#channelCode#
	@}
	
	