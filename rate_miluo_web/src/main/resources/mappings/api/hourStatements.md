sample
===
* 注释

	select #use("cols")# from miluo_air_hour_statements  where  #use("condition")#

getRealHours
===
    SELECT
    	t1.*,
    	t3.`name` stationName,
        t3.site_code,
    	t3.longitude,
    	t3.latitude,
    	t3.id site_id,
    	t1.aqi,
        t1.`no`,
        t1.nox,
        t1.no2,
        t1.so2,
        t1.pm10,
        t1.co,
        t1.pm25,
        t1.o3one_hour,
        t1.primary_ep,
        t1.query_time,
        t1.aq_type 
    FROM
    	`miluo_air_hour_statements` t1
        LEFT JOIN miluo_video t2 ON t1.site_code = t2.equment_id
        LEFT JOIN miluo_site t3 ON t2.site_id = t3.id 
    WHERE
    	t3.`status` = 1 
    @if( !isEmpty(siteCategory) && siteCategory != '' ){ 
        AND t3.site_category = #siteCategory#
    @} 
    ORDER BY
    	t1.query_time DESC 
    @if(!isEmpty(limit)){ 
    	LIMIT #limit#
    @}
    	
getOldHour
===
    SELECT
    	t3.`name` stationName,
        t3.site_code,
        t3.id site_id,
        t1.aqi,
        t1.`no`,
        t1.nox,
        t1.no2,
        t1.so2,
        t1.pm10,
        t1.co,
        t1.pm25,
        t1.o3one_hour,
        t1.tsp,
        t1.ws,
        t1.wd,
        t1.temp,
        t1.humi,
        t1.press,
        t1.primary_ep,
        t1.query_time,
        t1.aq_type 
    FROM
    	`miluo_air_hour_statements` t1
        LEFT JOIN miluo_video t2 ON t1.site_code = t2.equment_id
        LEFT JOIN miluo_site t3 ON t2.site_id = t3.id 
    WHERE 1=1
        @if(!isEmpty(siteIds)){
            AND t3.id IN (#join(siteIds)#)
        @}
    	@if(!isEmpty(queryTime)){
    	    AND t1.query_time BETWEEN DATE_SUB( #queryTime#, INTERVAL 23 HOUR ) AND #queryTime#
    	@}
    	@if(isEmpty(queryTime)){
            AND t1.query_time BETWEEN DATE_SUB( ( SELECT MAX( query_time ) FROM miluo_air_hour_statements ), INTERVAL 23 HOUR ) 
            AND ( SELECT MAX( query_time ) FROM miluo_air_hour_statements ) 
        @}
    ORDER BY
    	t1.query_time
   
getFromDesktop
===
    SELECT
        t3.`name` stationName,
        t3.site_code,
        t3.id site_id,
        t1.aqi,
        t1.`no`,
        t1.nox,
        t1.no2,
        t1.so2,
        t1.pm10,
        t1.co,
        t1.pm25,
        t1.ws,
        t1.wd,
        t1.temp,
        t1.humi,
        t1.press,
        t1.o3one_hour,
        t1.tsp,
        t1.primary_ep,
        t1.query_time,
        t1.aq_type 
    FROM
        `miluo_air_hour_statements` t1
        LEFT JOIN miluo_video t2 ON t1.site_code = t2.equment_id
        LEFT JOIN miluo_site t3 ON t2.site_id = t3.id 
    WHERE 
        t3.id = #siteId#
        AND t3.site_category IN (22,23)
    UNION 
        SELECT 
        t3.`name` stationName,
        t1.site_code,
        t3.id site_id,
        t1.aqi,
        t1.`no`,
        t1.nox,
        t1.no2,
        t1.so2,
        t1.pm10,
        t1.co,
        t1.pm25,
        t1.ws,
        t1.wd,
        t1.temp,
        t1.humi,
        t1.press,
        t1.o3 o3one_hour,
        t1.tsp,
        t1.primary_ep,
        t1.report_time query_time,
        t1.aq_type 
        FROM
        `miluo_desktop_air` t1
        LEFT JOIN miluo_video t2 ON t1.site_code = t2.equment_id
        LEFT JOIN miluo_site t3 ON t2.site_id = t3.id 
        WHERE t3.id = #siteId# 	
    ORDER BY query_time DESC
    LIMIT 1   	
    	
getListByTimeAndSiteCode
===
    SELECT
    	t3.`name` stationName,
        t3.site_code,
        t3.id site_id,
        t1.aqi,
        t1.`no`,
        t1.nox,
        t1.no2,
        t1.so2,
        t1.pm10,
        t1.co,
        t1.pm25,
        t1.ws,
        t1.wd,
        t1.temp,
        t1.humi,
        t1.press,
        t1.o3one_hour,
        t1.tsp,
        t1.primary_ep,
        t1.query_time,
        t1.aq_type,
        t1.aq_degree
    FROM
    	`miluo_air_hour_statements` t1
        LEFT JOIN miluo_video t2 ON t1.site_code = t2.equment_id
        LEFT JOIN miluo_site t3 ON t2.site_id = t3.id 
    WHERE
    	1 = 1 
    	AND t3.`status` = 1 
    	@if(!isEmpty(siteIds)){
    	    AND t3.id IN ( #join(siteIds)# ) 
    	@}
    	@if(!isEmpty(startTime) && startTime != '' && !isEmpty(endTime) && endTime != ''){
    	    AND t1.query_time BETWEEN #startTime#
    	    AND #endTime# 
    	@}
    ORDER BY
    	t1.query_time DESC
    @if(!isEmpty(pageNum) && !isEmpty(pageSize)){
        LIMIT #pageNum#,#pageSize#
    @}

queryListHourByCategory
===
    SELECT
    	ms.site_code siteCode,
    	ms.`name` stationName,
    	ms.latitude,
    	ms.longitude,
    	hs.aqi,
    	hs.no,
    	hs.nox,
    	hs.aq_degree,
    	hs.so2,
    	hs.no2,
    	hs.pm10,
    	hs.co,
    	hs.pm25,
    	hs.o3eight_hour,
    	hs.primary_ep,
    	hs.aq_type,
    	hs.query_time
    FROM
    	miluo_site ms
    	LEFT JOIN ( SELECT * FROM miluo_air_hour_statements WHERE 1=1 
    	@if(!isEmpty(queryTime) && queryTime != ''){
    	    AND query_time = #queryTime#
    	@}
    	@if(!isEmpty(limit)){
    	    ORDER BY query_time DESC LIMIT #limit#
    	@}
    	 ) hs ON ms.site_code = hs.site_code 
    WHERE 1=1
        @if(!isEmpty(siteCategory)){
            AND ms.site_category = #siteCategory#
        @}
    GROUP BY
    	ms.site_code


cols
===
	id,site_code,station_name,query_time,station_type,aq_degree,so2,so2_iaqi,no2,no2_iaqi,pm10,pm10_iaqi,co,co_iaqi,o3eight_hour,o3one_hour,pm25,pm25_iaqi,aqi,primary_ep,aq_type,ws,wd,temp,press,humi,rain,create_date,no,nox,tsp,noise

updateSample
===
	
	id=#id#,site_code=#siteCode#,station_name=#stationName#,query_time=#queryTime#,station_type=#stationType#,aq_degree=#aqDegree#,so2=#so2#,so2_iaqi=#so2Iaqi#,no2=#no2#,no2_iaqi=#no2Iaqi#,pm10=#pm10#,pm10_iaqi=#pm10Iaqi#,co=#co#,co_iaqi=#coIaqi#,o3eight_hour=#o3eightHour#,o3one_hour=#o3oneHour#,pm25=#pm25#,pm25_iaqi=#pm25Iaqi#,aqi=#aqi#,primary_ep=#primaryEp#,aq_type=#aqType#,ws=#ws#,wd=#wd#,temp=#temp#,press=#press#,humi=#humi#,rain=#rain#,create_date=#createDate#,no=#no#,nox=#nox#,tsp=#tsp#,noise=#noise#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(siteCode)){
	 and site_code=#siteCode#
	@}
	@if(!isEmpty(stationName)){
	 and station_name=#stationName#
	@}
	@if(!isEmpty(queryTime)){
	 and query_time=#queryTime#
	@}
	@if(!isEmpty(stationType)){
	 and station_type=#stationType#
	@}
	@if(!isEmpty(aqDegree)){
	 and aq_degree=#aqDegree#
	@}
	@if(!isEmpty(so2)){
	 and so2=#so2#
	@}
	@if(!isEmpty(so2Iaqi)){
	 and so2_iaqi=#so2Iaqi#
	@}
	@if(!isEmpty(no2)){
	 and no2=#no2#
	@}
	@if(!isEmpty(no2Iaqi)){
	 and no2_iaqi=#no2Iaqi#
	@}
	@if(!isEmpty(pm10)){
	 and pm10=#pm10#
	@}
	@if(!isEmpty(pm10Iaqi)){
	 and pm10_iaqi=#pm10Iaqi#
	@}
	@if(!isEmpty(co)){
	 and co=#co#
	@}
	@if(!isEmpty(coIaqi)){
	 and co_iaqi=#coIaqi#
	@}
	@if(!isEmpty(o3eightHour)){
	 and o3eight_hour=#o3eightHour#
	@}
	@if(!isEmpty(o3oneHour)){
	 and o3one_hour=#o3oneHour#
	@}
	@if(!isEmpty(pm25)){
	 and pm25=#pm25#
	@}
	@if(!isEmpty(pm25Iaqi)){
	 and pm25_iaqi=#pm25Iaqi#
	@}
	@if(!isEmpty(aqi)){
	 and aqi=#aqi#
	@}
	@if(!isEmpty(primaryEp)){
	 and primary_ep=#primaryEp#
	@}
	@if(!isEmpty(aqType)){
	 and aq_type=#aqType#
	@}
	@if(!isEmpty(ws)){
	 and ws=#ws#
	@}
	@if(!isEmpty(wd)){
	 and wd=#wd#
	@}
	@if(!isEmpty(temp)){
	 and temp=#temp#
	@}
	@if(!isEmpty(press)){
	 and press=#press#
	@}
	@if(!isEmpty(humi)){
	 and humi=#humi#
	@}
	@if(!isEmpty(rain)){
	 and rain=#rain#
	@}
	@if(!isEmpty(createDate)){
	 and create_date=#createDate#
	@}
	@if(!isEmpty(no)){
	 and no=#no#
	@}
	@if(!isEmpty(nox)){
	 and nox=#nox#
	@}
	@if(!isEmpty(tsp)){
	 and tsp=#tsp#
	@}
	@if(!isEmpty(noise)){
	 and noise=#noise#
	@}