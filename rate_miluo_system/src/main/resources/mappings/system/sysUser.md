sample
===
* 注释

	select #use("cols")# from sys_user  where  #use("condition")#

cols
===
	user_id,username,name,password,dept_id,email,mobile,status,user_id_create,gmt_create,gmt_modified,sex,birth,pic_id,live_address,hobby,province,city,district

updateSample
===
	
	user_id=#userId#,username=#username#,name=#name#,password=#password#,dept_id=#deptId#,email=#email#,mobile=#mobile#,status=#status#,user_id_create=#userIdCreate#,gmt_create=#gmtCreate#,gmt_modified=#gmtModified#,sex=#sex#,birth=#birth#,pic_id=#picId#,live_address=#liveAddress#,hobby=#hobby#,province=#province#,city=#city#,district=#district#

updateUserById
===
    update sys_user set name=#user.name#,username=#user.username#,mobile=#user.mobile#,dept_id=#user.deptId#,
    status=#user.status#,pw_id=#user.pwId# where user_id=#user.userId#
    
getCompanyNameByDeptId
===
	select c.pw_id,c.name from pollutant_company c where dept_id=#deptId#

condition
===

	1 = 1  
	@if(!isEmpty(userId)){
	 and user_id=#userId#
	@}
	@if(!isEmpty(username)){
	 and username=#username#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(password)){
	 and password=#password#
	@}
	@if(!isEmpty(deptId)){
	 and dept_id=#deptId#
	@}
	@if(!isEmpty(email)){
	 and email=#email#
	@}
	@if(!isEmpty(mobile)){
	 and mobile=#mobile#
	@}
	@if(!isEmpty(status)){
	 and status=#status#
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
	@if(!isEmpty(sex)){
	 and sex=#sex#
	@}
	@if(!isEmpty(birth)){
	 and birth=#birth#
	@}
	@if(!isEmpty(picId)){
	 and pic_id=#picId#
	@}
	@if(!isEmpty(liveAddress)){
	 and live_address=#liveAddress#
	@}
	@if(!isEmpty(hobby)){
	 and hobby=#hobby#
	@}
	@if(!isEmpty(province)){
	 and province=#province#
	@}
	@if(!isEmpty(city)){
	 and city=#city#
	@}
	@if(!isEmpty(district)){
	 and district=#district#
	@}

exitUser
===

	select username from sys_user  where  username = #params["username"]#
	
queryByCondtion
===

    SELECT 
    @pageTag(){
    u.*,d.name workName,r.role_name roleName 
    @}
    FROM sys_user u 
    LEFT JOIN sys_dept d ON u.dept_id = d.dept_id
	LEFT join sys_user_role ur on ur.user_id = u.user_id
	LEFT join sys_role r on r.role_id = ur.role_id
	WHERE u.dept_id in(
    select d2.dept_id from sys_dept d2 where 1=1
	@if(!isEmpty(delFlag)&&delFlag==2){
	  and d2.parent_id = (SELECT d3.parent_id FROM sys_dept d3 WHERE d3.dept_id = #deptId#) OR d2.dept_id = (SELECT d4.parent_id FROM sys_dept
	  d4 WHERE dept_id = #deptId#) or d2.del_flag =3 
	@}
	@if(!isEmpty(delFlag)&&delFlag==3){
	and find_in_set(d2.dept_id,getChild(#parentId#))
	@}
	)
	@if(!isEmpty(searchText) && searchText != ''){
	 and ( username like #'%'+searchText+'%'#
	 or u.name like #'%'+searchText+'%'# )
	 @}
	@if(!isEmpty(deptIds) && deptIds != ''){
	 and u.dept_id in (#join(deptIds)#)
	 @}
	 @if(!isEmpty(name)&&name!=''){
	 and name like #'%'+name+'%'#
	 @}
	 
listAll
===

    SELECT 
    u.*,d.name workName,r.role_name roleName ,u2.username username2
    FROM sys_user u 
    LEFT JOIN sys_user u2    ON u.mgr_id = u2.user_id
    LEFT JOIN sys_dept d ON u.dept_id = d.dept_id
	LEFT join sys_user_role ur on ur.user_id = u.user_id
	LEFT join sys_role r on r.role_id = ur.role_id
	WHERE u.dept_id in(
    select d2.dept_id from sys_dept d2 where 1=1
	@if(!isEmpty(delFlag)&&delFlag==2){
	  and d2.parent_id = (SELECT d3.parent_id FROM sys_dept d3 WHERE d3.dept_id = #deptId#) OR d2.dept_id = (SELECT d4.parent_id FROM sys_dept
	  d4 WHERE dept_id = #deptId#) or d2.del_flag =3 
	@}
	@if(!isEmpty(delFlag)&&delFlag==3){
	and find_in_set(d2.dept_id,getChild(#parentId#))
	@}
	)
	@if(!isEmpty(searchText) && searchText != ''){
	 and ( username like #'%'+searchText+'%'#
	 or u.name like #'%'+searchText+'%'# )
	 @}
	@if(!isEmpty(deptIds) && deptIds != ''){
	 and u.dept_id in (#join(deptIds)#)
	 @}
	 @if(!isEmpty(name)&&name!=''){
	 and name like #'%'+name+'%'#
	 @}
	 
	 	 
getCount
===
	
	SELECT COUNT(u.user_id) FROM sys_user u INNER JOIN sys_dept d ON u.dept_id = d.dept_id
	WHERE u.dept_id in (SELECT dept_id FROM sys_dept WHERE parent_id = 
	(SELECT parent_id FROM sys_dept WHERE dept_id = #deptId#)
	)
    @if(!isEmpty(searchText) && searchText != ''){
	 and ( username like #'%'+searchText+'%'#
	 or u.name like #'%'+searchText+'%'# )
	 @}
	@if(!isEmpty(deptIds) && deptIds != ''){
	 and u.dept_id in (#join(deptIds)#)
	 @}
	
queryByCondtion2
===
	SELECT u.*,d.name workName,r.role_name roleName FROM sys_user u 
	INNER JOIN sys_dept d ON u.dept_id = d.dept_id
	inner join sys_user_role ur on ur.user_id = u.user_id
	inner join sys_role r on r.role_id = ur.role_id
	WHERE 1=1
    @if(!isEmpty(searchText) && searchText != ''){
	 and ( username like #'%'+searchText+'%'#
	 or u.name like #'%'+searchText+'%'# )
	 @}
	@if(!isEmpty(deptIds) && deptIds != ''){
	 and u.dept_id in (#join(deptIds)#)
	@}	
	limit #offset#,#limit#
 
getCount2
===
	SELECT count(u.user_id) FROM sys_user u INNER JOIN sys_dept d ON u.dept_id = d.dept_id
	INNER JOIN sys_user_role ur ON u.user_id = ur.user_id
	INNER JOIN sys_role r on r.role_id = ur.role_id
	WHERE 1=1
    @if(!isEmpty(searchText) && searchText != ''){
	 and ( username like #'%'+searchText+'%'#
	 or u.name like #'%'+searchText+'%'# )
	 @}
	@if(!isEmpty(deptIds) && deptIds != ''){
	 and u.dept_id in (#join(deptIds)#)
	@}	
	
getUserList
===

	SELECT u.user_id,u.name,u.dept_id FROM `sys_user` u INNER JOIN sys_dept d on u.dept_id = d.dept_id WHERE u.dept_id in (
    SELECT d.dept_id FROM sys_dept d WHERE d.parent_id = #parentId# AND d.dept_id != #deptId#)
    
getUsersByDeptIdPage
===
	SELECT u.*,d.name workName
	FROM sys_user u INNER JOIN sys_dept d ON u.dept_id = d.dept_id WHERE 1=1
	and u.dept_id in (SELECT dept_id FROM
	sys_dept WHERE parent_id = (SELECT parent_id from sys_dept WHERE dept_id = #deptId#))
	 @if(!isEmpty(name)&&name!=''){
	 and u.name like #'%'+name+'%'#
	 @}
	 @if(!isEmpty(mobile)&&mobile!=''){
	 and u.mobile like #'%'+mobile+'%'#
	 @}
	 @if(!isEmpty(sex)&&sex!=''){
	 and u.sex = #sex#
	 @}
    order by u.user_id desc
    limit #offset#,#limit#

getUsersByDeptId
===
	SELECT u.user_id,u.name
	FROM sys_user u INNER JOIN sys_dept d ON u.dept_id = d.dept_id WHERE 1=1
	and u.dept_id in (SELECT dept_id FROM
	sys_dept WHERE parent_id = (SELECT parent_id from sys_dept WHERE dept_id = #deptId#))

countByDeptId
===
	SELECT COUNT(u.user_id)
	FROM sys_user u INNER JOIN sys_dept d ON u.dept_id = d.dept_id WHERE 1=1
	and u.dept_id in (SELECT dept_id FROM
	sys_dept WHERE parent_id = (SELECT parent_id from sys_dept WHERE dept_id = #deptId#))
	 @if(!isEmpty(name)&&name!=''){
	 and u.name like #'%'+name+'%'#
	 @}
	 @if(!isEmpty(mobile)&&mobile!=''){
	 and u.mobile like #'%'+mobile+'%'#
	 @}
	 @if(!isEmpty(sex)&&sex!=''){
	 and u.sex = #sex#
	 @}	
	 
getUserName
===
	SELECT * from sys_user where username=#userName#	
	
findAllByCompany
===
        SELECT
        	u.* 
        FROM
        	sys_user u
        	LEFT JOIN sys_dept d ON u.dept_id = d.dept_id 
        WHERE
        	d.parent_id = #parentId# 
        	
userById
===      
         select * from sys_user  where user_id= #id#  	
         
listAllSysUser
=== 
     SELECT DISTINCT u.user_id FROM `sys_user` u LEFT JOIN `sys_user_role` ur
     ON ur.user_id = u.user_id LEFT JOIN `sys_role` r
     ON r.role_id = ur.role_id
     WHERE r.role_id NOT IN (78,79,81,82)
 
getuserByname
===
    SELECT * from sys_user where username= #username2# 
    
    
getAllList
===
    SELECT * from sys_user   