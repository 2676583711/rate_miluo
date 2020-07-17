listMenuByUserId
===
	select distinct m.menu_id , parent_id, name, url,perms,type,icon,order_num,gmt_create, gmt_modified
	from sys_menu m left join sys_role_menu rm on m.menu_id = rm.menu_id left join sys_user_role ur on rm.role_id =ur.role_id 
	where ur.user_id = #id# and m.type in(0,1) order by m.order_num

listUserPerms
===

	select distinct m.perms
		from sys_menu m left join
		sys_role_menu rm on m.menu_id = rm.menu_id
		left join sys_user_role ur
		on rm.role_id = ur.role_id where ur.user_id=#userId#

getMenuByRoleId
===

    SELECT sm.* FROM sys_menu sm LEFT JOIN sys_role_menu rm
    ON sm.menu_id = rm.`menu_id`
    WHERE rm.`role_id`=#roleId#
    AND sm.`type` IN (0,1)
    ORDER BY sm.`order_num`
    
sample
===
* 注释

	select #use("cols")# from sys_menu  where  #use("condition")#

cols
===
	menu_id,parent_id,name,url,perms,type,icon,order_num,gmt_create,gmt_modified

updateSample
===
	
	menu_id=#menuId#,parent_id=#parentId#,name=#name#,url=#url#,perms=#perms#,type=#type#,icon=#icon#,order_num=#orderNum#,gmt_create=#gmtCreate#,gmt_modified=#gmtModified#

condition
===

	1 = 1  
	@if(!isEmpty(menuId)){
	 and menu_id=#menuId#
	@}
	@if(!isEmpty(parentId)){
	 and parent_id=#parentId#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(url)){
	 and url=#url#
	@}
	@if(!isEmpty(perms)){
	 and perms=#perms#
	@}
	@if(!isEmpty(type)){
	 and type=#type#
	@}
	@if(!isEmpty(icon)){
	 and icon=#icon#
	@}
	@if(!isEmpty(orderNum)){
	 and order_num=#orderNum#
	@}
	@if(!isEmpty(gmtCreate)){
	 and gmt_create=#gmtCreate#
	@}
	@if(!isEmpty(gmtModified)){
	 and gmt_modified=#gmtModified#
	@}
	
	