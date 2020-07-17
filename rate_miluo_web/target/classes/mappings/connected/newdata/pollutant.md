getPollutantNewestData
===

    SELECT 
    d.recording_time,s.name siteName,
    ROUND(d.exhaust,1) exhaust,
    ROUND(d.dust,1) dust,
    ROUND(d.o2,1) o2,
    ROUND(d.so2,1) so2,
    ROUND(d.nox,1) nox,
    ROUND(d.flow_velocity,1) flow_velocity,
    ROUND(d.temp) temp,
    ROUND(d.press,2) press
    FROM `miluo_desktop_pollutant` d
    LEFT JOIN `miluo_video` v ON v.equment_id = d.equment_id
    LEFT JOIN `miluo_site` s ON s.id = v.site_id
    WHERE s.status = 1
    @if(!isEmpty(siteIds)){
        AND s.id in (#join(siteIds)#)
    @}
    GROUP BY d.equment_id
    ORDER BY recording_time DESC,s.`name`