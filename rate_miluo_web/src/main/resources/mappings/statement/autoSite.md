sample
===
* 注释

	select #use("cols")# from miluo_water_site  where  #use("condition")#

cols
===
	id,station_code,date_time,sw,plc,zd,ddl,codmn,pb,rjy,ph,shen,nh3_n,comprehensive_toxicity,tp,chromium,water_sort,k03,k04

updateSample
===
	
	id=#id#,station_code=#stationCode#,date_time=#dateTime#,sw=#sw#,plc=#plc#,zd=#zd#,ddl=#ddl#,codmn=#codmn#,pb=#pb#,rjy=#rjy#,ph=#ph#,shen=#shen#,nh3_n=#nh3N#,comprehensive_toxicity=#comprehensiveToxicity#,tp=#tp#,chromium=#chromium#,water_sort=#waterSort#,k03=#k03#,k04=#k04#

minuteList
===
	select 
     w.*,s.name siteName,e.`name` AS equipmentName, s.`site_code` AS siteCode
	FROM miluo_water_site w LEFT JOIN miluo_video e ON e.`equment_id`=w.`station_code`
	LEFT JOIN miluo_site s ON e.`site_id`=s.`id`
	WHERE s.`status`=1 and s.site_type=1
	@if(!isEmpty(siteCodes) && siteCodes != ""){
        AND s.id in (#join(siteCodes)#)
    @}else{
        AND s.id in (#join(siteIds)#)
    @}
	@if(!isEmpty(beginTime) && beginTime != ""){
        and w.date_time >= #beginTime#
     @}
     @if(!isEmpty(endTime) && endTime != ""){
        and w.date_time <= #endTime#
     @}
     ORDER BY w.date_time

hourList
===
	select 
     w.*,s.name siteName,e.`name` AS equipmentName, s.`site_code` AS siteCode
	FROM miluo_hour_water_site w LEFT JOIN miluo_video e ON e.`equment_id`=w.`station_code`
	LEFT JOIN miluo_site s ON e.`site_id`=s.`id`
	WHERE s.`status`=1 and s.site_type=1
	@if(!isEmpty(siteCodes) && siteCodes != ""){
        AND s.id in (#join(siteCodes)#)
    @}else{
        AND s.id in (#join(siteIds)#)
    @}
	@if(!isEmpty(beginTime) && beginTime != ""){
        and w.date_time >= #beginTime#
     @}
     @if(!isEmpty(endTime) && endTime != ""){
        and w.date_time <= #endTime#
     @}
     ORDER BY w.date_time

dailyList
===
    select 
     w.*,s.name siteName,e.`name` AS equipmentName, s.`site_code` AS siteCode
	FROM miluo_days_water_site w LEFT JOIN miluo_video e ON e.`equment_id`=w.`station_code`
	LEFT JOIN miluo_site s ON e.`site_id`=s.`id`
	WHERE s.`status`=1 and s.site_type=1
	@if(!isEmpty(siteCodes) && siteCodes != ""){
        AND s.id in (#join(siteCodes)#)
    @}else{
        AND s.id in (#join(siteIds)#)
    @}
	@if(!isEmpty(beginTime) && beginTime != ""){
        and w.date_time >= #beginTime#
     @}
     @if(!isEmpty(endTime) && endTime != ""){
        and w.date_time <= #endTime#
     @}
     ORDER BY w.date_time

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(stationCode)){
	 and station_code=#stationCode#
	@}
	@if(!isEmpty(dateTime)){
	 and date_time=#dateTime#
	@}
	@if(!isEmpty(sw)){
	 and sw=#sw#
	@}
	@if(!isEmpty(plc)){
	 and plc=#plc#
	@}
	@if(!isEmpty(zd)){
	 and zd=#zd#
	@}
	@if(!isEmpty(ddl)){
	 and ddl=#ddl#
	@}
	@if(!isEmpty(codmn)){
	 and codmn=#codmn#
	@}
	@if(!isEmpty(pb)){
	 and pb=#pb#
	@}
	@if(!isEmpty(rjy)){
	 and rjy=#rjy#
	@}
	@if(!isEmpty(ph)){
	 and ph=#ph#
	@}
	@if(!isEmpty(shen)){
	 and shen=#shen#
	@}
	@if(!isEmpty(nh3N)){
	 and nh3_n=#nh3N#
	@}
	@if(!isEmpty(comprehensiveToxicity)){
	 and comprehensive_toxicity=#comprehensiveToxicity#
	@}
	@if(!isEmpty(tp)){
	 and tp=#tp#
	@}
	@if(!isEmpty(chromium)){
	 and chromium=#chromium#
	@}
	@if(!isEmpty(waterSort)){
	 and water_sort=#waterSort#
	@}
	@if(!isEmpty(k03)){
	 and k03=#k03#
	@}
	@if(!isEmpty(k04)){
	 and k04=#k04#
	@}
	