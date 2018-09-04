sample
===
* 注释

	select #use("cols")# from MER_INFO_T  where  #use("condition")#

cols
===
	REVISION,CREATED_BY,CREATED_TIME,UPDATED_BY,UPDATED_TIME,DELETE_TIME,DELETE_BY,ID,MERC_TYPE,CUSTOM_MCC_TYPE,MERC_NUM,MERC_NAME,PROV_CODE,CITY_CODE,AREA_CODE,LEGAL_PERSON,LEGAL_PHONE,LEGAL_ID_CARD_NUM,BUS_LICENSE_NO,LEGAL_ID_CARD_VALIDITY_PEROID,BUS_LICENSE_VALIDITY_PEROID,LINK_PERSON,LINK_PHONE,MERC_SHORT_NAME,ADDR_DETAIL,INCOME_TYPE,RATE_CODE,CHANNEL_ID,RATE,STATUS,DATA_STATUS,SETTLE_WAY,ACC_TYPE,BANK_CODE,ACC_NAME,BANK_PROV_CODE,BANK_CITY_CODE,ACC_NUM,ID_CARD_NUM,ID_CARD_VALIDITY_PEROID,PHONE,BANK_NAME_BRANCH,CARD_TYPE,LICENSE_ID,PIC_PEOBLE_ID,BANK_CARD_ID,CARD_ID,BACK_CARD_ID,POWER_ID,PROTOCOL_PHOTO_ID,MAIN_PHOTO_ID,ORG_PHOTO_ID,PRODUCT,EMAIL,AP_CODE

