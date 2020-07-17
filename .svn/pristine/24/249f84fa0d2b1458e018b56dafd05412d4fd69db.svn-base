getFactorData
===
	SELECT #use("cols")# FROM pollutant_minute_data d 
	WHERE site_code=#siteCode# AND data_time BETWEEN #startDate# AND #endDate# ORDER BY data_time
sample
===
* 注释

	select #use("cols")# from pollutant_minute_data  where  #use("condition")#

cols
===
	id,site_code,site_type,cmd_id,data_time,site_data

updateSample
===
	
	id=#id#,site_code=#siteCode#,site_type=#siteType#,cmd_id=#cmdId#,data_time=#dataTime#,site_data=#siteData#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(siteCode)){
	 and site_code=#siteCode#
	@}
	@if(!isEmpty(siteType)){
	 and site_type=#siteType#
	@}
	@if(!isEmpty(cmdId)){
	 and cmd_id=#cmdId#
	@}
	@if(!isEmpty(dataTime)){
	 and data_time=#dataTime#
	@}
	@if(!isEmpty(siteData)){
	 and site_data=#siteData#
	@}
	
	