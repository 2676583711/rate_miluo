sample
===
* 注释

	select #use("cols")# from miluo_air_hour_statements  where  #use("condition")#

cols
===
	id,site_code,station_name,query_time,station_type,aq_degree,so2,so2_iaqi,no2,no2_iaqi,pm10,pm10_iaqi,co,co_iaqi,o3eight_hour,o3one_hour_iaqi,pm25,pm25_iaqi,aqi,primary_ep,aq_type,ws,wd,temp,press,humi,rain,create_date,no,nox,tsp,noise

updateSample
===
	
	id=#id#,site_code=#siteCode#,station_name=#stationName#,query_time=#queryTime#,station_type=#stationType#,aq_degree=#aqDegree#,so2=#so2#,so2_iaqi=#so2Iaqi#,no2=#no2#,no2_iaqi=#no2Iaqi#,pm10=#pm10#,pm10_iaqi=#pm10Iaqi#,co=#co#,co_iaqi=#coIaqi#,o3eight_hour=#o3eightHour#,o3one_hour_iaqi=#o3oneHourIaqi#,pm25=#pm25#,pm25_iaqi=#pm25Iaqi#,aqi=#aqi#,primary_ep=#primaryEp#,aq_type=#aqType#,ws=#ws#,wd=#wd#,temp=#temp#,press=#press#,humi=#humi#,rain=#rain#,create_date=#createDate#,no=#no#,nox=#nox#,tsp=#tsp#,noise=#noise#

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
	@if(!isEmpty(o3oneHourIaqi)){
	 and o3one_hour_iaqi=#o3oneHourIaqi#
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
    
hourList
===

		SELECT
	  h.`id` AS id,
	  ms.`name` as siteName,
	  DATE_FORMAT(h.`query_time`,'%Y-%m-%d %H') AS queryTime,
	  round(h.`so2`) AS so2,
	  round(h.`no2`) AS no2,
	  round(h.`co`,1) AS co,
	  round(h.`o3one_hour`) AS o3OneHour,
	  round(h.`aqi`) AS aqi,
	  h.`primary_ep` AS primaryEp,
	  h.`aq_type` AS aqType,
	  h.`aq_degree` AS aqDegree,
	  round(h.`tsp`) AS tsp,
	  round(h.`pm10`) AS pm10,
	  round(h.`pm25`) AS pm25,
	  round(h.`noise`,1) AS noise,
	  round(h.`temp`) AS temp,
	  round(h.`humi`,1) AS humi,
	  round(h.`press`) AS press,
	  round(h.`ws`,1) AS ws,
	  round(h.`wd`) AS wd
	FROM
	  miluo_air_hour_statements h LEFT JOIN miluo_video v ON v.`equment_id`=h.`site_code`
	  LEFT JOIN miluo_site ms ON ms.id = v.site_id
	WHERE ms.status=1 AND ms.site_type=2
    @if(!isEmpty(siteCodes) && siteCodes != ""){
        AND ms.id in (#join(siteCodes)#)
    @}else{
        AND ms.id in (#join(siteIds)#)
    @}
    @if(!isEmpty(beginTime) && beginTime != ""){
       AND DATE_FORMAT(h.`query_time`, '%Y-%m-%d %H') >= DATE_FORMAT(#beginTime#,'%Y-%m-%d %H')
     @}
     @if(!isEmpty(endTime) && endTime != ""){
       AND DATE_FORMAT(h.`query_time`, '%Y-%m-%d %H') <= DATE_FORMAT(#endTime#,'%Y-%m-%d %H')
     @}
	ORDER BY h.`query_time` DESC
   
dailyList
===
		SELECT 
	  d.`id` AS id,
	  ms.`name` AS siteName,
	  DATE_FORMAT(d.`query_time`,'%Y-%m-%d') AS queryTime,
	  round(d.`so2`) AS so2,
	  round(d.`no2`) AS no2,
	  round(d.`co`,1) AS co,
	  round(d.`o3eight_hour`) AS o3EightHour,
	  round(d.`aqi`) AS aqi,
	  d.`primary_ep` AS primaryEp,
	  d.`aq_type` AS aqType,
	  d.`aq_degree` AS aqDegree,
	  round(d.`tsp`) AS tsp,
	  round(d.`pm10`) AS pm10,
	  round(d.`pm25`) AS pm25,
	  round(d.`noise`,1) AS noise,
	  round(d.`temp`) AS temp,
	  round(d.`humi`,1) AS humi,
	  round(d.`press`) AS press,
	  round(d.`ws`) AS ws,
	  round(d.`wd`,1) AS wd
	FROM
	  miluo_air_daily_statements d LEFT JOIN miluo_video v ON v.`equment_id`=d.`site_code`
	  LEFT JOIN miluo_site ms ON ms.id = v.site_id
	WHERE ms.status=1 AND ms.site_type=2
    @if(!isEmpty(siteCodes) && siteCodes != ""){
        AND ms.id in (#join(siteCodes)#)
    @}else{
        AND ms.id in (#join(siteIds)#)
    @}
    @if(!isEmpty(beginTime) && beginTime != ""){
       AND DATE_FORMAT(d.`query_time`, '%Y-%m-%d') >= #beginTime#
     @}
     @if(!isEmpty(endTime) && endTime != ""){
       AND DATE_FORMAT(d.`query_time`, '%Y-%m-%d') <= #endTime#
     @}
	ORDER BY d.`query_time` DESC
	
* monthList
===
		SELECT 
	  DATE_FORMAT(d.query_time,'%Y-%m') AS queryTime,
	  d.`site_code` AS siteCode,
	  s.name as siteName,
	  avg(d.tsp) as tsp,
	  AVG(d.pm10) AS pm10,
	  AVG(d.pm25) AS pm25,
	  avg(d.noise) as noise,
	  AVG(d.temp) AS temp,
	  AVG(d.humi) AS humi,
	  AVG(d.press) AS press,
	  AVG(d.ws) AS ws,
	  AVG(d.wd) AS wd
	  FROM miluo_air_daily_statements d 
	  LEFT JOIN miluo_site s 
	  ON d.`site_code`=s.`site_code` 
	  WHERE s.`status`=1 AND s.`site_category` IN (22,23)
	  @if(!isEmpty(siteCodes) && siteCodes != ""){
        AND d.site_code in (#join(siteCodes)#)
    	  @}
      @if(!isEmpty(beginTime) && beginTime != ""){
        AND DATE_FORMAT(d.`query_time`, '%Y-%m') >= #beginTime#
      @}
      @if(!isEmpty(endTime) && endTime != ""){
        AND DATE_FORMAT(d.`query_time`, '%Y-%m') <= #endTime#
      @}
	  GROUP BY DATE_FORMAT(d.query_time,'%Y-%m'),d.`site_code`
	  ORDER BY d.`query_time` DESC

