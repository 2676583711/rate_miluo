findSiteTree
===
    SELECT *
    FROM miluo_site
    WHERE status=1
    @if(!isEmpty(siteCodes)){
     AND id IN (#join(siteCodes)#)
    @}
    @if(!isEmpty(siteType)&&siteType!=""){
     AND site_type = #siteType#
    @}

findSiteList
===
    SELECT *
        FROM miluo_site
        WHERE status=1
        @if(!isEmpty(siteCodes)){
         AND id IN (#join(siteCodes)#)
        @}
        @if(!isEmpty(siteType)&&siteType!=""){
         AND site_type = #siteType#
        @}
        
        
getMicro
===             
               select #page("x.*")# from (
                select 
                @pageTag(){
                t.name,t.id,t.site_type,t.longitude,t.latitude,t.site_code,t1.equment_id
                @}   
                from miluo_site t 
                 left join miluo_video t1
                 on t.id=t1.site_id
                 left join miluo_air_hour_statements t2
                 on t1.equment_id=t2.site_code
                where 1=1 and site_type =2  and  status=1 	
                @if(!isEmpty(siteIds)&&siteIds!=''){
                  and t.id in (#join(siteIds)#)
                @}else{
                  and t.id in (#join(siteIds2)#)
                @}
                group by t2.site_code)x
        
getWaterAutoStation
===      
             select #page("x.*")# from (
             select 
             @pageTag(){
             t.name,t.id,t.site_type,t.longitude,t.latitude,t.site_code,t2.station_code,t1.equment_id,t1.name videoName
             @} 
             from miluo_site  t
             left join miluo_video t1
             on t.id=t1.site_id
             left join miluo_water_site t2
             on t1.equment_id=t2.station_code
             where t.site_type =1  and  t.status=1
              @if(!isEmpty(siteIds)&&siteIds!=''){
               and t.id in (#join(siteIds)#)
             @}else{
               and t.id in (#join(siteIds2)#)
             @}
             and  t2.station_code is not null
             GROUP BY t2.station_code  ) x
   
getPolluteWaterStation
===                 
             select #page("x.*")# from ( 
			 select 
             @pageTag(){
             t.name,t.id,t.site_type,t.longitude,t.latitude,t.site_code,t2.equment_id,t1.name videoName
             @}
             from miluo_site  t
             left join miluo_video t1
             on t.id=t1.site_id
             left join miluo_hours_treatment_plant t2
             on t1.equment_id=t2.equment_id
             where t.site_type =4  and  t.status=1
             @if(!isEmpty(siteIds)&&siteIds!=''){
               and t.id in (#join(siteIds)#)
             @}else{
               and t.id in (#join(siteIds2)#)
             @}
             and  t2.equment_id is not null
             GROUP BY t2.equment_id  ) x
getAirList
===
       
       		select t.name,t.id,t.site_code from miluo_site t where t.site_type =2 and t.status=1 
       		
getPolluteWaterSites
===         
           select t.name,t.id,t.site_code from miluo_site t where t.site_type =3 and t.status=1 

getWaterSites
===        
           select t.name,t.id,t.site_code from miluo_site t where t.site_type =1 and t.status=1 
              		
getSiteList
===
        SELECT 
                sp.site_code ,sp.user_id, s.*
                 FROM miluo_site_power sp
                LEFT JOIN miluo_site s ON s.id=sp.site_code
                WHERE sp.user_id=(SELECT u.user_id FROM sys_user_role u WHERE 1=1
        @if(!isEmpty(userId)&&userId!=''){
          and  u.user_id=#userId#
         @}
        )
        
getSiteListByType
===
        SELECT 
                sp.site_code ,sp.user_id, s.*
                 FROM miluo_site_power sp
                LEFT JOIN miluo_site s ON s.id=sp.site_code
                WHERE sp.user_id=(SELECT u.user_id FROM sys_user_role u WHERE 1=1
        @if(!isEmpty(userId)&&userId!=''){
          and  u.user_id=#userId#
         @}
        )
        @if(!isEmpty(siteType) && siteType != ''){
          and s.site_type = #siteType#
        @}
         
        
        
getSiteListByPage
===
        select
        @pageTag(){
            *
        @}
        from miluo_site
       where status = 1
        @if(!isEmpty(siteIds) && siteIds !=""){
          and id in (#join(siteIds)#)
        @}
        @if(!isEmpty(positionName)&& positionName !=""){
          and name like #'%'+positionName+'%'#
        @}             	
                                                       	
queryListByUserId
===
    SELECT
    	s1.`id`,
    	s1.`name`,
    	s2.date_time siteStatus,
    	s1.`site_type`,
    	s1.`site_category`
    FROM
    	(
    SELECT
    	s.id,
    	s.`name`,
    	s.`site_category`,
    	s.`site_type` 
    FROM
    	miluo_site_power sp
    	LEFT JOIN miluo_site s ON s.id = sp.site_code 
    WHERE
    	sp.user_id = #userId#
    	AND s.site_type = 1 
    	) s1
    	LEFT JOIN (
    SELECT
    	t1.date_time,
    	t2.site_id 
    FROM
    	miluo_hour_water_site t1
    	LEFT JOIN miluo_video t2 ON t1.station_code = t2.equment_id 
    WHERE
    	t1.date_time = #time#
    	) s2 ON s1.id = s2.site_id UNION
    SELECT
    	s1.`id`,
    	s1.`name`,
    	s2.query_time siteStatus,
    	s1.`site_type`,
    	s1.`site_category`
    FROM
    	(
    SELECT
    	s.id,
    	s.`name`,
    	s.`site_category`,
    	s.`site_type` 
    FROM
    	miluo_site_power sp
    	LEFT JOIN miluo_site s ON s.id = sp.site_code 
    WHERE
    	sp.user_id = #userId#
    	AND s.site_type = 2 
    	) s1
    	LEFT JOIN (
    SELECT
    	t1.query_time,
    	t2.site_id 
    FROM
    	miluo_air_hour_statements t1
    	LEFT JOIN miluo_video t2 ON t1.site_code = t2.equment_id 
    WHERE
    	t1.query_time = #time# 
    	) s2 ON s1.id = s2.site_id UNION
    SELECT
    	s1.`id`,
    	s1.`name`,
    	s2.recording_time siteStatus,
    	s1.`site_type`,
    	s1.`site_category`
    FROM
    	(
    SELECT
    	s.id,
    	s.`name`,
    	s.`site_category`,
    	s.`site_type` 
    FROM
    	miluo_site_power sp
    	LEFT JOIN miluo_site s ON s.id = sp.site_code 
    WHERE
    	sp.user_id = #userId#
    	AND s.site_type = 4
    	) s1
    	LEFT JOIN (
    SELECT
    	t1.recording_time,
    	t2.site_id 
    FROM
    	miluo_hours_treatment_plant t1
    	LEFT JOIN miluo_video t2 ON t1.equment_id = t2.equment_id 
    WHERE
    	t1.recording_time = #time#
    	) s2 ON s1.id = s2.site_id UNION 
    SELECT 
        s1.`id`,
        s1.`name`,
        s2.recording_time siteStatus,
        s1.`site_type`,
        s1.`site_category`
    FROM
        (
    SELECT
        s.id,
        s.`name`,
        s.`site_category`,
        s.`site_type` 
    FROM
        miluo_site_power sp
        LEFT JOIN miluo_site s ON s.id = sp.site_code 
    WHERE
        sp.user_id = #userId#
        AND s.site_type = 3
        ) s1
        LEFT JOIN (
    SELECT
        t1.recording_time,
        t2.site_id 
    FROM
        miluo_pollutant_hour t1
        LEFT JOIN miluo_video t2 ON t1.equment_id = t2.equment_id 
    WHERE
        t1.`data_type` = "1"
        AND t1.recording_time = #time#
        ) s2 ON s1.id = s2.site_id

queryAllList
===
    SELECT
        s1.`id`,
        s1.`name`,
        s2.date_time siteStatus,
        s1.`site_type`,
        s1.`site_category`
    FROM
        (
    SELECT
        s.id,
        s.`name`,
        s.`site_category`,
        s.`site_type` 
    FROM miluo_site s
    WHERE s.site_type = 1 
        ) s1
        LEFT JOIN (
    SELECT
        t1.date_time,
        t2.site_id 
    FROM
        miluo_hour_water_site t1
        LEFT JOIN miluo_video t2 ON t1.station_code = t2.equment_id 
    WHERE
        t1.date_time = #time#
        ) s2 ON s1.id = s2.site_id UNION
    SELECT
        s1.`id`,
        s1.`name`,
        s2.query_time siteStatus,
        s1.`site_type`,
        s1.`site_category`
    FROM
        (
    SELECT
        s.id,
        s.`name`,
        s.`site_category`,
        s.`site_type` 
    FROM miluo_site s 
    WHERE s.site_type = 2 
        ) s1
        LEFT JOIN (
    SELECT
        t1.query_time,
        t2.site_id 
    FROM
        miluo_air_hour_statements t1
        LEFT JOIN miluo_video t2 ON t1.site_code = t2.equment_id 
    WHERE
        t1.query_time = #time# 
        ) s2 ON s1.id = s2.site_id UNION
    SELECT
        s1.`id`,
        s1.`name`,
        s2.recording_time siteStatus,
        s1.`site_type`,
        s1.`site_category`
    FROM
        (
    SELECT
        s.id,
        s.`name`,
        s.`site_category`,
        s.`site_type` 
    FROM miluo_site s
    WHERE s.site_type = 4
        ) s1
        LEFT JOIN (
    SELECT
        t1.recording_time,
        t2.site_id 
    FROM
        miluo_hours_treatment_plant t1
        LEFT JOIN miluo_video t2 ON t1.equment_id = t2.equment_id 
    WHERE
        t1.recording_time = #time#
        ) s2 ON s1.id = s2.site_id UNION 
    SELECT 
        s1.`id`,
        s1.`name`,
        s2.recording_time siteStatus,
        s1.`site_type`,
        s1.`site_category`
    FROM
        (
    SELECT
        s.id,
        s.`name`,
        s.`site_category`,
        s.`site_type` 
    FROM miluo_site s 
    WHERE s.site_type = 3
        ) s1
        LEFT JOIN (
    SELECT
        t1.recording_time,
        t2.site_id 
    FROM
        miluo_pollutant_hour t1
        LEFT JOIN miluo_video t2 ON t1.equment_id = t2.equment_id 
    WHERE
        t1.`data_type` = "1"
        AND t1.recording_time = #time#
        ) s2 ON s1.id = s2.site_id  
                                                             	
queryListByUserIdAndSiteType
===
    SELECT
    	sp.site_code,
    	sp.user_id,
    	s.* 
    FROM
    	miluo_site_power sp
    	LEFT JOIN miluo_site s ON s.id = sp.site_code
    WHERE
    	sp.user_id = (
    SELECT
    	u.user_id 
    FROM
    	sys_user_role u 
    WHERE
    	1 = 1  
    	@if(!isEmpty(userId) && userId != ''){
    	    AND u.user_id = #userId#
    	@}
    	@if(!isEmpty(type) && type != ''){
    	    AND s.site_type = #type#
    	@}
    	)

findSiteInfo
===
    SELECT name      site_name,
           id        site_id,
           site_code,
           longitude lng,
           latitude  lat,
           site_category,
           site_type,
           address,
           fzr,
           phone
    FROM miluo_site
    WHERE status = 1
      AND id = #siteCode#

findSiteInfoById
===
    SELECT name      site_name,
           id        site_id,
           site_code,
           longitude lng,
           latitude  lat,
           site_category,
           site_type,
           address,
           fzr,
           phone
    FROM miluo_site
    WHERE status = 1
      AND id = #siteId#


queryAirSiteInfo
===
    SELECT
    	t1.`name` site_name,
    	t1.id site_id,
    	t1.site_code,
    	t1.longitude lng,
    	t1.latitude lat,
    	t1.site_category,
    	t1.site_type,
    	t1.address,
    	t1.fzr,
    	phone
    FROM
    	miluo_site t1
    	LEFT JOIN miluo_video t2 ON t1.id = t2.site_id
    WHERE
    	STATUS = 1
    	AND t2.equment_id = #siteCode#
    	
    	
querySiteByUser
===
    select site_code from miluo_site_power where user_id = #userId#
    
querySiteByUserAndType
===
    select p.site_code from miluo_site_power p left join miluo_site s 
    on s.id = p.site_code 
    where p.user_id = #userId#
    and  s.site_type = #type#
    
getAllSite
===
    select * from miluo_site where status = 1
    
getVideoList
===
    SELECT v.`id`,v.`site_id`,v.`vidicon_name`,t1.`name` AS site_name ,t1.`site_type`
    FROM `miluo_vidicon` v LEFT JOIN 
    (SELECT s.id,s.`name`,s.`site_type` FROM `miluo_site_power` p 
    LEFT JOIN `miluo_site` s ON s.`id` = p.`site_code` 
    WHERE p.`user_id` = #userId# AND s.`id` IS NOT NULL AND s.`status` = 1) t1
    ON v.`site_id` = t1.id
    WHERE t1.name IS NOT NULL
    
findVideoAllList
===   

    SELECT * FROM `miluo_vidicon`