sample
===
* 注释

	select #use("cols")# from miluo_air_daily_statements  where  #use("condition")#

getOldDay
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
        t1.o3eight_hour,
        t1.primary_ep,
        t1.query_time,
        t1.aq_type  
    FROM
    	`miluo_air_daily_statements` t1
        LEFT JOIN miluo_video t2 ON t1.site_code = t2.equment_id
        LEFT JOIN miluo_site t3 ON t2.site_id = t3.id 
    WHERE 1=1
        @if(!isEmpty(siteIds)){
            AND t3.id IN ( #join(siteIds)# ) 
        @}
    	AND t1.query_time BETWEEN DATE_SUB( ( SELECT MAX( query_time ) FROM miluo_air_daily_statements ), INTERVAL 6 DAY ) 
    	AND ( SELECT MAX( query_time ) FROM miluo_air_daily_statements ) 
    ORDER BY
    	t1.query_time ASC

getListByTimeAndSiteCode
===
    SELECT
        t3.`name` stationName,
        t3.site_code,
        t1.aqi,
        t1.`no`,
        t1.nox,
        t1.no2,
        t1.so2,
        t1.pm10,
        t1.co,
        t1.pm25,
        t1.tsp,
        t1.ws,
        t1.wd,
        t1.temp,
        t1.humi,
        t1.press,
        t1.o3eight_hour,
        t1.primary_ep,
        t1.query_time,
        t1.aq_type,
        t1.aq_degree
    FROM
        `miluo_air_daily_statements` t1
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


cols
===
	id,site_code,station_name,query_time,station_type,aq_degree,so2,so2_iaqi,no2,no2_iaqi,pm10,pm10_iaqi,co,co_iaqi,o3eight_hour,o3one_hour_iaqi,pm25,pm25_iaqi,aqi,primary_ep,aq_type,ws,wd,temp,press,humi,rain,create_date,no,nox,tsp,noise

updateSample
===
	
	id=#id#,site_code=#siteCode#,station_name=#stationName#,query_time=#queryTime#,station_type=#stationType#,aq_degree=#aqDegree#,so2=#so2#,so2_iaqi=#so2Iaqi#,no2=#no2#,no2_iaqi=#no2Iaqi#,pm10=#pm10#,pm10_iaqi=#pm10Iaqi#,co=#co#,co_iaqi=#coIaqi#,o3eight_hour=#o3eightHour#,o3one_hour_iaqi=#o3oneHourIaqi#,pm25=#pm25#,pm25_iaqi=#pm25Iaqi#,aqi=#aqi#,primary_ep=#primaryEp#,aq_type=#aqType#,ws=#ws#,wd=#wd#,temp=#temp#,press=#press#,humi=#humi#,rain=#rain#,create_date=#createDate#,no=#no#,nox=#nox#,tsp=#tsp#,noise=#noise#

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
	@if(!isEmpty(o3oneHourIaqi)){
	 and o3one_hour_iaqi=#o3oneHourIaqi#
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