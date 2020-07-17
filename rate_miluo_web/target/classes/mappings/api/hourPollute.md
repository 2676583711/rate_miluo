getOldHour
===
      SELECT
         	t1.*,t2.name siteName,CONCAT(t2.name,'(',v.name,')') `name` ,t2.longitude,t2.latitude
         FROM
         	`miluo_hours_treatment_plant` t1 LEFT JOIN miluo_video v ON v.equment_id = t1.equment_id LEFT JOIN miluo_site t2 ON t2.id = v.site_id
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
        t1.*,t2.name siteName,CONCAT(t2.name,'(',v.name,')') `name` ,t2.longitude,t2.latitude
     FROM
        `miluo_desktop_water_plant` t1 LEFT JOIN miluo_video v ON v.equment_id = t1.equment_id LEFT JOIN miluo_site t2 ON t2.id = v.site_id
     WHERE t2.id = #siteId#
     ORDER BY t1.recording_time