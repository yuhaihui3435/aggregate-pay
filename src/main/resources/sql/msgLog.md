sample
===
* 注释

	select #use("cols")# from MSG_LOG_T  where  #use("condition")#

cols
===
	REVISION,CREATED_BY,CREATED_TIME,UPDATED_BY,UPDATED_TIME,DELETE_TIME,DELETE_BY,ID,API_TYPE,DOWN_REQ_JSON,DOWN_RESP_JSON,UP_REQ_JSON,UP_RESP_JSON,DOWN_RES_CODE,DOWN_RES_MSG,UP_RES_CODE,UP_RES_MSG,MERC_CODE,TRADE_CODE

updateSample
===
	
	REVISION=#revision#,CREATED_BY=#createdBy#,CREATED_TIME=#createdTime#,UPDATED_BY=#updatedBy#,UPDATED_TIME=#updatedTime#,DELETE_TIME=#deleteTime#,DELETE_BY=#deleteBy#,ID=#id#,API_TYPE=#apiType#,DOWN_REQ_JSON=#downReqJson#,DOWN_RESP_JSON=#downRespJson#,UP_REQ_JSON=#upReqJson#,UP_RESP_JSON=#upRespJson#,DOWN_RES_CODE=#downResCode#,DOWN_RES_MSG=#downResMsg#,UP_RES_CODE=#upResCode#,UP_RES_MSG=#upResMsg#,MERC_CODE=#mercCode#,TRADE_CODE=#tradeCode#

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
	@if(!isEmpty(apiType)){
	 and API_TYPE=#apiType#
	@}
	@if(!isEmpty(downReqJson)){
	 and DOWN_REQ_JSON=#downReqJson#
	@}
	@if(!isEmpty(downRespJson)){
	 and DOWN_RESP_JSON=#downRespJson#
	@}
	@if(!isEmpty(upReqJson)){
	 and UP_REQ_JSON=#upReqJson#
	@}
	@if(!isEmpty(upRespJson)){
	 and UP_RESP_JSON=#upRespJson#
	@}
	@if(!isEmpty(downResCode)){
	 and DOWN_RES_CODE=#downResCode#
	@}
	@if(!isEmpty(downResMsg)){
	 and DOWN_RES_MSG=#downResMsg#
	@}
	@if(!isEmpty(upResCode)){
	 and UP_RES_CODE=#upResCode#
	@}
	@if(!isEmpty(upResMsg)){
	 and UP_RES_MSG=#upResMsg#
	@}
	@if(!isEmpty(mercCode)){
	 and MERC_CODE=#mercCode#
	@}
	@if(!isEmpty(tradeCode)){
	 and TRADE_CODE=#tradeCode#
	@}
	
	