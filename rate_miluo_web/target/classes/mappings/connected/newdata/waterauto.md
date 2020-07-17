sample
===
* 注释

	select #use("cols")# from miluo_water_site  where  #use("condition")#

minuteWaterInter
===
         
    SELECT     
     t.name,t1.*
    FROM 
    (SELECT station_code,date_time,sw,ph,kmn,ddl,zd,rjy,tp,nh3_n,shen,pb,cd FROM miluo_water_site) t1
    LEFT JOIN miluo_video t2
    ON t2.equment_id = t1.station_code
    LEFT JOIN (SELECT `id`,`name` FROM miluo_site)t
    ON t.id = t2.site_id
     WHERE t1.station_code = #siteCode#
     AND t1.date_time BETWEEN #startTime# AND #endTime#
     ORDER BY t1.date_time DESC 

waterAutoLatest
===
    	 
		    select  
            @pageTag(){
            t2.name,t2.id,t1.sw,t1.plc,t1.zd,t1.ddl,t1.codmn,t1.pb,t1.rjy,t1.ph,t1.shen,t1.nh3_n,t1.comprehensive_toxicity,t1.tp,t1.chromium,t1.cd,t1.kmn,t1.temp,t1.humi,
            t1.water_sort,t1.k03,t1.k04,t1.yls,t1.llz,t1.date_time,t1.station_code,t3.name videoName
            @}
            from miluo_site t2
            left join miluo_video t3
            on t2.id=t3.site_id
            left join (	select t7.sw,t7.plc,t7.zd,t7.ddl,t7.codmn,t7.pb,t7.rjy,t7.ph,t7.shen,t7.nh3_n,t7.comprehensive_toxicity,t7.tp,t7.chromium,t7.cd,t7.kmn,t7.temp,t7.humi,
            t7.water_sort,t7.k03,t7.k04,t7.yls,t7.llz,t7.date_time,t7.station_code
            from 
            (select sw,plc,zd,ddl,codmn,pb,rjy,ph,shen,nh3_n,comprehensive_toxicity,tp,chromium,cd,kmn,temp,humi,water_sort,k03,k04,yls,llz,date_time,station_code
            from miluo_water_site ORDER BY date_time desc limit 1000)t7
            GROUP BY t7.station_code ) t1 
            on t1.station_code=t3.equment_id
            where t2.status=1 and t2.site_type =1 and
            t1. station_code is not null
          @if(!isEmpty(siteIds)&&siteIds!=''){
          and t2.id in (#join(siteIds)#)
          @}else{
          and t2.id in (#join(siteIds2)#)
          @}
          ORDER BY t1.date_time desc
waterAutoIds
===

	        select  GROUP_CONCAT(s.id) as idList from miluo_water_site s
            where s.date_time BETWEEN  #beginTime#  and #endTime# 		
            group by s.station_code



waterAutoRate
===     
     
    select t.station_code,count(t.sw) sw,count(t.plc) plc,count(t.zd) zd ,count(t.ddl) ddl,count(t.codmn) codmn,count(t.pb) pb,
    count(t.rjy) rjy,count(t.ph) ph,count(t.shen) shen,count(t.nh3_n) nh3_n,count(t.comprehensive_toxicity) comprehensive_toxicity,
    count(t.tp) tp,count(t.chromium) chromium,count(t.water_sort) water_sort,count(t.k03) k03,count(t.k04) k04,t1.name videoName
    from  
    (select *  from miluo_water_site where id in (#join(ids)#)) t 
    left join miluo_video t1
    on t1.equment_id =t.equment_id
     where t.date_time BETWEEN  #beginTime# and #endTime# 
waterAutoDetails  
===
    
     select 
     @pageTag(){             
     t.name,t1.date_time, t.site_code,t1.station_code,t1.sw,t1.plc,t1.zd,t1.ddl,t1.codmn,t1.pb,
     t1.rjy,t1.ph,t1.shen,t1.nh3_n,t1.comprehensive_toxicity,
     t1.tp,t1.chromium,t1.water_sort,t1.k03,t1.k04,t1.date_time,t2.name videoName
     @}       
     from 
     (select 
     station_code,sw,plc,zd ,ddl,codmn,pb,
     rjy,ph,shen,nh3_n,comprehensive_toxicity,
     tp,chromium,water_sort,k03,k04,date_time
     from miluo_water_site
     where date_time >=(now()-INTERVAL 24 hour)
     ) t1
     left join miluo_video t2
     on t2.equment_id=t1.station_code
     left join 
     (select site_code,name,id from miluo_site)t
     on t.id=t2.site_id
      where t1.station_code=#siteCode#
     ORDER BY t1.date_time desc 
cols
===
	id,station_code,date_time,sw,plc,zd,ddl,codmn,pb,rjy,ph,shen,nh3_n,comprehensive_toxicity,tp,chromium,water_sort,k03,k04

updateSample
===
	
	id=#id#,station_code=#stationCode#,date_time=#dateTime#,sw=#sw#,plc=#plc#,zd=#zd#,ddl=#ddl#,codmn=#codmn#,pb=#pb#,rjy=#rjy#,ph=#ph#,shen=#shen#,nh3_n=#nh3N#,comprehensive_toxicity=#comprehensiveToxicity#,tp=#tp#,chromium=#chromium#,water_sort=#waterSort#,k03=#k03#,k04=#k04#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(stationCode)){
	 and station_code=#stationCode#
	@}
	@if(!isEmpty(dateTime)){
	 and date_time=#dateTime#
	@}
	@if(!isEmpty(sw)){
	 and sw=#sw#
	@}
	@if(!isEmpty(plc)){
	 and plc=#plc#
	@}
	@if(!isEmpty(zd)){
	 and zd=#zd#
	@}
	@if(!isEmpty(ddl)){
	 and ddl=#ddl#
	@}
	@if(!isEmpty(codmn)){
	 and codmn=#codmn#
	@}
	@if(!isEmpty(pb)){
	 and pb=#pb#
	@}
	@if(!isEmpty(rjy)){
	 and rjy=#rjy#
	@}
	@if(!isEmpty(ph)){
	 and ph=#ph#
	@}
	@if(!isEmpty(shen)){
	 and shen=#shen#
	@}
	@if(!isEmpty(nh3N)){
	 and nh3_n=#nh3N#
	@}
	@if(!isEmpty(comprehensiveToxicity)){
	 and comprehensive_toxicity=#comprehensiveToxicity#
	@}
	@if(!isEmpty(tp)){
	 and tp=#tp#
	@}
	@if(!isEmpty(chromium)){
	 and chromium=#chromium#
	@}
	@if(!isEmpty(waterSort)){
	 and water_sort=#waterSort#
	@}
	@if(!isEmpty(k03)){
	 and k03=#k03#
	@}
	@if(!isEmpty(k04)){
	 and k04=#k04#
	@}
