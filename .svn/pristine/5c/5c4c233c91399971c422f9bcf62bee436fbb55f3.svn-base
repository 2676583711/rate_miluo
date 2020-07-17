sample
===
* 注释

	select #use("cols")# from sys_log  where  #use("condition")#

cols
===
	id,user_id,username,operation,time,method,params,ip,gmt_create

updateSample
===
	
	id=#id#,user_id=#userId#,username=#username#,operation=#operation#,time=#time#,method=#method#,params=#params#,ip=#ip#,gmt_create=#gmtCreate#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(userId)){
	 and user_id=#userId#
	@}
	@if(!isEmpty(username)){
	 and username=#username#
	@}
	@if(!isEmpty(operation)){
	 and operation=#operation#
	@}
	@if(!isEmpty(time)){
	 and time=#time#
	@}
	@if(!isEmpty(method)){
	 and method=#method#
	@}
	@if(!isEmpty(params)){
	 and params=#params#
	@}
	@if(!isEmpty(ip)){
	 and ip=#ip#
	@}
	@if(!isEmpty(gmtCreate)){
	 and gmt_create=#gmtCreate#
	@}
	
	