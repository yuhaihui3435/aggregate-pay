sample
===
* 注释

	select #use("cols")# from DICT_T  where  #use("condition")#

cols
===
	REVISION,CREATED_BY,CREATED_TIME,UPDATED_BY,UPDATED_TIME,DELETE_TIME,DELETE_BY,ID,DICT_NAME,DICT_CODE,DICT_ITEM_NAME,DICT_ITEM_CODE,DICT_ITEM_VAL

updateSample
===
	
	REVISION=#revision#,CREATED_BY=#createdBy#,CREATED_TIME=#createdTime#,UPDATED_BY=#updatedBy#,UPDATED_TIME=#updatedTime#,DELETE_TIME=#deleteTime#,DELETE_BY=#deleteBy#,ID=#id#,DICT_NAME=#dictName#,DICT_CODE=#dictCode#,DICT_ITEM_NAME=#dictItemName#,DICT_ITEM_CODE=#dictItemCode#,DICT_ITEM_VAL=#dictItemVal#

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
	@if(!isEmpty(dictName)){
	 and DICT_NAME=#dictName#
	@}
	@if(!isEmpty(dictCode)){
	 and DICT_CODE=#dictCode#
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
	
	