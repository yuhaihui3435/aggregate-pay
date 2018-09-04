sample
===
* 注释

	select #use("cols")# from MER_BANK_INFO_T  where  #use("condition")#

cols
===
	REVISION,CREATED_BY,CREATED_TIME,UPDATED_BY,UPDATED_TIME,DELETE_TIME,DELETE_BY,ID,MER_ID,ACC_TYPE,BANK_CODE,ACC_NAME,BANK_PROV_CODE,BANK_CITY_CODE,ACC_NUM,ID_CARD_NUM,ID_CARD_VALIDITY_PEROID,PHONE,BANK_NAME_BRANCH,CARD_TYPE

updateSample
===
	
	REVISION=#revision#,CREATED_BY=#createdBy#,CREATED_TIME=#createdTime#,UPDATED_BY=#updatedBy#,UPDATED_TIME=#updatedTime#,DELETE_TIME=#deleteTime#,DELETE_BY=#deleteBy#,ID=#id#,MER_ID=#merId#,ACC_TYPE=#accType#,BANK_CODE=#bankCode#,ACC_NAME=#accName#,BANK_PROV_CODE=#bankProvCode#,BANK_CITY_CODE=#bankCityCode#,ACC_NUM=#accNum#,ID_CARD_NUM=#idCardNum#,ID_CARD_VALIDITY_PEROID=#idCardValidityPeroid#,PHONE=#phone#,BANK_NAME_BRANCH=#bankNameBranch#,CARD_TYPE=#cardType#

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
	@if(!isEmpty(merId)){
	 and MER_ID=#merId#
	@}
	@if(!isEmpty(accType)){
	 and ACC_TYPE=#accType#
	@}
	@if(!isEmpty(bankCode)){
	 and BANK_CODE=#bankCode#
	@}
	@if(!isEmpty(accName)){
	 and ACC_NAME=#accName#
	@}
	@if(!isEmpty(bankProvCode)){
	 and BANK_PROV_CODE=#bankProvCode#
	@}
	@if(!isEmpty(bankCityCode)){
	 and BANK_CITY_CODE=#bankCityCode#
	@}
	@if(!isEmpty(accNum)){
	 and ACC_NUM=#accNum#
	@}
	@if(!isEmpty(idCardNum)){
	 and ID_CARD_NUM=#idCardNum#
	@}
	@if(!isEmpty(idCardValidityPeroid)){
	 and ID_CARD_VALIDITY_PEROID=#idCardValidityPeroid#
	@}
	@if(!isEmpty(phone)){
	 and PHONE=#phone#
	@}
	@if(!isEmpty(bankNameBranch)){
	 and BANK_NAME_BRANCH=#bankNameBranch#
	@}
	@if(!isEmpty(cardType)){
	 and CARD_TYPE=#cardType#
	@}
	
	