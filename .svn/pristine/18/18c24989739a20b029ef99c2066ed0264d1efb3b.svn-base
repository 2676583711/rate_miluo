findAllWaterWarn
===
    select site.id as siteId,site.name as siteName,site.longitude lng,site.latitude lat,site.section_attr site_type,data.date_time,MAX(data.water_sort) water_sort from miluo_water_auto_site site left join miluo_water_month_data data  on data.site_id=site.id where STR_TO_DATE(data.date_time,'%Y-%m')=STR_TO_DATE(#dateTime#,'%Y-%m')
     GROUP BY data.site_id UNION     select site.id as siteId,site.name as siteName,site.longitude lng,site.latitude lat,site.section_attr site_type,data.date_time,MAX(data.water_sort) water_sort from miluo_water_auto_site site left join miluo_water_month_data data  on data.site_id=site.id where STR_TO_DATE(data.date_time,'%Y-%m')=STR_TO_DATE(#yesTime#,'%Y-%m')
     GROUP BY data.site_id