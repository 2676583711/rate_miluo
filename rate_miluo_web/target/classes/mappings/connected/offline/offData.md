findAirStatusList
===
    SELECT s.name siteName,s.site_category,v.name videoName,v.equment_id site_code,d.report_time AS query_time FROM miluo_site s 
    LEFT JOIN miluo_video v ON s.id=v.site_id LEFT JOIN miluo_desktop_air d
    ON v.equment_id = d.site_code AND d.report_time >= (NOW() - INTERVAL 10 MINUTE)
    WHERE s.site_type = '2' AND s.site_category = '21'
    AND s.id IN (#join(siteIds)#) 
    UNION ALL
    SELECT s.name siteName,s.site_category,v.name videoName,v.equment_id site_code,d.query_time AS query_time FROM miluo_site s 
    LEFT JOIN miluo_video v ON s.id=v.site_id LEFT JOIN miluo_air_hour_statements d
    ON v.equment_id = d.site_code AND d.query_time > (NOW() - INTERVAL 1.5 HOUR)
    WHERE (s.site_category = '22' OR s.site_category = '23')
    AND s.id IN (#join(siteIds)#)
    
findAirDataList
===
    SELECT DISTINCT site_code,report_time AS query_time FROM miluo_air_minute_small_site
    WHERE report_time >= #beginTime# AND report_time <= #endTime#
    AND site_code in (#join(equmentIds)#)
    UNION 
    SELECT DISTINCT site_code,query_time FROM miluo_air_hour_statements
    WHERE query_time >= #beginTime# AND query_time <= #endTime#
    AND site_code in (#join(equmentIds)#)
    
findPolluteStatusList
===
    SELECT s.name siteName,v.name videoName,v.equment_id site_code,d.recording_time AS query_time FROM miluo_site s 
    LEFT JOIN miluo_video v ON s.id=v.site_id LEFT JOIN miluo_desktop_pollutant d
    ON v.equment_id = d.equment_id AND d.recording_time >= (NOW() - INTERVAL 10 MINUTE)
    WHERE s.site_type = '3'
    AND s.id IN (#join(siteIds)#)
    
findPolluteDataList
=== 
    SELECT #text(sqlAll)#
    
findWaterPlantStatusList
===
    SELECT s.name siteName,v.name videoName,v.equment_id site_code,d.recording_time AS query_time FROM miluo_site s 
    LEFT JOIN miluo_video v ON s.id=v.site_id LEFT JOIN miluo_desktop_water_plant d
    ON v.equment_id = d.equment_id AND d.recording_time >= (NOW() - INTERVAL 20 MINUTE)
    WHERE s.site_type = '4'
    AND s.id IN (#join(siteIds)#)
    
findWaterPlantDataList
===
    SELECT DISTINCT equment_id site_code,recording_time AS query_time FROM miluo_realtime_treatment_plant
    WHERE recording_time >= #beginTime# AND recording_time <= #endTime#
    AND equment_id IN (#join(equmentIds)#)

findWaterAutoStatusList
===
    SELECT s.name siteName,v.name videoName,v.equment_id site_code,d.date_time AS query_time FROM miluo_site s 
    LEFT JOIN miluo_video v ON s.id=v.site_id LEFT JOIN miluo_desktop_water d
    ON v.equment_id = d.station_code AND d.date_time >= (NOW() - INTERVAL 10 MINUTE)
    WHERE s.site_type = '1'
    AND s.id IN (#join(siteIds)#)
    
findWaterAutoDataList
===
    SELECT DISTINCT station_code site_code,date_time AS query_time FROM miluo_water_site
    WHERE date_time >= #beginTime# AND date_time <= #endTime#
    AND station_code IN (#join(equmentIds)#)