findHourData
===
    SELECT
           htp.*,CONCAT(s.name, '(', v.name, ')') site_name,
           s.site_code
    FROM miluo_hours_treatment_plant htp
       LEFT JOIN miluo_video v ON v.equment_id = htp.equment_id
       LEFT JOIN miluo_site s ON s.id = v.site_id
    WHERE 1 = 1
    @if(!isEmpty(siteIds)&&siteIds!=''){
        AND s.id in (#join(siteIds)#)
    @}
    @if(!isEmpty(siteId)&&siteId!=''){
        AND s.id = #siteId#
    @}
    AND htp.recording_time <= #endDate#
    AND htp.recording_time >= #startDate#
    ORDER BY htp.recording_time;


findDayData
===
    SELECT
           hdp.*,CONCAT(s.name, '(', v.name, ')') site_name,
           s.site_code
    FROM miluo_days_treatment_plant hdp
       LEFT JOIN miluo_video v ON v.equment_id = hdp.equment_id
       LEFT JOIN miluo_site s ON s.id = v.site_id
    WHERE 1 = 1
    @if(!isEmpty(siteIds)&&siteIds!=''){
        AND s.id in (#join(siteIds)#)
    @}
    @if(!isEmpty(siteId)&&siteId!=''){
        AND s.id = #siteId#
    @}
    AND hdp.recording_time <= #endDate#
    AND hdp.recording_time >= #startDate#
    ORDER BY hdp.recording_time;

findNewMinuteData
===
    SELECT mpr.*,s.`name` site_name,v.`name` equName
        FROM miluo_desktop_water_plant mpr
        LEFT JOIN miluo_video v ON v.equment_id=mpr.equment_id
        LEFT JOIN miluo_site s ON s.id = v.site_id
        WHERE s.status = 1 AND s.id=#siteId#
        ORDER BY equment_id