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
	
list
===

		select #use("cols")#
    from sys_log where 1 = 1
    
     @if(!isEmpty(username) && username != ''){
	 and username = #username#
	 @}    
     @if(!isEmpty(operation) && operation != ''){
	 and operation like #'%'+operation+'%'#
	 @}    
	 and user_id in (
		SELECT u.user_id FROM `sys_user` u WHERE u.dept_id in (
			SELECT dept_id FROM sys_dept WHERE 1=1 
			@if(!isEmpty(parentId) && parentId != ''){
	         and parent_id = #parentId#
	        @} 		 
		)	 
	 )
    order by gmt_create desc
    limit #offset#,#limit#
    
count
===

	select count(id) from sys_log where 1=1
	
	 @if(!isEmpty(username) && username != ''){
	 and username = #username#
	 @}    
    @if(!isEmpty(operation) && operation != ''){
	 and operation like #'%'+operation+'%'#
	 @} 
	 and user_id in (
		SELECT u.user_id FROM `sys_user` u WHERE u.dept_id in (
			SELECT dept_id FROM sys_dept WHERE 1=1 
			@if(!isEmpty(parentId) && parentId != ''){
	         and parent_id = #parentId#
	        @} 	
		)	 
	 )
	
batchRemove
===

	delete from sys_log where id in (#join(ids)#)