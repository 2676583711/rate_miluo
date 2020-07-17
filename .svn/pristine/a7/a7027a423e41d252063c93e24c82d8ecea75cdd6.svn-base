batchRemove
===
	delete from sys_file where id in (
		@for(id in ids){
			#id#  #text(idLP.last?"":"," )#
		@}
	
count
===
	select count(*) from sys_file where #use("condition")#
list
===
	Select #use("cols")# from sys_file  where  #use("condition")#
	
getListByIds    		  
===
    SELECT * FROM sys_file WHERE id IN (#join(ids)#)
    
deleteByIds
===
    delete from sys_file where id in (#join(ids)#)

sample
===
* 注释

	select #use("cols")# from sys_file  where  #use("condition")#

cols
===
	id,fileName,fileSize,fileExt,type,path_name,url,fileFlag,create_date

updateSample
===
	
	id=#id#,fileName=#filename#,fileSize=#filesize#,fileExt=#fileext#,type=#type#,path_name=#pathName#,url=#url#,fileFlag=#fileflag#,create_date=#createDate#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(filename)){
	 and fileName=#filename#
	@}
	@if(!isEmpty(filesize)){
	 and fileSize=#filesize#
	@}
	@if(!isEmpty(fileext)){
	 and fileExt=#fileext#
	@}
	@if(!isEmpty(type)){
	 and type=#type#
	@}
	@if(!isEmpty(pathName)){
	 and path_name=#pathName#
	@}
	@if(!isEmpty(url)){
	 and url=#url#
	@}
	@if(!isEmpty(fileflag)){
	 and fileFlag=#fileflag#
	@}
	@if(!isEmpty(createDate)){
	 and create_date=#createDate#
	@}
	
	