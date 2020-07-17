findHourData
===
    SELECT ph.*, s.name siteName, v.name equipmentName
    FROM miluo_pollutant_hour ph
           LEFT JOIN miluo_video v ON v.equment_id = ph.equment_id
           LEFT JOIN miluo_site s ON s.id=v.site_id
    WHERE 1 = 1
      @if(!isEmpty(siteIds)){
       AND s.id in (#join(siteIds)#)
      @}
      AND s.status = 1
      AND ph.recording_time >= #startDate# AND ph.recording_time <= #endDate#
    ORDER BY ph.recording_time

findDayData
===
    SELECT ph.*, s.name siteName, v.name equipmentName
    FROM miluo_pollutant_day ph
           LEFT JOIN miluo_video v ON v.equment_id = ph.equment_id
           LEFT JOIN miluo_site s ON s.id=v.site_id
    WHERE 1 = 1
      @if(!isEmpty(siteIds)&&siteIds!=''){
        AND s.id in (#join(siteIds)#)
      @}
      AND ph.recording_time >= #startDate# AND ph.recording_time <= #endDate#
    ORDER BY ph.recording_time

findNewMinuteData
===

    SELECT mpr.*,s.`name` site_name,v.`name` equipmentName
        FROM miluo_desktop_pollutant mpr
        LEFT JOIN miluo_video v ON v.equment_id=mpr.equment_id
        LEFT JOIN miluo_site s ON s.id = v.site_id
        WHERE s.status = 1 AND s.id=#siteId#
        ORDER BY equment_id
            
findMinuteData
===
    SELECT #text(sqlAll)#
    ORDER BY recording_time
    
findSiteNamesBySiteIds
===
    SELECT s.name siteName,pf.name pollutName FROM miluo_pollutant_realtime pr LEFT JOIN miluo_video v
    ON pr.equment_id = v.equment_id
    LEFT JOIN miluo_site s ON v.site_id=s.id
    LEFT JOIN miluo_pollutant_factor pf ON pr.factor_id=pf.id
    WHERE pr.recording_time BETWEEN #startDate# AND #endDate#
    AND pr.data_type=1
    AND s.id IN (#join(siteIds)#)
    GROUP BY s.name,pf.name
    
findTableList
===
    select table_name from information_schema.tables where table_schema='miluo'
    
 
sel
===
    select  * from miluo_site_alarm where equipment_id =#equmentId# and data_time = #recordingTime#