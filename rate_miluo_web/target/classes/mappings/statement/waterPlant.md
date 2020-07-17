sample
===
* 注释

	select #use("cols")# from miluo_realtime_treatment_plant  where  #use("condition")#

cols
===
	id,equment_id,recording_time,bo1,ph,pb,cd,shen,zn,cu

updateSample
===
	
	id=#id#,equment_id=#equmentId#,recording_time=#recordingTime#,bo1=#bo1#,ph=#ph#,pb=#pb#,cd=#cd#,shen=#shen#,zn=#zn#,cu=#cu#

minuteList
===
	select 
     p.*,s.name siteName,s.id site_id,e.`equment_id` as equmentId,e.`name` AS equipmentName, s.`site_code` AS siteCode
	FROM miluo_realtime_treatment_plant p LEFT JOIN miluo_video e ON e.`equment_id`=p.`equment_id`
	LEFT JOIN miluo_site s ON e.`site_id`=s.`id`
	WHERE s.`status`=1 and s.site_type=4
    AND s.id in (#join(siteIds)#)
	@if(!isEmpty(beginTime) && beginTime != ""){
        and p.recording_time >= #beginTime#
     @}
     @if(!isEmpty(endTime) && endTime != ""){
        and p.recording_time <= #endTime#
     @}
     order by p.recording_time desc
     @if(!isEmpty(pageNum) && !isEmpty(pageSize)){
         LIMIT #pageNum#,#pageSize#
     @}

hourList
===
	SELECT 
     p.*,s.name AS siteName,s.id site_id,e.`equment_id` AS equmentId,e.`name` AS equipmentName,s.`site_code` AS siteCode,
     p.`recording_time` AS hourTime   
	FROM miluo_hours_treatment_plant p LEFT JOIN miluo_video e ON e.`equment_id`=p.`equment_id`
	LEFT JOIN miluo_site s ON e.`site_id`=s.`id`
	WHERE s.`status`=1 AND s.site_type=4
	AND s.id in (#join(siteIds)#)
	@if(!isEmpty(beginTime) && beginTime != ""){
       AND p.`recording_time` >= #beginTime#
     @}
     @if(!isEmpty(endTime) && endTime != ""){
       AND p.`recording_time` <= #endTime#
     @}
     order by p.`recording_time` DESC
     @if(!isEmpty(pageNum) && !isEmpty(pageSize)){
         LIMIT #pageNum#,#pageSize#
     @}

dailyList
===
	SELECT 
     p.*,s.name AS siteName,s.id site_id,
     DATE_FORMAT(p.`recording_time`,'%Y-%m-%d') AS queryTime,
     e.`equment_id` AS equmentId,e.`name` AS equipmentName,s.`site_code` AS siteCode
	FROM miluo_days_treatment_plant p LEFT JOIN miluo_video e ON e.`equment_id`=p.`equment_id`
	LEFT JOIN miluo_site s ON e.`site_id`=s.`id`
	WHERE s.`status`=1 AND s.site_type=4
	AND s.id in (#join(siteIds)#)
	@if(!isEmpty(beginTime) && beginTime != ""){
        and DATE_FORMAT(p.`recording_time`, '%Y-%m-%d') >= #beginTime#
     @}
     @if(!isEmpty(endTime) && endTime != ""){
        and DATE_FORMAT(p.`recording_time`, '%Y-%m-%d') <= #endTime#
     @}
     order by p.`recording_time` DESC
     @if(!isEmpty(pageNum) && !isEmpty(pageSize)){
         LIMIT #pageNum#,#pageSize#
     @}

pollutList
===
	select 
    @pageTag(){
        p.*,s.name siteName,e.`equment_id` as equmentId,e.`name` AS equipmentName,s.`name` AS siteName, s.`site_code` AS siteCode
    @} 
	FROM miluo_realtime_treatment_plant p LEFT JOIN miluo_video e ON e.`equment_id`=p.`equment_id`
	LEFT JOIN miluo_site s ON e.`site_id`=s.`id`
	WHERE s.`status`=1 and s.site_type=4
	@if(!isEmpty(siteCodes) && siteCodes != ""){
        AND s.id in (#join(siteCodes)#)
    @}else{
        AND s.id in (#join(siteIds)#)
    @}
	@if(!isEmpty(beginTime) && beginTime != ""){
        and p.recording_time >= #beginTime#
     @}
     @if(!isEmpty(endTime) && endTime != ""){
        and p.recording_time <= #endTime#
     @}
	
	#use("getOrder")#
	
getOrder
===
	ORDER BY
	@if(!isEmpty(sortName)){
	@if(sortName=='recordingTime'){
	 p.recording_time
	@}
	@if(sortName=='bo1'){
	 p.bo1
	@}
	@if(sortName=='ph'){
	 p.ph
	@}
	@if(sortName=='pb'){
	 p.pb
	@}
	@if(sortName=='cd'){
	 p.cd
	@}
	@if(sortName=='shen'){
	 p.shen
	@}
	@if(sortName=='produDate'){
	 produ_date
	@}
	@if(sortName=='zn'){
	 p.zn
	@} 
	@if(sortName=='cu'){
	 p.cu
	@}
	@if(sortOrder=="asc"){
	 ASC
	@}
	@if(sortOrder=="desc"){
	DESC
	@}
	@}else{
	p.recording_time desc
	@}
condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(equmentId)){
	 and equment_id=#equmentId#
	@}
	@if(!isEmpty(recordingTime)){
	 and recording_time=#recordingTime#
	@}
	@if(!isEmpty(bo1)){
	 and bo1=#bo1#
	@}
	@if(!isEmpty(ph)){
	 and ph=#ph#
	@}
	@if(!isEmpty(pb)){
	 and pb=#pb#
	@}
	@if(!isEmpty(cd)){
	 and cd=#cd#
	@}
	@if(!isEmpty(shen)){
	 and shen=#shen#
	@}
	@if(!isEmpty(zn)){
	 and zn=#zn#
	@}
	@if(!isEmpty(cu)){
	 and cu=#cu#
	@}