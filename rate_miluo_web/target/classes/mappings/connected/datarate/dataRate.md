getDataRateAirList
===
    
    SELECT s.name,s.site_category,p.* FROM miluo_site s
    LEFT JOIN miluo_video v ON v.site_id = s.id
    LEFT JOIN (
    SELECT t.site_code,COUNT(t.no2) no2,COUNT(t.so2) so2,COUNT(t.o3one_hour) o3one_hour ,COUNT(t.co) co,COUNT(t.pm10) pm10,COUNT(t.pm25) pm25,
     COUNT(t.ws) ws,COUNT(t.wd) wd,COUNT(t.press) press,COUNT(t.temp) temp,COUNT(t.humi) humi,COUNT(t.rain) rain,COUNT(t.noise)noise,COUNT(t.tsp)tsp
     FROM miluo_air_hour_statements t WHERE t.query_time >= #beginTime#  AND t.query_time < #endTime#
     GROUP BY t.site_code
    ) p ON p.site_code = v.equment_id
    WHERE s.status = 1
    AND s.id IN (#join(siteIds)#)
    ORDER BY s.site_category DESC,v.equment_id
    
getDataRatePolluteList
===

    SELECT s.name siteName,p.* FROM miluo_site s
    LEFT JOIN miluo_video v ON v.site_id = s.id
    LEFT JOIN (
    SELECT t.recording_time,t.equment_id,COUNT(t.exhaust) exhaust,COUNT(t.so2) so2,COUNT(t.o2) o2 ,COUNT(t.dust) dust,COUNT(t.nox) nox,COUNT(t.flow_velocity) flow_velocity,
    COUNT(t.temp) temp,COUNT(t.press) press
     FROM miluo_pollutant_hour t WHERE t.recording_time >= #beginTime# AND t.recording_time < #endTime#
     GROUP BY t.equment_id
    ) p ON p.equment_id = v.equment_id
    WHERE s.status = 1
    AND s.id IN (#join(siteIds)#)
    ORDER BY p.recording_time DESC
    
getDataRateWaterPlantList
===
    SELECT s.name,v.name videoName,p.* FROM miluo_site s
    LEFT JOIN miluo_video v ON v.site_id = s.id
    LEFT JOIN (
    SELECT t.recording_time,t.equment_id,COUNT(t.bo1) bo1,COUNT(t.ph) ph,COUNT(t.pb) pb,COUNT(t.cd) cd,COUNT(t.shen) shen,COUNT(t.zn) zn,COUNT(t.cu) cu,
    COUNT(t.tp) tp,COUNT(t.tn) tn,COUNT(t.cod) cod,COUNT(t.nh3) nh3
     FROM `miluo_hours_treatment_plant` t WHERE t.recording_time >= #beginTime#  AND t.recording_time < #endTime#
     GROUP BY t.equment_id
    ) p ON p.equment_id = v.equment_id
    WHERE s.status = 1
    AND s.id IN (#join(siteIds)#)
    ORDER BY p.recording_time DESC
    
getDataRateWaterList
===
    SELECT s.name siteName,p.* FROM miluo_site s
    LEFT JOIN miluo_video v ON v.site_id = s.id
    LEFT JOIN (
    SELECT t.date_time,t.station_code,COUNT(t.sw) sw,COUNT(t.plc) plc,COUNT(t.zd) zd ,COUNT(t.ddl) ddl,COUNT(t.codmn) codmn,COUNT(t.pb) pb,COUNT(t.rjy) rjy,
    COUNT(t.ph) ph,COUNT(t.shen) shen,COUNT(t.nh3_n) nh3_n,COUNT(t.comprehensive_toxicity) comprehensive_toxicity,COUNT(t.tp) tp,
    COUNT(t.chromium) chromium,COUNT(t.cd) cd,COUNT(t.kmn) kmn,COUNT(t.temp) temp,COUNT(t.humi) humi
     FROM miluo_hour_water_site t WHERE t.date_time >= #beginTime# AND t.date_time < #endTime#
     GROUP BY t.station_code
    ) p ON p.station_code = v.equment_id
    WHERE s.status = 1
    AND s.id IN (#join(siteIds)#)
    ORDER BY p.date_time DESC
    
get24HourPollutantList
===
    SELECT s.name siteName,p.* FROM `miluo_pollutant_hour` p LEFT JOIN `miluo_video` v
    ON v.equment_id = p.equment_id
    LEFT JOIN `miluo_site` s ON s.id = v.site_id
    WHERE v.equment_id = #siteCode# AND p.data_type = 1 AND p.recording_time >= (NOW()-INTERVAL 25 HOUR)
    ORDER BY p.recording_time DESC
    
get24HourWaterPlantList
===
    SELECT s.name,v.name videoName,p.* FROM `miluo_hours_treatment_plant` p 
    LEFT JOIN `miluo_video` v ON v.equment_id = p.equment_id
    LEFT JOIN `miluo_site` s ON s.id = v.site_id
    WHERE v.equment_id = #siteCode# AND p.recording_time >= (NOW()-INTERVAL 25 HOUR)
    ORDER BY p.recording_time DESC
    
get24HourWaterList
===
    SELECT s.name siteName,p.* FROM `miluo_hour_water_site` p 
    LEFT JOIN `miluo_video` v ON v.equment_id = p.station_code
    LEFT JOIN `miluo_site` s ON s.id = v.site_id
    WHERE v.equment_id = #siteCode# AND p.date_time >= (NOW()-INTERVAL 25 HOUR)
    ORDER BY p.date_time DESC 