updateSample
===
	
	REVISION=#revision#,CREATED_BY=#createdBy#,CREATED_TIME=#createdTime#,UPDATED_BY=#updatedBy#,UPDATED_TIME=#updatedTime#,DELETE_TIME=#deleteTime#,DELETE_BY=#deleteBy#,ID=#id#,MERC_TYPE=#mercType#,CUSTOM_MCC_TYPE=#customMccType#,MERC_NUM=#mercNum#,MERC_NAME=#mercName#,PROV_CODE=#provCode#,CITY_CODE=#cityCode#,AREA_CODE=#areaCode#,LEGAL_PERSON=#legalPerson#,LEGAL_PHONE=#legalPhone#,LEGAL_ID_CARD_NUM=#legalIdCardNum#,BUS_LICENSE_NO=#busLicenseNo#,LEGAL_ID_CARD_VALIDITY_PEROID=#legalIdCardValidityPeroid#,BUS_LICENSE_VALIDITY_PEROID=#busLicenseValidityPeroid#,LINK_PERSON=#linkPerson#,LINK_PHONE=#linkPhone#,MERC_SHORT_NAME=#mercShortName#,ADDR_DETAIL=#addrDetail#,INCOME_TYPE=#incomeType#,RATE_CODE=#rateCode#,CHANNEL_ID=#channelId#,RATE=#rate#,STATUS=#status#,DATA_STATUS=#dataStatus#,SETTLE_WAY=#settleWay#,ACC_TYPE=#accType#,BANK_CODE=#bankCode#,ACC_NAME=#accName#,BANK_PROV_CODE=#bankProvCode#,BANK_CITY_CODE=#bankCityCode#,ACC_NUM=#accNum#,ID_CARD_NUM=#idCardNum#,ID_CARD_VALIDITY_PEROID=#idCardValidityPeroid#,PHONE=#phone#,BANK_NAME_BRANCH=#bankNameBranch#,CARD_TYPE=#cardType#,LICENSE_ID=#licenseId#,PIC_PEOBLE_ID=#picPeobleId#,BANK_CARD_ID=#bankCardId#,CARD_ID=#cardId#,BACK_CARD_ID=#backCardId#,POWER_ID=#powerId#,PROTOCOL_PHOTO_ID=#protocolPhotoId#,MAIN_PHOTO_ID=#mainPhotoId#,ORG_PHOTO_ID=#orgPhotoId#,PRODUCT=#product#,EMAIL=#email#,AP_CODE=#apCode#

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
	@if(!isEmpty(mercType)){
	 and MERC_TYPE=#mercType#
	@}
	@if(!isEmpty(customMccType)){
	 and CUSTOM_MCC_TYPE=#customMccType#
	@}
	@if(!isEmpty(mercNum)){
	 and MERC_NUM=#mercNum#
	@}
	@if(!isEmpty(mercName)){
	 and MERC_NAME=#mercName#
	@}
	@if(!isEmpty(provCode)){
	 and PROV_CODE=#provCode#
	@}
	@if(!isEmpty(cityCode)){
	 and CITY_CODE=#cityCode#
	@}
	@if(!isEmpty(areaCode)){
	 and AREA_CODE=#areaCode#
	@}
	@if(!isEmpty(legalPerson)){
	 and LEGAL_PERSON=#legalPerson#
	@}
	@if(!isEmpty(legalPhone)){
	 and LEGAL_PHONE=#legalPhone#
	@}
	@if(!isEmpty(legalIdCardNum)){
	 and LEGAL_ID_CARD_NUM=#legalIdCardNum#
	@}
	@if(!isEmpty(busLicenseNo)){
	 and BUS_LICENSE_NO=#busLicenseNo#
	@}
	@if(!isEmpty(legalIdCardValidityPeroid)){
	 and LEGAL_ID_CARD_VALIDITY_PEROID=#legalIdCardValidityPeroid#
	@}
	@if(!isEmpty(busLicenseValidityPeroid)){
	 and BUS_LICENSE_VALIDITY_PEROID=#busLicenseValidityPeroid#
	@}
	@if(!isEmpty(linkPerson)){
	 and LINK_PERSON=#linkPerson#
	@}
	@if(!isEmpty(linkPhone)){
	 and LINK_PHONE=#linkPhone#
	@}
	@if(!isEmpty(mercShortName)){
	 and MERC_SHORT_NAME=#mercShortName#
	@}
	@if(!isEmpty(addrDetail)){
	 and ADDR_DETAIL=#addrDetail#
	@}
	@if(!isEmpty(incomeType)){
	 and INCOME_TYPE=#incomeType#
	@}
	@if(!isEmpty(rateCode)){
	 and RATE_CODE=#rateCode#
	@}
	@if(!isEmpty(channelId)){
	 and CHANNEL_ID=#channelId#
	@}
	@if(!isEmpty(rate)){
	 and RATE=#rate#
	@}
	@if(!isEmpty(status)){
	 and STATUS=#status#
	@}
	@if(!isEmpty(dataStatus)){
	 and DATA_STATUS=#dataStatus#
	@}
	@if(!isEmpty(settleWay)){
	 and SETTLE_WAY=#settleWay#
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
	@if(!isEmpty(licenseId)){
	 and LICENSE_ID=#licenseId#
	@}
	@if(!isEmpty(picPeobleId)){
	 and PIC_PEOBLE_ID=#picPeobleId#
	@}
	@if(!isEmpty(bankCardId)){
	 and BANK_CARD_ID=#bankCardId#
	@}
	@if(!isEmpty(cardId)){
	 and CARD_ID=#cardId#
	@}
	@if(!isEmpty(backCardId)){
	 and BACK_CARD_ID=#backCardId#
	@}
	@if(!isEmpty(powerId)){
	 and POWER_ID=#powerId#
	@}
	@if(!isEmpty(protocolPhotoId)){
	 and PROTOCOL_PHOTO_ID=#protocolPhotoId#
	@}
	@if(!isEmpty(mainPhotoId)){
	 and MAIN_PHOTO_ID=#mainPhotoId#
	@}
	@if(!isEmpty(orgPhotoId)){
	 and ORG_PHOTO_ID=#orgPhotoId#
	@}
	@if(!isEmpty(product)){
	 and PRODUCT=#product#
	@}
	@if(!isEmpty(email)){
	 and EMAIL=#email#
	@}
	@if(!isEmpty(apCode)){
	 and AP_CODE=#apCode#
	@}
	
	