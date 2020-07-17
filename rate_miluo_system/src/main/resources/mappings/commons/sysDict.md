sample
===
* 注释

	select #use("cols")# from sys_dict  where  #use("condition")#

cols
===
	id,name,value,type,description,sort,parent_id,create_by,create_date,update_by,update_date,remarks,del_flag

updateSample
===
	
	id=#id#,name=#name#,value=#value#,type=#type#,description=#description#,sort=#sort#,parent_id=#parentId#,create_by=#createBy#,create_date=#createDate#,update_by=#updateBy#,update_date=#updateDate#,remarks=#remarks#,del_flag=#delFlag#

queryByCondition
===	 
	
	
	select 
	@pageTag(){
		d.*,u.name createName,u1.name updateName
	@}
	 from sys_dict d left join sys_user u on d.create_by=u.user_id 
	 left join sys_user u1 on d.update_by=u1.user_id 
	 where #use("condition")#
	 
condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(name)&&name!=''){
	 and d.name like #'%'+name+'%'#
	@}
	@if(!isEmpty(value)){
	 and value=#value#
	@}
	@if(!isEmpty(type)){
	 and type=#type#
	@}
	@if(!isEmpty(description)&&description!=''){
	 and d.description like #'%'+description+'%'#
	@}
	@if(!isEmpty(sort)){
	 and sort=#sort#
	@}
	@if(!isEmpty(parentId)){
	 and parent_id=#parentId#
	@}
	@if(!isEmpty(createBy)){
	 and create_by=#createBy#
	@}
	@if(!isEmpty(createDate)){
	 and create_date=#createDate#
	@}
	@if(!isEmpty(updateBy)){
	 and update_by=#updateBy#
	@}
	@if(!isEmpty(updateDate)){
	 and update_date=#updateDate#
	@}
	@if(!isEmpty(remarks)){
	 and remarks=#remarks#
	@}
	@if(!isEmpty(delFlag)){
	 and del_flag=#delFlag#
	@}
	
list
===
	select * 
	from sys_dict d 
	where d.type = #type#