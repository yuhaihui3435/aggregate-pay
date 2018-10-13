sample
===
* 注释

	select #use("cols")# from PRODUCT_T  where  #use("condition")#

cols
===
	ID,INDUSTRY_CODE,PRODUCT_NAME,MIN_PRICE,MAX_PRICE,CHANNEL_CODE

updateSample
===
	
	ID=#id#,INDUSTRY_CODE=#industryCode#,PRODUCT_NAME=#productName#,MIN_PRICE=#minPrice#,MAX_PRICE=#maxPrice#,CHANNEL_CODE=#channelCode#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and ID=#id#
	@}
	@if(!isEmpty(industryCode)){
	 and INDUSTRY_CODE=#industryCode#
	@}
	@if(!isEmpty(productName)){
	 and PRODUCT_NAME=#productName#
	@}
	@if(!isEmpty(minPrice)){
	 and MIN_PRICE=#minPrice#
	@}
	@if(!isEmpty(maxPrice)){
	 and MAX_PRICE=#maxPrice#
	@}
	@if(!isEmpty(channelCode)){
	 and CHANNEL_CODE=#channelCode#
	@}
	@if(!isEmpty(price)){
     and MAX_PRICE>#price#  and MIN_PRICE<=#price#
    @}
	

	