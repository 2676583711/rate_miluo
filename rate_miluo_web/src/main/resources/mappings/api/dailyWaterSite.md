sample
===
* 注释

	select #use("cols")# from miluo_days_water_site  where  #use("condition")#
	
queryListByCodeAndTime
===
    SELECT
        	t1.*,
        	t2.`name` stationName,
        	t3.`name` equipmentName
        FROM
        	#text(tableName)# t1
        	LEFT JOIN miluo_video t3 ON t3.`equment_id`=t1.`station_code`
        	LEFT JOIN miluo_site t2 ON t2.id = t3.site_id
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
