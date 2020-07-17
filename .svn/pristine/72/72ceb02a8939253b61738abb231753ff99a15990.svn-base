
findStandardLatestData
===
    SELECT hs.*,s.site_category station_type,s.name stationName
     FROM miluo_air_hour_statements hs
            LEFT JOIN miluo_site s ON s.site_code = hs.site_code
     WHERE
        (s.site_category = 22 OR s.site_category = 23)
      AND hs.query_time=(SELECT query_time FROM miluo_air_hour_statements ORDER BY query_time DESC LIMIT 1)
     ORDER BY hs.query_time DESC

findStandardLatestData1
===
    (SELECT *
     FROM miluo_air_hour_statements hs1
            LEFT JOIN miluo_site s1 ON s1.site_code = hs1.site_code
     WHERE s1.site_category = 22
     ORDER BY hs1.query_time DESC
     LIMIT 1)
    UNION (SELECT *
           FROM miluo_air_hour_statements hs2
                  LEFT JOIN miluo_site s2 ON s2.site_code = hs2.site_code
           WHERE s2.site_category = 23
           ORDER BY hs2.query_time DESC
           LIMIT 1)

findDesktopRankData
===
    SELECT hs.*,s.site_category station_type
     FROM miluo_air_hour_statements hs
    LEFT JOIN miluo_site s ON s.site_code = hs.site_code
     WHERE
     1=1
     AND s.site_type=2
     @if(!isEmpty(siteCodes)){
      AND s.site_code in (#join(siteCodes)#)
     @}
      AND hs.query_time=(SELECT query_time FROM miluo_air_hour_statements ORDER BY query_time DESC LIMIT 1)
     ORDER BY hs.query_time DESC

findMapMarkerByAirMicro
===
    SELECT concat(s.name, (CASE
                             WHEN s.site_type = '2' THEN concat('(', v.name, ')')
                             ELSE concat('') END)) siteName,
           s.site_type                             siteType,
           s.site_category                         siteCategory,
           s.latitude                              lat,
           s.longitude                             lng,
           ah.*,
           v.equment_id                            siteCode,
           s.id                                    siteId,
           ah.report_time          as              `query_time`,
           b.pollutant
    FROM miluo_site s
           LEFT JOIN miluo_video v ON s.id = v.site_id
           LEFT JOIN miluo_desktop_air ah
            ON v.equment_id = ah.site_code AND ah.report_time >= #date#
           LEFT JOIN miluo_exceeds_bid b
            ON b.site_code=v.equment_id AND b.exceeds_bid_date >= #date#
    WHERE s.site_type = 2
      AND s.site_category = 21
        @if(!isEmpty(siteIds)){
            AND s.id in (#join(siteIds)#)
        @}
       GROUP BY s.id

findMapMarkerByAir
===
    SELECT s.name siteName,s.site_type siteType,
        s.site_category siteCategory, s.latitude lat,
        s.longitude lng,ah.*,s.site_code siteCode,s.id siteId,b.pollutant
        FROM miluo_site s
           LEFT JOIN miluo_video v ON s.id = v.site_id
           LEFT JOIN miluo_air_hour_statements ah ON v.equment_id = ah.site_code AND ah.query_time >= (NOW() - INTERVAL 90 MINUTE)
           LEFT JOIN miluo_exceeds_bid b
                ON b.site_code=v.equment_id AND b.exceeds_bid_date >= (NOW() - INTERVAL 90 MINUTE)
        WHERE
            s.site_type=2 AND (s.site_category=22 OR s.site_category=23)
    @if(!isEmpty(siteIds)){
      AND s.id in (#join(siteIds)#)
     @}
    GROUP BY s.id

findMapMarkerByWater
===
    SELECT s.name siteName,s.site_type siteType,
    s.site_category siteCategory, s.latitude lat,
    s.longitude lng,ws.*,s.site_code siteCode,ws.date_time as 'query_time',s.id siteId
    FROM miluo_site s
    LEFT JOIN miluo_video v ON s.id=v.site_id
    LEFT JOIN miluo_desktop_water ws ON v.equment_id=ws.station_code AND ws.date_time >= #date#
    WHERE
        s.site_type=1
         @if(!isEmpty(siteIds)){
              AND s.id in (#join(siteIds)#)
             @}
    GROUP BY s.id
    
findMapMarkerByWaterPlant
===
    SELECT s.name siteName,s.site_type siteType,
    s.site_category siteCategory, s.latitude lat,
    s.longitude lng,htp.*,s.site_code siteCode,htp.recording_time as `query_time`,s.id siteId
    FROM miluo_site s
           LEFT JOIN miluo_video v ON s.id=v.site_id
           LEFT JOIN miluo_desktop_water_plant htp ON v.equment_id=htp.equment_id 
           AND htp.recording_time >= #date#
    WHERE
        s.site_type=4
         @if(!isEmpty(siteIds)){
              AND s.id in (#join(siteIds)#)
             @}
    GROUP BY s.id

findMapMarkerByPollute
===
    SELECT s.name siteName,s.site_type siteType,
        s.site_category siteCategory, s.latitude lat,
        s.longitude lng,htp.*,s.site_code siteCode,htp.recording_time as 'query_time',s.id siteId
        FROM miluo_site s
               LEFT JOIN miluo_video v ON s.id=v.site_id
               LEFT JOIN miluo_desktop_pollutant htp ON v.equment_id=htp.equment_id 
               AND htp.recording_time >= #date#
        WHERE
            s.site_type=3
             @if(!isEmpty(siteIds)){
                  AND s.id in (#join(siteIds)#)
                 @}
        GROUP BY s.id

findNewOneMicroData
===
    SELECT *
    FROM 
    miluo_air_minute_small_site s
    WHERE s.site_code = (SELECT equment_id FROM miluo_video WHERE site_id=#siteId#)
    ORDER BY s.report_time DESC
    LIMIT 1

findNewOneStandardData
===
    SELECT *
    FROM 
    miluo_air_hour_statements hs
    WHERE hs.site_code = (SELECT equment_id FROM miluo_video WHERE site_id=#siteId#)
    ORDER BY hs.query_time DESC
    LIMIT 1
        
findMicroSiteOnlineState
===
    SELECT ms.site_code, ms.report_time as 'date_time', s.id site_id, s.site_category, s.site_type,b.pollutant
    FROM miluo_site s
           INNER JOIN miluo_video v ON v.site_id = s.id
           LEFT JOIN miluo_air_minute_small_site ms ON ms.site_code = v.equment_id
                                                         AND
                                                       (ms.report_time BETWEEN #startDate# AND #endDate#)
           LEFT JOIN miluo_exceeds_bid b
                           ON b.site_code=v.equment_id AND (b.exceeds_bid_date BETWEEN #startDate# AND #endDate#)
    WHERE s.site_category=21
    @if(!isEmpty(siteIds)){
      AND s.id in (#join(siteIds)#)
     @}
    GROUP BY s.id

findStandardSiteOnlineState
===
    SELECT hs.site_code, hs.query_time as 'date_time', s.id site_id, s.site_category, s.site_type,b.pollutant
    FROM miluo_site s
           LEFT JOIN miluo_video v ON v.site_id = s.id
           LEFT JOIN miluo_air_hour_statements hs ON hs.site_code = v.equment_id
                                                       AND
                                                     (hs.query_time BETWEEN #startDate# AND #endDate#)
          LEFT JOIN miluo_exceeds_bid b
                                    ON b.site_code=v.equment_id AND (b.exceeds_bid_date BETWEEN #startDate# AND #endDate#)
    WHERE (s.site_category = 22 OR s.site_category = 23)
    @if(!isEmpty(siteIds)){
      AND s.id in (#join(siteIds)#)
     @}
    GROUP BY s.id

findPolluteSiteOnlineState
===
    SELECT rtp.recording_time as 'date_time', s.id site_id, s.site_type,b.pollutant
    FROM miluo_site s
           LEFT JOIN miluo_video v ON v.site_id = s.id
           LEFT JOIN miluo_realtime_treatment_plant rtp ON rtp.equment_id = v.equment_id
                                                       AND
                                                     (rtp.recording_time BETWEEN #startDate# AND #endDate#)
         LEFT JOIN miluo_exceeds_bid b
                                    ON b.site_code=v.equment_id AND (b.exceeds_bid_date BETWEEN #startDate# AND #endDate#)
    WHERE s.site_type=3
    @if(!isEmpty(siteIds)){
      AND s.id in (#join(siteIds)#)
     @}
    GROUP BY s.id

findWaterSiteOnlineState
===
    SELECT ws.date_time as 'date_time', s.id site_id, s.site_type,b.pollutant
    FROM miluo_site s
           LEFT JOIN miluo_video v ON v.site_id = s.id
           LEFT JOIN miluo_water_site ws ON ws.station_code = v.equment_id
                                                       AND
                                                     (ws.date_time BETWEEN #startDate# AND #endDate#)
         LEFT JOIN miluo_exceeds_bid b
                                ON b.site_code=v.equment_id AND (b.exceeds_bid_date BETWEEN #startDate# AND #endDate#)
    WHERE s.site_type=1
    @if(!isEmpty(siteIds)){
      AND s.id in (#join(siteIds)#)
     @}
    GROUP BY s.id


findMicroSiteOnlineStateByMarker
===
    SELECT ms.site_code, ms.report_time as 'date_time', s.id site_id, s.site_category, s.site_type,
            concat(s.name, (CASE
                                 WHEN s.site_type = '2' THEN concat('(', v.name, ')')
                                 ELSE concat('') END)) siteName,
               s.site_type                             siteType,
               s.site_category                         siteCategory,
               s.latitude                              lat,
               s.longitude                             lng
    FROM miluo_site s
           INNER JOIN miluo_video v ON v.site_id = s.id
           LEFT JOIN miluo_air_minute_small_site ms ON ms.site_code = v.equment_id
                                                         AND
                                                       (ms.report_time BETWEEN #startDate# AND #endDate#)
    WHERE s.site_category=21
    @if(!isEmpty(siteIds)){
      AND s.id in (#join(siteIds)#)
     @}
    GROUP BY s.id

findStandardSiteOnlineStateByMarker
===
    SELECT hs.site_code, hs.query_time as 'date_time', s.id site_id, s.site_category, s.site_type
                ,concat(s.name, (CASE
                                     WHEN s.site_type = '2' THEN concat('(', v.name, ')')
                                     ELSE concat('') END)) siteName,
                   s.site_type                             siteType,
                   s.site_category                         siteCategory,
                   s.latitude                              lat,
                   s.longitude                             lng
    FROM miluo_site s
           LEFT JOIN miluo_video v ON v.site_id = s.id
           LEFT JOIN miluo_air_hour_statements hs ON hs.site_code = v.equment_id
                                                       AND
                                                     (hs.query_time BETWEEN #startDate# AND #endDate#)
    WHERE (s.site_category = 22 OR s.site_category = 23)
    @if(!isEmpty(siteIds)){
      AND s.id in (#join(siteIds)#)
     @}
    GROUP BY s.id

findPolluteSiteOnlineStateByMarker
===
    SELECT rtp.recording_time as 'date_time', s.id site_id, s.site_type
        ,concat(s.name, (CASE
                             WHEN s.site_type = '2' THEN concat('(', v.name, ')')
                             ELSE concat('') END)) siteName,
           s.site_type                             siteType,
           s.site_category                         siteCategory,
           s.latitude                              lat,
           s.longitude                             lng
    FROM miluo_site s
           LEFT JOIN miluo_video v ON v.site_id = s.id
           LEFT JOIN miluo_realtime_treatment_plant rtp ON rtp.equment_id = v.equment_id
                                                       AND
                                                     (rtp.recording_time BETWEEN #startDate# AND #endDate#)
    WHERE s.site_type=3
    @if(!isEmpty(siteIds)){
      AND s.id in (#join(siteIds)#)
     @}
    GROUP BY s.id

findWaterSiteOnlineStateByMarker
===
    SELECT ws.date_time as 'date_time', s.id site_id, s.site_type
        ,concat(s.name, (CASE
         WHEN s.site_type = '2' THEN concat('(', v.name, ')')
         ELSE concat('') END)) siteName,
           s.site_category                         siteCategory,
           s.latitude                              lat,
           s.longitude                             lng
    FROM miluo_site s
           LEFT JOIN miluo_video v ON v.site_id = s.id
           LEFT JOIN miluo_water_site ws ON ws.station_code = v.equment_id
                                                       AND
                                                     (ws.date_time BETWEEN #startDate# AND #endDate#)
    WHERE s.site_type=1
    @if(!isEmpty(siteIds)){
      AND s.id in (#join(siteIds)#)
     @}
    GROUP BY s.id

findMarkerOnlineStateByType1
===
    SELECT s.id site_id, b.pollutant
    FROM miluo_site s
           LEFT JOIN miluo_video v ON v.site_id = s.id
           LEFT JOIN miluo_exceeds_bid b
             ON b.site_code = v.equment_id AND (b.exceeds_bid_date BETWEEN #startDate# AND #endDate#)
    WHERE 1=1
      AND b.pollutant IS NOT NULL
      AND s.site_type = #siteType#
    GROUP BY s.id

findMarkerOnlineStateByType
===
    SELECT equipment_id,pollutant FROM miluo_site_alarm 
    WHERE data_time >= #beforeTwentyMinuteDateStr#
    AND alarm_type = '2-1'
    AND status = 0
    GROUP BY equipment_id
    UNION
    SELECT equipment_id,pollutant FROM miluo_site_alarm 
    WHERE data_time >= #beforeTwentyMinuteDateStr#
    AND alarm_type = '4-1'
    AND status = 0
    GROUP BY equipment_id
    UNION
    SELECT equipment_id,pollutant FROM miluo_site_alarm 
    WHERE data_time > #beforeTenMinuteStr#
    AND alarm_type = '3-1'
    AND status = 0
    GROUP BY equipment_id
    UNION
    SELECT equipment_id,pollutant FROM miluo_site_alarm 
    WHERE data_time >= #beforeTenMinuteStr#
    AND alarm_type = '1-1'
    AND status = 0
    GROUP BY equipment_id


findWaterSiteOnlineStateByMarkerAll
===
    SELECT
    	ms.report_time AS 'date_time',
    	s.id site_id,
    	v.equment_id,
    	s.site_type,
    	s.name site_name,
    	s.site_category,
    	s.latitude lat,
    	s.longitude lng
    FROM
    	miluo_site s
    	INNER JOIN miluo_video v ON v.site_id = s.id
    	LEFT JOIN (SELECT m.report_time,m.site_code FROM miluo_desktop_air m 
    	WHERE m.report_time >= #beforeTwentyMinuteDateStr# GROUP BY m.site_code) ms ON ms.site_code = v.equment_id 
    
    WHERE
    	s.site_category = 21 
    	@if(!isEmpty(siteIds)){
        AND s.id in (#join(siteIds)#)
       @}
    UNION
    SELECT p.recording_time AS 'date_time',
           s.id site_id,
           v.equment_id,
           s.site_type,
           s.name site_name,
           s.site_category,
           s.latitude lat,
           s.longitude lng
    FROM miluo_site s
           INNER JOIN miluo_video v ON v.site_id = s.id
           LEFT JOIN (SELECT rtp.recording_time,rtp.equment_id FROM miluo_desktop_water_plant rtp
           WHERE rtp.recording_time >= #beforeTwentyMinuteDateStr#) p ON p.equment_id = v.equment_id
                                                             
    WHERE s.site_type = 4
      @if(!isEmpty(siteIds)){
        AND s.id in (#join(siteIds)#)
       @}
    UNION
    SELECT p.recording_time AS 'date_time',
           s.id site_id,
           v.equment_id,
           s.site_type,
           s.name site_name,
           s.site_category,
           s.latitude lat,
           s.longitude lng
    FROM miluo_site s
           INNER JOIN miluo_video v ON v.site_id = s.id
           LEFT JOIN (SELECT mpr.recording_time,mpr.equment_id FROM miluo_desktop_pollutant mpr
           WHERE mpr.recording_time >= #beforeTenMinuteStr#
           GROUP BY mpr.equment_id) p ON p.equment_id = v.equment_id
           WHERE s.site_type = 3
           @if(!isEmpty(siteIds)){
           AND s.id in (#join(siteIds)#)
          @}
    UNION
    SELECT hs.query_time AS 'date_time',
           s.id site_id,
           v.equment_id,
           s.site_type,
           s.name site_name,
           s.site_category,
           s.latitude lat,
           s.longitude lng
    FROM miluo_site s
           LEFT JOIN miluo_video v ON v.site_id = s.id
           LEFT JOIN miluo_air_hour_statements hs ON hs.site_code = v.equment_id
                                                       AND
                                                     (hs.query_time > #beforeTwoHourDateStr#)
    WHERE (s.site_category = 22 OR s.site_category = 23)
      @if(!isEmpty(siteIds)){
        AND s.id in (#join(siteIds)#)
       @}
    UNION
    SELECT p.date_time AS 'date_time',
           s.id site_id,
           v.equment_id,
           s.site_type,
           s.name site_name,
           s.site_category,
           s.latitude lat,
           s.longitude lng
    FROM miluo_site s
           LEFT JOIN miluo_video v ON v.site_id = s.id
           LEFT JOIN 
           (SELECT ws.date_time,ws.station_code FROM miluo_desktop_water ws
           WHERE ws.date_time >= #beforeTenMinuteStr# GROUP BY ws.station_code) p ON p.station_code = v.equment_id 
    WHERE s.site_type=1
      @if(!isEmpty(siteIds)){
        AND s.id in (#join(siteIds)#)
       @}

findMapMarkerNotState
===
    SELECT s.id siteId, s.name siteName, site_type
        , site_category, IFNULL(s.longitude,'') lng, IFNULL(s.latitude,'') lat
        , s.site_code, 'only' type
    FROM miluo_site s
    WHERE s.status = 1
      @if(!isEmpty(siteIds)){
      AND s.id in (#join(siteIds)#)
     @}
