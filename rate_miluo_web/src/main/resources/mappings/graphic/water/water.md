findMinuteData
===
    SELECT
            ws.* ,s.name site_name,
            CONCAT(s.name, '(', v.name, ')') site_name
        FROM miluo_water_site ws
        LEFT JOIN miluo_video v ON v.equment_id = ws.station_code
        LEFT JOIN miluo_site s ON s.id = v.site_id
        WHERE
        1=1
        @if(!isEmpty(siteIds) && siteIds != ''){
         AND s.id in (#join(siteIds)#)
        @}
        AND ws.date_time <= #endDate# AND ws.date_time >= #startDate#
        ORDER BY ws.date_time
findHourData
===
    SELECT
        ws.* ,s.name site_name,
        CONCAT(s.name, '(', v.name, ')') site_name
    FROM miluo_hour_water_site ws
    LEFT JOIN miluo_video v ON v.equment_id = ws.station_code
    LEFT JOIN miluo_site s ON s.id = v.site_id
    WHERE
    1=1
    @if(!isEmpty(siteIds) && siteIds!=''){
     AND s.id in (#join(siteIds)#)
    @}
    @if(!isEmpty(siteId) && siteId!=''){
     AND s.id = #siteId#
    @}
    AND ws.date_time <= #endDate# AND ws.date_time >= #startDate#
    ORDER BY ws.date_time

findDayData
===
    SELECT
        ws.* ,s.name site_name,
        CONCAT(s.name, '(', v.name, ')') site_name
    FROM miluo_days_water_site ws
    LEFT JOIN miluo_video v ON v.equment_id = ws.station_code
    LEFT JOIN miluo_site s ON s.id = v.site_id
    WHERE
    1=1
    @if(!isEmpty(siteIds)&&siteIds!=''){
     AND s.id in (#join(siteIds)#)
    @}
    AND ws.date_time <= #endDate# AND ws.date_time >= #startDate#
    ORDER BY ws.date_time
