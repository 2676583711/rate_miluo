listRoleId
===
	select role_id from sys_user_role where user_id=#userId#

listRoleId
===
* 获取角色列表

	select role_id from sys_user_role where user_id=#userId#

sample
===
* 注释

	select #use("cols")# from sys_user_role  where  #use("condition")#

cols
===
	id,user_id,role_id

updateSample
===
	
	id=#id#,user_id=#userId#,role_id=#roleId#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(userId)){
	 and user_id=#userId#
	@}
	@if(!isEmpty(roleId)){
	 and role_id=#roleId#
	@}
	
removeByUserId
===

	delete from sys_user_role where #use("condition")#

updateByUserId
===
    update sys_user_role set role_id = #roleId#
    where user_id = #userId#

findRoleIdByUserId
===
    select role_id from sys_user_role where user_id = #userId#
	
batchSave
===
	INSERT INTO sys_user_role
		(
			user_id,
			role_id
		) values(
		@for(userRole in userRoles){
    		#userRole.userId#,#userRole.roleId#  #text(userRoleLP.last?"":"," )#
		@}
		)

findUserIdsByRoleId
===
    select distinct(user_id) from sys_user_role 
    where role_id = #roleId#