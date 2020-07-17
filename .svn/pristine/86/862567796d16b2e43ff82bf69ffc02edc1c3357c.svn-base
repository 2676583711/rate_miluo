sample
===
* 注释

	select #use("cols")# from api_verification  where  #use("condition")#

cols
===
	id,code,start_time,update_time,user_id,type

updateSample
===
	
	id=#id#,code=#code#,start_time=#startTime#,update_time=#updateTime#,user_id=#userId#,type=#type#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(code)){
	 and code=#code#
	@}
	@if(!isEmpty(startTime)){
	 and start_time=#startTime#
	@}
	@if(!isEmpty(updateTime)){
	 and update_time=#updateTime#
	@}
	@if(!isEmpty(userId)){
	 and user_id=#userId#
	@}
	@if(!isEmpty(type)){
	 and type=#type#
	@}
	
selectByUserId
===
    select * from api_verification where type=0 and user_id=#userId# 
    
updateType
===
    update api_verification set type=1,update_time=now() where id=#userId#
    
appAutoCode
===
    select * from api_verification where type=0 and code=#code# 
	
	