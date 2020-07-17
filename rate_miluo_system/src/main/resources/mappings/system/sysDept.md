sample
===
* 注释

	select #use("cols")# from sys_dept  where  #use("condition")#
	
getDeptUserNumber
===
	select count(*) from sys_user where dept_id = #deptId#
queryByCondition
===

	select * from sys_dept d where 1=1
    	@if(!isEmpty(name)){
    	 and name like #'%'+name+'%'#
    	@}
    	@if(!isEmpty(delFlag)&&delFlag==2){
    	  and d.parent_id = (SELECT parent_id FROM sys_dept WHERE dept_id = #deptId#) OR d.dept_id = (SELECT parent_id FROM sys_dept
    	  WHERE dept_id
    	  = #deptId#) or del_flag =3 
    	@}
    	@if(!isEmpty(delFlag)&&delFlag==3){
    	and find_in_set(dept_id,getChild(#parentId#))
    	@}

findByName
===
	SELECT d.* FROM sys_dept d WHERE 1=1
	@if(!isEmpty(name)){
	 and d.name like #'%'+name+'%'#
	@}	
	
findAllByCondition
===
	SELECT
    	d1.*,
    	d2.`name` parentName 
    FROM
    	sys_dept d1
    	LEFT JOIN sys_dept d2 ON d1.parent_id = d2.dept_id 
    WHERE
    	d1.parent_id <> 0
	@if(!isEmpty(name)){
	 and d1.name like #'%'+name+'%'#
	@}	
    order by d2.dept_id  	

count
===
	select count(*) from sys_dept where #use("condition")#
listParentDept
===
	select DISTINCT parent_id from sys_dept
cols
===
	dept_id,parent_id,name,order_num,del_flag

updateSample
===
	
	dept_id=#deptId#,parent_id=#parentId#,name=#name#,order_num=#orderNum#,del_flag=#delFlag#

getByDeptId
===

	SELECT d.* FROM sys_dept d WHERE d.parent_id = (SELECT parent_id FROM sys_dept WHERE dept_id = #deptId#) OR d.dept_id = (SELECT parent_id FROM sys_dept WHERE dept_id = #deptId#)
	@if(!isEmpty(delFlag)&&delFlag==2){
	  or del_flag =3 
	@}
	
updateDelFlagByParentId
===
	UPDATE sys_dept 
    SET del_flag = #delFlag# 
    WHERE
	parent_id = #deptId#
	
condition
===

	1 = 1  
	@if(!isEmpty(deptId)){
	 and dept_id=#deptId#
	@}
	@if(!isEmpty(parentId)){
	 and parent_id=#parentId#
	@}
	@if(!isEmpty(name)){
	 and name like #'%'+name+'%'#
	@}
	@if(!isEmpty(orderNum)){
	 and order_num=#orderNum#
	@}
	@if(!isEmpty(delFlag)){
	 and del_flag=#delFlag#
	@}
	
getdeptByname
===
        SELECT * from sys_dept d where  d.name  =#deptName#
	