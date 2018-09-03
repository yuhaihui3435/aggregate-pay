sample
===
* 注释

	select #use("cols")# from DICT_ITEM_T  where  #use("condition")#

cols
===
	ID,DICT_ITEM_NAME,DICT_ITEM_CODE,DICT_ITEM_VAL,DICT_ID

updateSample
===
	
	ID=#id#,DICT_ITEM_NAME=#dictItemName#,DICT_ITEM_CODE=#dictItemCode#,DICT_ITEM_VAL=#dictItemVal#,DICT_ID=#dictId#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and ID=#id#
	@}
	@if(!isEmpty(dictItemName)){
	 and DICT_ITEM_NAME=#dictItemName#
	@}
	@if(!isEmpty(dictItemCode)){
	 and DICT_ITEM_CODE=#dictItemCode#
	@}
	@if(!isEmpty(dictItemVal)){
	 and DICT_ITEM_VAL=#dictItemVal#
	@}
	@if(!isEmpty(dictId)){
	 and DICT_ID=#dictId#
	@}
	
	