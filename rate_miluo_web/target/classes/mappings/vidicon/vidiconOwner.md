
sample
===
* 注释

	select #use("cols")# from miluo_vidicon_owner  where  #use("condition")#

cols
===
	owner_id,owner_name,app_key,secret,access_token,token_update_date,create_date

updateSample
===
	
	owner_id=#ownerId#,owner_name=#ownerName#,app_key=#appKey#,secret=#secret#,access_token=#accessToken#,token_update_date=#tokenUpdateDate#,create_date=#createDate#

condition
===

	1 = 1  
	@if(!isEmpty(ownerId)){
	 and owner_id=#ownerId#
	@}
	@if(!isEmpty(ownerName)){
	 and owner_name=#ownerName#
	@}
	@if(!isEmpty(appKey)){
	 and app_key=#appKey#
	@}
	@if(!isEmpty(secret)){
	 and secret=#secret#
	@}
	@if(!isEmpty(accessToken)){
	 and access_token=#accessToken#
	@}
	@if(!isEmpty(tokenUpdateDate)){
	 and token_update_date=#tokenUpdateDate#
	@}
	@if(!isEmpty(createDate)){
	 and create_date=#createDate#
	@}
	
	