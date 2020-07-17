findHourData
===
    SELECT
        hs.* ,
         concat(s.name, (CASE
                         WHEN s.site_category = '22' OR s.site_category = '23' THEN concat('(标准站)') ELSE concat('') END)) station_name
    FROM miluo_air_hour_statements hs
    LEFT JOIN miluo_video v ON v.equment_id=hs.site_code
    LEFT JOIN miluo_site s ON s.id = v.site_id
    WHERE
    1=1
    @if(!isEmpty(siteIds)&&siteIds!=''){
     AND s.id in (#join(siteIds)#)
    @}
    @if(!isEmpty(siteId)&&siteId!=''){
        AND s.id = #siteId#
    @}
    AND hs.query_time <= #endDate# AND hs.query_time >= #startDate#
    ORDER BY hs.query_time

findDayData
===
    SELECT
        ds.* ,
         concat(s.name, (CASE
                          WHEN s.site_category = '22' OR s.site_category = '23' THEN concat('(标准站)') ELSE concat('') END)) station_name
    FROM miluo_air_daily_statements ds
    LEFT JOIN miluo_video v ON v.equment_id=ds.site_code
    LEFT JOIN miluo_site s ON s.id = v.site_id
    WHERE
    1=1
    @if(!isEmpty(siteIds)&&siteIds!=''){
     AND s.id in (#join(siteIds)#)
    @}
    @if(!isEmpty(siteId)&&siteId!=''){
        AND s.id = #siteId#
    @}
    AND ds.query_time <= #endDate# AND ds.query_time >= #startDate#
    ORDER BY ds.query_time

findWindData
===
    SELECT
        hs.wd,hs.ws,hs.id,hs.query_time,s.name station_name
    FROM miluo_air_hour_statements hs
    LEFT JOIN miluo_video v ON v.equment_id=hs.site_code
    LEFT JOIN miluo_site s ON s.id = v.site_id
    WHERE
    1=1
    AND s.site_category=21
    @if(!isEmpty(siteIds)&&siteIds!=''){
     AND s.id in (#join(siteIds)#)
    @}
    AND hs.query_time <= #endDate# AND hs.query_time >= #startDate#
    ORDER BY hs.query_time

findChangeData
===
    SELECT
        hs.* ,
         concat(s.name, (CASE
                         WHEN s.site_category = '22' OR s.site_category = '23' THEN concat('(标准站)') ELSE concat('') END)) station_name
    FROM miluo_air_hour_statements hs
    LEFT JOIN miluo_video v ON v.equment_id=hs.site_code
    LEFT JOIN miluo_site s ON s.id = v.site_id
    WHERE
    1=1
    @if(!isEmpty(siteIds)&&siteIds!=''){
     AND s.id in (#join(siteIds)#)
    @}
    @if(!isEmpty(siteId)&&siteId!=''){
     AND s.id = #siteId#
    @}
    AND hs.query_time <= #endDate# AND hs.query_time >= #startDate#
    ORDER BY hs.query_time

findYearLevelDay
===
    SELECT id,query_time,aq_type,count(aq_type) dayNum
    FROM miluo_air_daily_statements hs
    LEFT JOIN miluo_video v ON v.equment_id=hs.site_code
    LEFT JOIN miluo_site s ON s.id = v.site_id
    WHERE s.id=#siteId#
    AND query_time BETWEEN #startDate# AND #endDate#
    GROUP BY aq_type