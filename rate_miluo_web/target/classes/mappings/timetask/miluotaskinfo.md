sample
===
* 注释

	select #use("cols")# from miluo_statement_type  where  #use("condition")#


addStatement
===

    insert into miluo_statement_type values(#id#,#siteStatement#,#time#,#siteType#,#equipmentId#)	
	
getSiteId
===   
     select site_id from miluo_statement_type  

cols
===
	id,site_code,site_statement,time,site_type,equipment_id,site_id

updateSample
===
	
	id=#id#,site_code=#siteCode#,site_statement=#siteStatement#,time=#time#,site_type=#siteType#,equipment_id=#equipmentId#,site_id=#siteId#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(siteCode)){
	 and site_code=#siteCode#
	@}
	@if(!isEmpty(siteStatement)){
	 and site_statement=#siteStatement#
	@}
	@if(!isEmpty(time)){
	 and time=#time#
	@}
	@if(!isEmpty(siteType)){
	 and site_type=#siteType#
	@}
	@if(!isEmpty(equipmentId)){
	 and equipment_id=#equipmentId#
	@}
	@if(!isEmpty(siteId)){
	 and site_id=#siteId#
	@}
	