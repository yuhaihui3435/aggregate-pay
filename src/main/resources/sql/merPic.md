sample
===
* 注释

	select #use("cols")# from MER_PIC_T  where  #use("condition")#

cols
===
	REVISION,CREATED_BY,CREATED_TIME,UPDATED_BY,UPDATED_TIME,DELETE_TIME,DELETE_BY,ID,AP_CODE,PIC_ID,PIC_PATH,PIC_TYPE

updateSample
===
	
	REVISION=#revision#,CREATED_BY=#createdBy#,CREATED_TIME=#createdTime#,UPDATED_BY=#updatedBy#,UPDATED_TIME=#updatedTime#,DELETE_TIME=#deleteTime#,DELETE_BY=#deleteBy#,ID=#id#,AP_CODE=#apCode#,PIC_ID=#picId#,PIC_PATH=#picPath#,PIC_TYPE=#picType#

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
	@if(!isEmpty(apCode)){
	 and AP_CODE=#apCode#
	@}
	@if(!isEmpty(picId)){
	 and PIC_ID=#picId#
	@}
	@if(!isEmpty(picPath)){
	 and PIC_PATH=#picPath#
	@}
	@if(!isEmpty(picType)){
	 and PIC_TYPE=#picType#
	@}
	
	