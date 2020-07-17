siteNameAndType
===
    s.name as siteName
   
qualityCategoryPageList
===
    select #page("x.*")# from (
    	select #use("siteNameAndType")#,
    	DATE_FORMAT(query_time,'%Y-%m-%d') times,
    	sum(aq_type='优') excellentDays,round(sum(aq_type='优')/count(aq_type)*100,1) excellentRatio,
    	sum(aq_type='良') goodDays,round(sum(aq_type='良')/count(aq_type)*100,1) goodRatio,
    	sum(aq_type='轻度污染') mildDays,round(sum(aq_type='轻度污染')/count(aq_type)*100,1) mildRatio,
    	sum(aq_type='中度污染') moderateDays,round(sum(aq_type='中度污染')/count(aq_type)*100,1) moderateRatio,
    	sum(aq_type='重度污染') severeDays,round(sum(aq_type='重度污染')/count(aq_type)*100,1) severeRatio,
    	sum(aq_type='严重污染') seriousnessDays,round(sum(aq_type='严重污染')/count(aq_type)*100,1) seriousnessRatio,
    	day(LAST_DAY(DATE_FORMAT(query_time,'%Y-%m-%d'))) sumDays,
    	DATEDIFF(#endDate#,#startDate#)+1 allsumDays,
    	count(aq_type) validDays,
    	sum( primary_ep  like '%PM2.5%' ) pm25days,
        		sum( primary_ep like '%PM10%' ) pm10days,
        		sum( primary_ep like '%CO%' ) codays,
        		sum( primary_ep like '%NO2%' ) no2days,
        		sum( primary_ep like '%O3%' ) o3days,
        		sum( primary_ep like '%SO2%' ) so2days,
        		sum( pm25_iaqi <=100 ) pm25GoodDays,
        		count( pm25_iaqi ) pm25sumDays,
        		round( sum( pm25_iaqi <=100 )  / count( pm25_iaqi ) * 100, 1 ) pm25Ratio,
    	sum(aq_type='优')+sum(aq_type='良') excellentAndGood,
    	sum(aqi>100) excellnotentAndGood,
    	round((sum(aq_type='优')+sum(aq_type='良'))/count(aq_type)*100,1) excellentAndGoodRatio
    	from miluo_air_daily_statements d
        left join miluo_video v on v.`equment_id`=d.`site_code`
    	left join miluo_site s on s.id = v.site_id
    
    	where d.query_time >= #startDate# AND  d.query_time <= #endDate# 
        and s.status=1 AND s.site_type='2'
         @if(!isEmpty(siteCodes) && siteCodes != ""){
             AND s.id in (#join(siteCodes)#)
         @}
    	  @if(!isEmpty(dateType) && dateType =='1'){
              GROUP BY d.site_code , MONTH(d.query_time)
            @}
            @if(!isEmpty(dateType) && dateType =='2'){
              GROUP BY d.site_code , quarter(d.query_time)
            @}
            @if(!isEmpty(dateType) && dateType =='3'){
              GROUP BY d.site_code, year(d.query_time)
            @}
             @if(!isEmpty(dateType) && dateType =='4'){
               GROUP BY d.site_code
             @}              
    	) x
 
qualityCategoryPageList2
===
    select #page("x.*")# from (
     select  
    	DATE_FORMAT(query_time,'%Y-%m-%d') times,
    	sum(aq_type='优') excellentDays,round(sum(aq_type='优')/count(aq_type)*100,1) excellentRatio,
    	sum(aq_type='良') goodDays,round(sum(aq_type='良')/count(aq_type)*100,1) goodRatio,
    	sum(aq_type='轻度污染') mildDays,round(sum(aq_type='轻度污染')/count(aq_type)*100,1) mildRatio,
    	sum(aq_type='中度污染') moderateDays,round(sum(aq_type='中度污染')/count(aq_type)*100,1) moderateRatio,
    	sum(aq_type='重度污染') severeDays,round(sum(aq_type='重度污染')/count(aq_type)*100,1) severeRatio,
    	sum(aq_type='严重污染') seriousnessDays,round(sum(aq_type='严重污染')/count(aq_type)*100,1) seriousnessRatio,
    	day(LAST_DAY(DATE_FORMAT(query_time,'%Y-%m-%d'))) sumDays,
            	DATEDIFF(#endDate#,#startDate#)+1 allsumDays,
            		sum( primary_ep  like '%PM2.5%' ) pm25days,
                        		sum( primary_ep like '%PM10%' ) pm10days,
                        		sum( primary_ep like '%CO%' ) codays,
                        		sum( primary_ep like '%NO2%' ) no2days,
                        		sum( primary_ep like '%O3%' ) o3days,
                        		sum( primary_ep like '%SO2%' ) so2days,
     
        		sum( pm25_iaqi <=100 ) pm25GoodDays,
        		count( pm25_iaqi ) pm25sumDays,
        		round( sum( pm25_iaqi <=100 )  / count( pm25_iaqi ) * 100, 1 ) pm25Ratio,
    	count(aq_type) validDays,
    	sum(aq_type='优')+sum(aq_type='良') excellentAndGood,
    	sum(aqi>100) excellnotentAndGood,
    	round((sum(aq_type='优')+sum(aq_type='良'))/count(aq_type)*100,1) excellentAndGoodRatio
    	from miluo_air_daily_statements d
    	left join miluo_video v on v.`equment_id`=d.`site_code`
        left join miluo_site s on s.id = v.site_id
    
    	where d.query_time >= #startDate# AND  d.query_time <= #endDate# 
    	and s.status=1 AND s.site_type='2'
    	@if(!isEmpty(siteCodes)&&siteCodes!=''){
    	 and  s.id in (#join(siteCodes)#)
    	@}
        @if(!isEmpty(dateType) && dateType =='1'){
                  GROUP BY  d.site_code , MONTH(d.query_time)
                @}
        @if(!isEmpty(dateType) && dateType =='2'){
                          GROUP BY  d.site_code , quarter(d.query_time)
                        @}
        @if(!isEmpty(dateType) && dateType =='3'){
                          GROUP BY  d.site_code, year(d.query_time)
                        @}
         @if(!isEmpty(dateType) && dateType =='4'){
                           GROUP BY  d.site_code, date(d.query_time)
                         @}              
    	) x
    	
averageCompareListByPrimeval
===
     SELECT
    	d1.site_code,
    	ROUND( AVG( d1.so2 ) ) so2Avg,
    	ROUND( AVG( d1.no2 ) ) no2Avg,
    	ROUND( AVG( d1.pm10 ) ) pm10Avg,
    	ROUND( AVG( d1.pm25 ) ) pm25Avg,
    	ROUND( AVG( d1.co ), 1 ) coAvg,
    	ROUND( AVG( d1.o3eight_hour ) ) o3EightAvg,
    	so2PreYearAvg,
    	no2PreYearAvg,
    	pm10PreYearAvg,
    	pm25PreYearAvg,
    	coPreYearAvg,
    	o3EightPreYearAvg 
    FROM
    	miluo_air_daily_statements d1
    	LEFT JOIN (
    SELECT
    	d2.site_code,
    	v.site_id,
    	v.equment_id ,
    	ROUND( AVG( d2.so2 ) ) so2PreYearAvg,
    	ROUND( AVG( d2.no2 ) ) no2PreYearAvg,
    	ROUND( AVG( d2.pm10 ) ) pm10PreYearAvg,
    	ROUND( AVG( d2.pm25 ) ) pm25PreYearAvg,
    	ROUND( AVG( d2.co ), 1 ) coPreYearAvg,
    	ROUND( AVG( d2.o3eight_hour ) ) o3EightPreYearAvg 
    FROM
    	miluo_air_daily_statements d2
    LEFT JOIN miluo_video v ON v.equment_id = d2.site_code
    LEFT JOIN miluo_site ms ON ms.id = v.site_id 
    WHERE
    	d2.query_time <= #endPreDate# AND d2.query_time >= #startPreDate#
    	   @if(!isEmpty(siteCodes) && siteCodes != ""){
               AND ms.id in (#join(siteCodes)#)
           @}else{
               AND ms.id in (#join(siteIds)#)
           @}
    	) d2 on d2.site_code = d1.site_code  
    LEFT JOIN miluo_video v ON v.equment_id = d1.site_code 
    LEFT JOIN miluo_site ms ON ms.id = v.site_id
    WHERE 
    d1.query_time <= #endDate# AND d1.query_time >= #startDate#
      @if(!isEmpty(siteCodes) && siteCodes != ""){
             AND ms.id in (#join(siteCodes)#)
      @}else{
            AND ms.id in (#join(siteIds)#)
      @}
    ORDER BY d1.`query_time` DESC
     
 
    	
getStaionInfos
===
    SELECT
     site_code,
     station_name
    FROM
     miluo_air_daily_statements	
    WHERE
     site_code=1
    
 
        
selRankVo
===
    select #page("x.*")# from (
       	select 
       	s.name AS siteName,
                           	DATE_FORMAT(query_time,'%Y-%m-%d') time,
                             day(LAST_DAY(DATE_FORMAT(query_time,'%Y-%m-%d'))) ssssssss,
                             avg(pm25)  pm25,
                           	  avg(pm10)  pm10,
                           	  avg(no2)  no2,
                           	  avg(so2)  so2
                         from miluo_air_daily_statements d
                              
       	left join miluo_video v on v.`equment_id`=d.`site_code`
        left join miluo_site s on s.id = v.site_id
       	where d.query_time >= #startDate# AND  d.query_time <= #endDate# 
       	and s.status=1 AND s.site_type='2'
       	@if(!isEmpty(siteCodes)&&siteCodes!=''){
       	 and s.id in (#join(siteCodes)#)
       	@}
       	  @if(!isEmpty(dateType) && dateType =='1'){
                             GROUP BY d.site_code , MONTH(d.query_time)
                           @}
                   @if(!isEmpty(dateType)&& dateType =='2'){
                                     GROUP BY d.site_code , quarter(d.query_time)
                                   @}
                   @if(!isEmpty(dateType) && dateType =='3'){
                                     GROUP BY d.site_code, year(d.query_time)
                                   @}
                    @if(!isEmpty(dateType)&& dateType =='4'){
                                      GROUP BY d.site_code
                                    @}              
       	) x
   	
selAllRankVo
===
    
           	select 
          	s.name as siteName,
                               	DATE_FORMAT(query_time,'%Y-%m-%d') time,
                                 o3eight_hour o3,
                                 co co
               	from miluo_air_daily_statements d              
           
           	left join miluo_video v on v.`equment_id`=d.`site_code`
            left join miluo_site s on s.id = v.site_id
           	where d.query_time >= #startDate# AND d.query_time <= #endDate# 
           	and s.status=1 AND s.site_type='2'
           	@if(!isEmpty(siteCodes)&&siteCodes!=''){
           	 and s.id in (#join(siteCodes)#)
           	@}

primaryList
===
    select #page("x.*")# from (
    select s.name AS siteName,s.id,
    @if(timeType == '4'){
        CONCAT(#startDate#,'至',#endDate#) dataTime,
    @}else if(timeType == '2'){
        CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) dataTime,
    @}else{
        DATE_FORMAT(d.query_time, #timePattern#) dataTime,
    @}
    primary_ep,
    sum(CASE WHEN d.aq_type = '良' THEN 1 ELSE 0 END) goodDays,
    sum(CASE WHEN d.aq_type = '轻度污染' THEN 1 ELSE 0 END) mildDays,
    sum(CASE WHEN d.aq_type = '中度污染' THEN 1 ELSE 0 END) middleDays,
    sum(CASE WHEN d.aq_type = '重度污染' THEN 1 ELSE 0 END) severeDays,
    sum(CASE WHEN d.aq_type = '严重污染' THEN 1 ELSE 0 END) worstDays
    from miluo_air_daily_statements d
    left join miluo_video v on v.`equment_id`=d.`site_code`
    left join miluo_site s on s.id = v.site_id
    where s.status=1 AND s.site_type='2'
    AND primary_ep IS NOT NULL
    @if(timeType != '2'){
        AND DATE_FORMAT(d.query_time, #timePattern#) >= #startDate# AND DATE_FORMAT(d.query_time, #timePattern#) <= #endDate#
    @}else{
        AND CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) >= #startDate# AND CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) <= #endDate#
    @}
    @if(!isEmpty(siteCodes)&&siteCodes!=''){
        AND s.id in (#join(siteCodes)#)
    @}
    @if(timeType == '4'){
        GROUP BY d.site_code, primary_ep
    @}else{
        GROUP BY d.site_code, dataTime, primary_ep
    @}
    ) x
    ORDER BY x.id,x.primary_ep
    
primaryGraphicList
===
    select s.name as siteName,s.id,
    @if(timeType == '4'){
        CONCAT(#startDate#,'至',#endDate#) dataTime,
    @}else if(timeType == '2'){
        CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) dataTime,
    @}else{
        DATE_FORMAT(d.query_time, #timePattern#) dataTime,
    @}
    primary_ep,
    SUM(CASE WHEN d.primary_ep LIKE 'PM2.5' THEN 1 ELSE 0 END) pm25Count,
    SUM(CASE WHEN d.primary_ep LIKE 'PM10' THEN 1 ELSE 0 END) pm10Count,
    SUM(CASE WHEN d.primary_ep LIKE 'O3' THEN 1 ELSE 0 END) o3Count,
    SUM(CASE WHEN d.primary_ep LIKE 'NO2' THEN 1 ELSE 0 END) no2Count,
    SUM(CASE WHEN d.primary_ep LIKE 'SO2' THEN 1 ELSE 0 END) so2Count,
    SUM(CASE WHEN d.primary_ep LIKE 'CO' THEN 1 ELSE 0 END) coCount
    from miluo_air_daily_statements d
    left join miluo_video v on v.`equment_id`=d.`site_code`
    left join miluo_site s on s.id = v.site_id
    where s.status=1 AND s.site_type='2'
    AND primary_ep IS NOT NULL
    @if(timeType != '2'){
        AND DATE_FORMAT(d.query_time, #timePattern#) >= #startDate# AND DATE_FORMAT(d.query_time, #timePattern#) <= #endDate#
    @}else{
        AND CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) >= #startDate# AND CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) <= #endDate#
    @}
    @if(!isEmpty(siteCodes)&&siteCodes!=''){
        AND s.id in (#join(siteCodes)#)
    @}
    @if(timeType == '4'){
        GROUP BY d.site_code, primary_ep
    @}else{
        GROUP BY d.site_code, dataTime, primary_ep
    @}
    ORDER BY id,primary_ep
    
gradeList
===
    select #page("x.*")# from (
    select s.name as site_name,s.id,
    @if(timeType == '4'){
        CONCAT(#startDate#,'至',#endDate#) dataTime,
    @}else if(timeType == '2'){
        CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) dataTime,
    @}else{
        DATE_FORMAT(d.query_time, #timePattern#) dataTime,
    @}
     SUM(CASE WHEN d.aq_type = '优' THEN 1 ELSE 0 END) greatDays, 
     SUM(CASE WHEN d.aq_type = '良' THEN 1 ELSE 0 END) goodDays, 
     SUM(CASE WHEN d.aq_type = '轻度污染' THEN 1 ELSE 0 END) mildDays, 
     SUM(CASE WHEN d.aq_type = '中度污染' THEN 1 ELSE 0 END) middleDays, 
     SUM(CASE WHEN d.aq_type = '重度污染' THEN 1 ELSE 0 END) severeDays, 
     SUM(CASE WHEN d.aq_type = '严重污染' THEN 1 ELSE 0 END) worstDays,
     SUM(CASE WHEN d.aq_type IS NOT NULL THEN 1 ELSE 0 END) validDays,
     SUM(CASE WHEN d.primary_ep LIKE 'PM2.5' THEN 1 ELSE 0 END) pm25FirstDays,
     SUM(CASE WHEN d.primary_ep LIKE 'PM10' THEN 1 ELSE 0 END) pm10FirstDays,
     SUM(CASE WHEN d.primary_ep LIKE 'O3' THEN 1 ELSE 0 END) o3FirstDays,
     SUM(CASE WHEN d.primary_ep LIKE 'NO2' THEN 1 ELSE 0 END) no2FirstDays,
     SUM(CASE WHEN d.primary_ep LIKE 'SO2' THEN 1 ELSE 0 END) so2FirstDays,
     SUM(CASE WHEN d.primary_ep LIKE 'CO' THEN 1 ELSE 0 END) coFirstDays,
     SUM(CASE WHEN d.pm25 >= 0 THEN 1 ELSE 0 END) pm25ValidDays,
     SUM(CASE WHEN d.pm25 >= 0 AND d.pm25 < 75 THEN 1 ELSE 0 END) pm25OkDays
    from miluo_air_daily_statements d
    left join miluo_video v on v.`equment_id`=d.`site_code`
    left join miluo_site s on s.id = v.site_id
    where s.status=1 AND s.site_type='2'
    @if(timeType != '2'){
        AND DATE_FORMAT(d.query_time, #timePattern#) >= #startDate# AND DATE_FORMAT(d.query_time, #timePattern#) <= #endDate#
    @}else{
        AND CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) >= #startDate# AND CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) <= #endDate#
    @}
    @if(!isEmpty(siteCodes)&&siteCodes!=''){
        and s.id in (#join(siteCodes)#)
    @}
    @if(timeType == '4'){
        GROUP BY d.site_code
    @}else{
        GROUP BY d.site_code, dataTime
    @}
    ) x
    ORDER BY x.id
    
primaryListToExcel
===
    
    select s.name as siteName,s.id,
    @if(timeType == '4'){
        CONCAT(#startDate#,'至',#endDate#) dataTime,
    @}else if(timeType == '2'){
        CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) dataTime,
    @}else{
        DATE_FORMAT(d.query_time, #timePattern#) dataTime,
    @}
    primary_ep,
    sum(CASE WHEN d.aq_type = '良' THEN 1 ELSE 0 END) goodDays,
    sum(CASE WHEN d.aq_type = '轻度污染' THEN 1 ELSE 0 END) mildDays,
    sum(CASE WHEN d.aq_type = '中度污染' THEN 1 ELSE 0 END) middleDays,
    sum(CASE WHEN d.aq_type = '重度污染' THEN 1 ELSE 0 END) severeDays,
    sum(CASE WHEN d.aq_type = '严重污染' THEN 1 ELSE 0 END) worstDays
    from miluo_air_daily_statements d
    left join miluo_video v on v.`equment_id`=d.`site_code`
    left join miluo_site s on s.id = v.site_id
    where s.status=1 AND s.site_type='2'
    AND primary_ep IS NOT NULL
    @if(timeType != '2'){
        AND DATE_FORMAT(d.query_time, #timePattern#) >= #startDate# AND DATE_FORMAT(d.query_time, #timePattern#) <= #endDate#
    @}else{
        AND CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) >= #startDate# AND CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) <= #endDate#
    @}
    @if(!isEmpty(siteCodes)&&siteCodes!=''){
        AND s.id in (#join(siteCodes)#)
    @}
    @if(timeType == '4'){
        GROUP BY d.site_code, primary_ep
    @}else{
        GROUP BY d.site_code, dataTime, primary_ep
    @}
    ORDER BY s.id,primary_ep
    
gradeListToExcel
===
    select s.name as site_name,s.id,
    @if(timeType == '4'){
        CONCAT(#startDate#,'至',#endDate#) dataTime,
    @}else if(timeType == '2'){
        CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) dataTime,
    @}else{
        DATE_FORMAT(d.query_time, #timePattern#) dataTime,
    @}
     SUM(CASE WHEN d.aq_type = '优' THEN 1 ELSE 0 END) greatDays, 
     SUM(CASE WHEN d.aq_type = '良' THEN 1 ELSE 0 END) goodDays, 
     SUM(CASE WHEN d.aq_type = '轻度污染' THEN 1 ELSE 0 END) mildDays, 
     SUM(CASE WHEN d.aq_type = '中度污染' THEN 1 ELSE 0 END) middleDays, 
     SUM(CASE WHEN d.aq_type = '重度污染' THEN 1 ELSE 0 END) severeDays, 
     SUM(CASE WHEN d.aq_type = '严重污染' THEN 1 ELSE 0 END) worstDays,
     SUM(CASE WHEN d.aq_type IS NOT NULL THEN 1 ELSE 0 END) validDays,
     SUM(CASE WHEN d.primary_ep LIKE 'PM2.5' THEN 1 ELSE 0 END) pm25FirstDays,
     SUM(CASE WHEN d.primary_ep LIKE 'PM10' THEN 1 ELSE 0 END) pm10FirstDays,
     SUM(CASE WHEN d.primary_ep LIKE 'O3' THEN 1 ELSE 0 END) o3FirstDays,
     SUM(CASE WHEN d.primary_ep LIKE 'NO2' THEN 1 ELSE 0 END) no2FirstDays,
     SUM(CASE WHEN d.primary_ep LIKE 'SO2' THEN 1 ELSE 0 END) so2FirstDays,
     SUM(CASE WHEN d.primary_ep LIKE 'CO' THEN 1 ELSE 0 END) coFirstDays,
     SUM(CASE WHEN d.pm25 >= 0 THEN 1 ELSE 0 END) pm25ValidDays,
     SUM(CASE WHEN d.pm25 >= 0 AND d.pm25 < 75 THEN 1 ELSE 0 END) pm25OkDays
    from miluo_air_daily_statements d
    left join miluo_video v on v.`equment_id`=d.`site_code`
    left join miluo_site s on s.id = v.site_id
    where s.status=1 AND s.site_type='2'
    @if(timeType != '2'){
        AND DATE_FORMAT(d.query_time, #timePattern#) >= #startDate# AND DATE_FORMAT(d.query_time, #timePattern#) <= #endDate#
    @}else{
        AND CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) >= #startDate# AND CONCAT(YEAR(d.query_time),QUARTER(d.query_time)) <= #endDate#
    @}
    @if(!isEmpty(siteCodes)&&siteCodes!=''){
        and s.id in (#join(siteCodes)#)
    @}
    @if(timeType == '4'){
        GROUP BY d.site_code
    @}else{
        GROUP BY d.site_code, dataTime
    @}
    ORDER BY s.id
   