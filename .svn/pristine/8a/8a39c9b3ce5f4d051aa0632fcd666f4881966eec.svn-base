hourInter
===         

    SELECT       
     t.name,t1.*
     FROM 
     (SELECT `id`,`name` FROM miluo_site ) t
     LEFT JOIN miluo_video t2 
     ON t.id = t2.site_id
     LEFT JOIN
     (SELECT site_code,query_time,aqi,tsp,pm10,pm25,no2,so2,o3one_hour,co,primary_ep,aq_type,aq_degree,noise,ws,wd,temp,humi,press FROM miluo_air_hour_statements) t1
     ON t2.equment_id = t1.site_code
     WHERE t1.site_code = #siteCode#
     AND t1.query_time BETWEEN #startTime# AND #endTime#
     ORDER BY t1. query_time DESC
        

findDetails   
===       
        
                select 
                @pageTag(){ 
                t.name,t1.site_code,t1.query_time report_time,t1.pm10,t1.pm25,t1.so2,t1.co,t1.no2,t1.o3one_Hour,t1.humi,
                t1.ws,t1.wd,t1.temp,t1.press,t1.tsp,t1.noise
                @}
                from 
                (select site_code,name,id from miluo_site )t
                left join miluo_video t2 
                on t.id=t2.site_id
                left join
                (select 
                query_time,pm10,pm25,so2,co,no2,o3one_Hour,humi,
                ws,wd,temp,press,site_code,tsp,noise
                from miluo_air_hour_statements
                where query_time >=(now()-INTERVAL 24 hour)
                 ) t1
                on t2.equment_id=t1.site_code
                where t1.site_code=#siteCode#
                ORDER BY query_time desc                              		

         	  
dataRate 
===
    	 
	        select 
	        t.site_code,count(t.no2) no2,count(t.so2) so2,count(t.o3one_hour) o3one_hour ,count(t.co) co,count(t.pm10) pm10,count(t.pm25) pm25,
            count(t.ws) ws,count(t.wd) wd,count(t.press) press,count(t.temp) temp,count(t.humi) humi,count(t.rain) rain,count(t.noise)noise,count(t.tsp)tsp
            from  
            (select *  from miluo_air_hour_statements where id in (#join(ids)#)) t  where t.query_time > #beginTime#  and t.query_time <=#endTime#
            
standardSite
===
           select * from 
            (select   
            t.name,t2.site_code,t2.query_time,t2.pm10,t2.pm25,t2.so2,t2.co,t2.no2,t2.o3one_Hour,t2.humi,
            t2.ws,t2.wd,t2.temp,t2.press 
            from miluo_site t
            left join miluo_video  t1
            on t.id=t1.site_id
            left join miluo_air_hour_statements t2
             on t1.equment_id=t2.site_code
            where t.site_type=2 and t.site_category in('22','23')
             ORDER BY query_time desc limit 1000
            ) t3  where t3.site_code is not null
             GROUP BY t3.site_code            
  
ids       
===  
         select  GROUP_CONCAT(s.id) as idList from miluo_air_hour_statements s  where s.query_time > #beginTime#  and s.query_time <=#endTime#  group by s.site_code 
                                                                		
     
sample
===
* 注释

	 select #use("cols")# from miluo_air_hour_statements  where  #use("condition")#

cols
===
	id,site_code,station_name,query_time,station_type,aq_degree,so2,so2_iaqi,no2,no2_iaqi,pm10,pm10_iaqi,co,co_iaqi,o3eight_hour,o3one_hour,pm25,pm25_iaqi,aqi,primary_ep,aq_type,ws,wd,temp,press,humi,rain,create_date,no,nox,tsp,noise

updateSample
===
	
	id=#id#,site_code=#siteCode#,station_name=#stationName#,query_time=#queryTime#,station_type=#stationType#,aq_degree=#aqDegree#,so2=#so2#,so2_iaqi=#so2Iaqi#,no2=#no2#,no2_iaqi=#no2Iaqi#,pm10=#pm10#,pm10_iaqi=#pm10Iaqi#,co=#co#,co_iaqi=#coIaqi#,o3eight_hour=#o3eightHour#,o3one_hour=#o3oneHour#,pm25=#pm25#,pm25_iaqi=#pm25Iaqi#,aqi=#aqi#,primary_ep=#primaryEp#,aq_type=#aqType#,ws=#ws#,wd=#wd#,temp=#temp#,press=#press#,humi=#humi#,rain=#rain#,create_date=#createDate#,no=#no#,nox=#nox#,tsp=#tsp#,noise=#noise#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(siteCode)){
	 and site_code=#siteCode#
	@}
	@if(!isEmpty(stationName)){
	 and station_name=#stationName#
	@}
	@if(!isEmpty(queryTime)){
	 and query_time=#queryTime#
	@}
	@if(!isEmpty(stationType)){
	 and station_type=#stationType#
	@}
	@if(!isEmpty(aqDegree)){
	 and aq_degree=#aqDegree#
	@}
	@if(!isEmpty(so2)){
	 and so2=#so2#
	@}
	@if(!isEmpty(so2Iaqi)){
	 and so2_iaqi=#so2Iaqi#
	@}
	@if(!isEmpty(no2)){
	 and no2=#no2#
	@}
	@if(!isEmpty(no2Iaqi)){
	 and no2_iaqi=#no2Iaqi#
	@}
	@if(!isEmpty(pm10)){
	 and pm10=#pm10#
	@}
	@if(!isEmpty(pm10Iaqi)){
	 and pm10_iaqi=#pm10Iaqi#
	@}
	@if(!isEmpty(co)){
	 and co=#co#
	@}
	@if(!isEmpty(coIaqi)){
	 and co_iaqi=#coIaqi#
	@}
	@if(!isEmpty(o3eightHour)){
	 and o3eight_hour=#o3eightHour#
	@}
	@if(!isEmpty(o3oneHour)){
	 and o3one_hour=#o3oneHour#
	@}
	@if(!isEmpty(pm25)){
	 and pm25=#pm25#
	@}
	@if(!isEmpty(pm25Iaqi)){
	 and pm25_iaqi=#pm25Iaqi#
	@}
	@if(!isEmpty(aqi)){
	 and aqi=#aqi#
	@}
	@if(!isEmpty(primaryEp)){
	 and primary_ep=#primaryEp#
	@}
	@if(!isEmpty(aqType)){
	 and aq_type=#aqType#
	@}
	@if(!isEmpty(ws)){
	 and ws=#ws#
	@}
	@if(!isEmpty(wd)){
	 and wd=#wd#
	@}
	@if(!isEmpty(temp)){
	 and temp=#temp#
	@}
	@if(!isEmpty(press)){
	 and press=#press#
	@}
	@if(!isEmpty(humi)){
	 and humi=#humi#
	@}
	@if(!isEmpty(rain)){
	 and rain=#rain#
	@}
	@if(!isEmpty(createDate)){
	 and create_date=#createDate#
	@}
	@if(!isEmpty(no)){
	 and no=#no#
	@}
	@if(!isEmpty(nox)){
	 and nox=#nox#
	@}
	@if(!isEmpty(tsp)){
	 and tsp=#tsp#
	@}
	@if(!isEmpty(noise)){
	 and noise=#noise#
	@}
	 	