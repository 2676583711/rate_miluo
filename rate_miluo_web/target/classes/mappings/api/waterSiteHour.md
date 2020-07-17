sample
===
* 注释

	select #use("cols")# from miluo_water_site  where  #use("condition")#
	
queryListByCodeAndTime
===

    SELECT
    	t1.*,
    	t2.`name` stationName,
    	t2.id site_id,
    	t3.`name` equipmentName
    FROM
    	#text(tableName)# t1 
    	LEFT JOIN miluo_video t3 ON t3.`equment_id`=t1.`station_code`
    	LEFT JOIN miluo_site t2 ON t2.id=t3.site_id
    WHERE
    	1 = 1 
    	@if(!isEmpty(siteCodes)){
    	    AND t2.id IN ( #join(siteCodes)# ) 
    	@}
    	@if(!isEmpty(startTime) && startTime != ''){
    	    AND t1.date_time >= #startTime#
    	@}
    	@if(!isEmpty(endTime) && endTime != ''){
            AND t1.date_time <= #endTime#
        @}
    ORDER BY 
        t1.date_time DESC
    @if(!isEmpty(pageNum) && !isEmpty(pageSize)){
        LIMIT #pageNum#,#pageSize#
    @}
    
getOldHour
===
    SELECT
    t2.name stationName,CONCAT(t2.name,'(',v.name,')') equipmentName,t1.*
    FROM miluo_hour_water_site t1 LEFT 
    JOIN miluo_video v ON v.equment_id = t1.station_code 
    LEFT JOIN miluo_site t2 ON t2.id = v.site_id
    WHERE
    1 = 1 
    @if(!isEmpty(siteId) && siteId != ''){
        AND t2.id = #siteId#
    @}
    @if(!isEmpty(queryTime) && queryTime != ''){
        AND t1.date_time BETWEEN DATE_SUB( #queryTime#, INTERVAL 23 HOUR ) 
        AND #queryTime#
    @}
    ORDER BY t1.date_time
    
getFromDesktop
===
    SELECT
        t2.name stationName,CONCAT(t2.name,'(',v.name,')') equipmentName,t1.*
        FROM miluo_desktop_water t1 LEFT 
        JOIN miluo_video v ON v.equment_id = t1.station_code 
        LEFT JOIN miluo_site t2 ON t2.id = v.site_id
        WHERE t2.id = #siteId#
   