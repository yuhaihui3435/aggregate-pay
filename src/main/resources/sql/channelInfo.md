sample
===
* 注释

	select #use("cols")# from CHANNEL_INFO_T  where  #use("condition")#

cols
===
	REVISION,CREATED_BY,CREATED_TIME,UPDATED_BY,UPDATED_TIME,DELETE_TIME,DELETE_BY,ID,CODE,NAME,TEL,EMAIL,BANK_NAME,BANK_ACCOUNT,BANK_ACCOUNT_NAME,BANK_CODE,ACC_TYPE,BANK_TEL,BAKN_ID_CARD,ID_CARD_VALIDITY_PEROID,RATE,RATE_CODE,CEILING_OF_DAY,CEILING_OF_SINGLE,STATUS,PID

updateSample
===
	
	REVISION=#revision#,CREATED_BY=#createdBy#,CREATED_TIME=#createdTime#,UPDATED_BY=#updatedBy#,UPDATED_TIME=#updatedTime#,DELETE_TIME=#deleteTime#,DELETE_BY=#deleteBy#,ID=#id#,CODE=#code#,NAME=#name#,TEL=#tel#,EMAIL=#email#,BANK_NAME=#bankName#,BANK_ACCOUNT=#bankAccount#,BANK_ACCOUNT_NAME=#bankAccountName#,BANK_CODE=#bankCode#,ACC_TYPE=#accType#,BANK_TEL=#bankTel#,BAKN_ID_CARD=#baknIdCard#,ID_CARD_VALIDITY_PEROID=#idCardValidityPeroid#,RATE=#rate#,RATE_CODE=#rateCode#,CEILING_OF_DAY=#ceilingOfDay#,CEILING_OF_SINGLE=#ceilingOfSingle#,STATUS=#status#,PID=#pid#

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
	@if(!isEmpty(code)){
	 and CODE=#code#
	@}
	@if(!isEmpty(name)){
	 and NAME=#name#
	@}
	@if(!isEmpty(tel)){
	 and TEL=#tel#
	@}
	@if(!isEmpty(email)){
	 and EMAIL=#email#
	@}
	@if(!isEmpty(bankName)){
	 and BANK_NAME=#bankName#
	@}
	@if(!isEmpty(bankAccount)){
	 and BANK_ACCOUNT=#bankAccount#
	@}
	@if(!isEmpty(bankAccountName)){
	 and BANK_ACCOUNT_NAME=#bankAccountName#
	@}
	@if(!isEmpty(bankCode)){
	 and BANK_CODE=#bankCode#
	@}
	@if(!isEmpty(accType)){
	 and ACC_TYPE=#accType#
	@}
	@if(!isEmpty(bankTel)){
	 and BANK_TEL=#bankTel#
	@}
	@if(!isEmpty(baknIdCard)){
	 and BAKN_ID_CARD=#baknIdCard#
	@}
	@if(!isEmpty(idCardValidityPeroid)){
	 and ID_CARD_VALIDITY_PEROID=#idCardValidityPeroid#
	@}
	@if(!isEmpty(rate)){
	 and RATE=#rate#
	@}
	@if(!isEmpty(rateCode)){
	 and RATE_CODE=#rateCode#
	@}
	@if(!isEmpty(ceilingOfDay)){
	 and CEILING_OF_DAY=#ceilingOfDay#
	@}
	@if(!isEmpty(ceilingOfSingle)){
	 and CEILING_OF_SINGLE=#ceilingOfSingle#
	@}
	@if(!isEmpty(status)){
	 and STATUS=#status#
	@}
	@if(!isEmpty(pid)){
	 and PID=#pid#
	@}
	
	