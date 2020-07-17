queryByCondition
===
* 注释

	select #use("cols")# from sys_area  where  #use("condition")#

cols
===
	area_code,area_name,area_type,remarks
	
   
updateSample
===
	
	area_code=#areaCode#,area_name=#areaName#,area_type=#areaType#,remarks=#remarks#

condition
===

	1 = 1  
	@if(!isEmpty(query.areaCode)){
	 and area_code like #query.areaCode+'%'#
	@}
	@if(!isEmpty(query.areaName)){
	 and area_name=#query.areaName#
	@}
	@if(!isEmpty(query.areaType)){
	 and area_type=#query.areaType#
	@}
	@if(!isEmpty(remarks)){
	 and remarks=#remarks#
	@}