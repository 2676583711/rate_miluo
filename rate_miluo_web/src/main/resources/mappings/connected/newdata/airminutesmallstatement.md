queryByCondition
===
      SELECT 
      @pageTag(){
        t.*
      @}
      FROM (SELECT 
            d.report_time AS report_time,d.pm10,d.pm25,d.so2,d.co,d.no2,d.o3,d.ws,d.wd,d.temp,d.press,d.noise,d.tsp,s.name,s.site_category         
            FROM `miluo_desktop_air` d
            LEFT JOIN `miluo_video` v ON v.equment_id = d.site_code
            LEFT JOIN `miluo_site` s ON s.id = v.site_id
            WHERE s.status = 1 AND s.site_category = 21
            AND s.id IN (#join(siteIds)#)
            UNION
            (SELECT 
             d.query_time AS report_time,d.pm10,d.pm25,d.so2,d.co,d.no2,d.o3one_hour AS o3,d.ws,d.wd,d.temp,d.press,d.noise,d.tsp,s.name,s.site_category          
                FROM `miluo_air_hour_statements` d
                LEFT JOIN `miluo_video` v ON v.equment_id = d.site_code
                LEFT JOIN `miluo_site` s ON s.id = v.site_id
                WHERE s.status = 1 AND (s.site_category = 22 OR s.site_category = 23)
                AND s.id IN (#join(siteIds)#)
                ORDER BY report_time DESC
                LIMIT 1)
             ORDER BY site_category DESC, report_time DESC 
      ) t
         
                           
cols
===
	id,break_time,no,nox,co,ws,wd,temp,press,humi,rain,type
updateSample
===
	
	id=#id#,site_code=#siteCode#,report_time=#reportTime#,break_time=#breakTime#,so2=#so2#,no=#no#,no2=#no2#,nox=#nox#,co=#co#,o3=#o3#,pm10=#pm10#,pm25=#pm25#,ws=#ws#,wd=#wd#,temp=#temp#,press=#press#,humi=#humi#,rain=#rain#,type=#type#,so2_tag=#so2Tag#,no_tag=#noTag#,no2_tag=#no2Tag#,nox_tag=#noxTag#,co_tag=#coTag#,o3_tag=#o3Tag#,pm10_tag=#pm10Tag#,pm25_tag=#pm25Tag#,ws_tag=#wsTag#,wd_tag=#wdTag#,temp_tag=#tempTag#,press_tag=#pressTag#,humi_tag=#humiTag#,rain_tag=#rainTag#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(siteCode)){
	 and site_code=#siteCode#
	@}
	@if(!isEmpty(reportTime)){
	 and report_time=#reportTime#
	@}
	@if(!isEmpty(breakTime)){
	 and break_time=#breakTime#
	@}
	@if(!isEmpty(so2)){
	 and so2=#so2#
	@}
	@if(!isEmpty(no)){
	 and no=#no#
	@}
	@if(!isEmpty(no2)){
	 and no2=#no2#
	@}
	@if(!isEmpty(nox)){
	 and nox=#nox#
	@}
	@if(!isEmpty(co)){
	 and co=#co#
	@}
	@if(!isEmpty(o3)){
	 and o3=#o3#
	@}
	@if(!isEmpty(pm10)){
	 and pm10=#pm10#
	@}
	@if(!isEmpty(pm25)){
	 and pm25=#pm25#
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
	@if(!isEmpty(type)){
	 and type=#type#
	@}
	@if(!isEmpty(so2Tag)){
	 and so2_tag=#so2Tag#
	@}
	@if(!isEmpty(noTag)){
	 and no_tag=#noTag#
	@}
	@if(!isEmpty(no2Tag)){
	 and no2_tag=#no2Tag#
	@}
	@if(!isEmpty(noxTag)){
	 and nox_tag=#noxTag#
	@}
	@if(!isEmpty(coTag)){
	 and co_tag=#coTag#
	@}
	@if(!isEmpty(o3Tag)){
	 and o3_tag=#o3Tag#
	@}
	@if(!isEmpty(pm10Tag)){
	 and pm10_tag=#pm10Tag#
	@}
	@if(!isEmpty(pm25Tag)){
	 and pm25_tag=#pm25Tag#
	@}
	@if(!isEmpty(wsTag)){
	 and ws_tag=#wsTag#
	@}
	@if(!isEmpty(wdTag)){
	 and wd_tag=#wdTag#
	@}
	@if(!isEmpty(tempTag)){
	 and temp_tag=#tempTag#
	@}
	@if(!isEmpty(pressTag)){
	 and press_tag=#pressTag#
	@}
	@if(!isEmpty(humiTag)){
	 and humi_tag=#humiTag#
	@}
	@if(!isEmpty(rainTag)){
	 and rain_tag=#rainTag#
	@}
	