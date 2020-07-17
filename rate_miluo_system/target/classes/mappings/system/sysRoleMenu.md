sample
===
* 注释

	select #use("cols")# from sys_role_menu  where  #use("condition")#

cols
===
	id,role_id,menu_id

updateSample
===
	
	id=#id#,role_id=#roleId#,menu_id=#menuId#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(roleId)){
	 and role_id=#roleId#
	@}
	@if(!isEmpty(menuId)){
	 and menu_id=#menuId#
	@}
	
listMenuIdByRoleId
===

	select menu_id from sys_role_menu
		where role_id = #roleId#
		
deleteByRoleId
===

	delete from sys_role_menu where role_id = #roleId#