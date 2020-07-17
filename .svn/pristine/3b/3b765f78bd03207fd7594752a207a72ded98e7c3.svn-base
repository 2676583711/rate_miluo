sample
===
* 注释

	select #use("cols")# from miluo_vidicon_file  where  #use("condition")#

cols
===
	id,vidicon_id,file_id,printscreen_date

updateSample
===
	
	id=#id#,vidicon_id=#vidiconId#,file_id=#fileId#,printscreen_date=#printscreenDate#

cols
===
	select #use("cols")# from miluo_vidicon_file where #use("condition")# order by printscreen_date desc limit 1
	
condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(vidiconId)){
	 and vidicon_id=#vidiconId#
	@}
	@if(!isEmpty(fileId)){
	 and file_id=#fileId#
	@}
	@if(!isEmpty(printscreenDate)){
	 and printscreen_date=#printscreenDate#
	@}
	
getScreenPicsByCondition
===
    SELECT 
    @pageTag(){
        vf.`id` AS id,vf.`printscreen_date` AS printscreenDate,
        sf.id AS fileId,sf.url AS url,
        t.vidiconId,t.vidicon_name AS vidiconName,t.name AS siteName,t.siteId
    @} 
    FROM miluo_vidicon_file vf 
    LEFT JOIN sys_file sf ON vf.`file_id` = sf.id 
    LEFT JOIN 
        (SELECT v.id AS vidiconId,v.`vidicon_name`,s.name,s.id AS siteId 
        FROM miluo_vidicon v 
          LEFT JOIN miluo_site s ON s.id = v.site_id) t 
    ON vf.`vidicon_id` = t.vidiconId 
    WHERE sf.type=#type#
    @if(!isEmpty(siteCodes) && siteCodes != ""){
      AND t.siteId in (#join(siteCodes)#)
    @}
    @if(!isEmpty(startDate) && startDate != ""){
      AND vf.`printscreen_date` >= #startDate#
    @}
    @if(!isEmpty(endDate) && endDate != ""){
      AND vf.`printscreen_date` <= #endDate#
    @}
    ORDER BY vf.`printscreen_date` DESC
    
removeByFileIds
===
    delete from miluo_vidicon_file where file_id in (#join(ids)#)