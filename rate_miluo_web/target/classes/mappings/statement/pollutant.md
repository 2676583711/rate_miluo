minuteList
===
    
    select #page("x.*")#
    from (#text(sqlAll)#  order by t.recording_time desc) x
    
minuteList2
===
    
    select x.*
    from (#text(sqlAll)#  order by t.recording_time desc,v.site_id,t.equment_id) x
       
hourList
===
    select 
    @pageTag(){
        t.*,s.`name` siteName,v.`name` equipmentName
    @}
    from (select * from miluo_pollutant_hour 
    where recording_time >= #beginTime# and recording_time <= #endTime# 
    and equment_id in (#join(equmentIds)#)) t
    left join miluo_video v on v.equment_id = t.equment_id
    left join miluo_site s on s.id = v.site_id
    where t.data_type = #dataType#
    order by t.recording_time desc,v.site_id,t.equment_id,

hourList2
===
    select 
        t.*,s.`name` siteName,v.`name` equipmentName
    from (select * from miluo_pollutant_hour 
    where recording_time >= #beginTime# and recording_time <= #endTime# 
    and equment_id in (#join(equmentIds)#)) t
    left join miluo_video v on v.equment_id = t.equment_id
    left join miluo_site s on s.id = v.site_id
    where t.data_type = #dataType#
    order by t.recording_time desc,v.site_id,t.equment_id
    
hourList3
===
    select 
        t.*,s.`name` siteName,v.`name` equipmentName
    from (select * from miluo_pollutant_hour 
    where recording_time >= #beginTime# and recording_time <= #endTime# 
    and equment_id in (#join(equmentIds)#)) t
    left join miluo_video v on v.equment_id = t.equment_id
    left join miluo_site s on s.id = v.site_id
    where t.data_type = #dataType#
    order by t.recording_time ,v.site_id,t.equment_id
    
dayList
===
    select 
    @pageTag(){
        t.*,s.`name` siteName,v.`name` equipmentName
    @}
    from (select * from miluo_pollutant_day
    where recording_time >= #beginTime# and recording_time <= #endTime# 
    and equment_id in (#join(equmentIds)#)) t
    left join miluo_video v on v.equment_id = t.equment_id
    left join miluo_site s on s.id = v.site_id
    where t.data_type = #dataType#
    order by t.recording_time desc,v.site_id,t.equment_id   
    
dayList2
===
    select 
        t.*,s.`name` siteName,v.`name` equipmentName
    from (select * from miluo_pollutant_day
    where recording_time >= #beginTime# and recording_time <= #endTime# 
    and equment_id in (#join(equmentIds)#)) t
    left join miluo_video v on v.equment_id = t.equment_id
    left join miluo_site s on s.id = v.site_id
    where t.data_type = #dataType# 
    order by t.recording_time desc,v.site_id,t.equment_id 
             
minutePolluteInter
===
    SELECT #text(sqlAll)#
    ORDER BY pr.`recording_time`
    
findEqumentIds
===
    SELECT equment_id FROM miluo_site s
    LEFT JOIN miluo_video v 
    ON v.`site_id` = s.`id`
    WHERE s.`status` = 1
    AND v.site_id IN (#join(siteIds)#)

appMinuteList
===
    SELECT #text(sqlAll)#
    ORDER BY recording_time DESC,siteName
    @if(!isEmpty(pageNum) && !isEmpty(pageSize)){
        LIMIT #pageNum#,#pageSize#
    @}  
 
appHourList
===
    SELECT pr.*,s.name siteName,s.id site_id,v.name equipmentName 
    FROM `miluo_pollutant_hour` pr 
    LEFT JOIN `miluo_video` v ON v.equment_id = pr.equment_id
    LEFT JOIN miluo_site s ON s.id = v.site_id
    WHERE s.status = 1
    AND s.id IN (#join(siteIds)#)
    AND pr.recording_time BETWEEN #startTime# AND #endTime#
    AND pr.data_type = 1
    ORDER BY pr.recording_time DESC,siteName
    @if(!isEmpty(pageNum) && !isEmpty(pageSize)){
        LIMIT #pageNum#,#pageSize#
    @} 
    
appDailyList
===
    SELECT pr.*,s.name siteName,s.id site_id,v.name equipmentName 
    FROM `miluo_pollutant_day` pr 
    LEFT JOIN `miluo_video` v ON v.equment_id = pr.equment_id
    LEFT JOIN miluo_site s ON s.id = v.site_id
    WHERE s.status = 1
    AND s.id IN (#join(siteIds)#)
    AND pr.recording_time BETWEEN #startTime# AND #endTime#
    AND pr.data_type = 1
    ORDER BY pr.recording_time DESC,siteName
    @if(!isEmpty(pageNum) && !isEmpty(pageSize)){
        LIMIT #pageNum#,#pageSize#
    @} 
        
findPolluteData
===
    select `data` from miluo_pollutant_original 
    where recording_time > "2019-12-01 00:00:00" and recording_time <= "2019-12-02 10:41:00"
    order by recording_time 
    
insertPollutantEntity
===
    insert into miluo_pollutant_realtime_201912 (id,equment_id,recording_time,exhaust,dust,o2,so2,nox,flow_velocity,temp,press,data_type)
    values (#entity.id#,#entity.equmentId#,#entity.recordingTime#,#entity.exhaust#,#entity.dust#,
           #entity.o2#,#entity.so2#,#entity.nox#,#entity.flowVelocity#,#entity.temp#,#entity.press#,#entity.dataType#)

getOldHour
=== 
      SELECT
        	t2.name siteName,CONCAT(t2.name,'(',v.name,')') equipmentName,t1.*
        FROM
        	miluo_pollutant_hour t1 LEFT JOIN miluo_video v ON v.equment_id = t1.equment_id 
        	LEFT JOIN miluo_site t2 ON t2.id = v.site_id
        WHERE
        	1 = 1 
        	@if(!isEmpty(siteId) && siteId != ''){
        	    AND t2.id = #siteId#
        	@}
        	@if(!isEmpty(queryTime) && queryTime != ''){
                AND t1.recording_time BETWEEN DATE_SUB( #queryTime#, INTERVAL 23 HOUR ) 
                AND #queryTime#
            @}   
        ORDER BY
        	t1.recording_time
        	
getFromDesktop
===
    SELECT
        t2.name siteName,CONCAT(t2.name,'(',v.name,')') equipmentName,t1.*
    FROM
        miluo_desktop_pollutant t1 LEFT JOIN miluo_video v ON v.equment_id = t1.equment_id 
        LEFT JOIN miluo_site t2 ON t2.id = v.site_id
        WHERE t2.id = #siteId#
    GROUP BY t1.equment_id
    