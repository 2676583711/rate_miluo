sample
===
* 注释

	select #use("cols")# from sys_role  where  #use("condition")#

cols
===
	role_id,role_name,role_sign,remark,user_id_create,gmt_create,gmt_modified

updateSample
===
	
	role_id=#roleId#,role_name=#roleName#,role_sign=#roleSign#,remark=#remark#,user_id_create=#userIdCreate#,gmt_create=#gmtCreate#,gmt_modified=#gmtModified#

updateRoleId
===

	update sys_user_role set role_id = #roleId# where user_id = #userId#

findRoleList
===
    select role_id from sys_user_role where user_id=#userId#


condition
===

	1 = 1  
	@if(!isEmpty(roleId)){
	 and role_id=#roleId#
	@}
	@if(!isEmpty(roleName)){
	 and role_name=#roleName#
	@}
	@if(!isEmpty(roleSign)){
	 and role_sign=#roleSign#
	@}
	@if(!isEmpty(remark)){
	 and remark=#remark#
	@}
	@if(!isEmpty(userIdCreate)){
	 and user_id_create=#userIdCreate#
	@}
	@if(!isEmpty(gmtCreate)){
	 and gmt_create=#gmtCreate#
	@}
	@if(!isEmpty(gmtModified)){
	 and gmt_modified=#gmtModified#
	@}
	
	
getRoleByname
===
          select role_id from sys_role where role_name =#roleName#
	
	