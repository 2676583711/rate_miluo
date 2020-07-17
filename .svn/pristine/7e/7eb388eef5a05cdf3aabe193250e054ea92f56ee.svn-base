sample
===
* 注释

	select #use("cols")# from miluo_exceeds_bid  where  #use("condition")#

airAlarmQuery
===   
    SELECT 
     @pageTag(){
          s.id site_id,a.id,s.name site_name,v.name equipment_name,
          CONCAT(a.`name`,'[', a.pollutant,']') pollutant,
          a.data_time,a.value,a.index_value,
          a.degree_text,a.text,a.status,
          a.serious_exceed,a.equipment_id
     @}
     FROM `miluo_site_alarm` a 
     LEFT JOIN miluo_video v ON v.equment_id = a.equipment_id
     LEFT JOIN miluo_site s ON s.id = v.site_id 
      
     WHERE a.alarm_type IN ("2-1","2-2","2-3")
     @if(!isEmpty(startDate) && startDate !=''){
        AND a.data_time >= #startDate#
     @}
     @if(!isEmpty(endDate) && endDate !=''){
        AND a.data_time <= #endDate#
     @}
     @if(!isEmpty(siteIds) && siteIds !=''){
        AND s.id IN (#join(siteIds)#)
     @}
     @if(!isEmpty(status) && status !=''){
        AND a.status = #status#
     @}
     order by a.data_time desc
     
findInfoById
===
        SELECT s.name site_name,s.fzr,u.name fzr_name,u.mobile,a.* 
        FROM (SELECT id,data_time,equipment_id,CONCAT(`name`,'[',pollutant,']') pollutant,text,serious_exceed 
        FROM `miluo_site_alarm` WHERE id = #id#) a
        LEFT JOIN miluo_video v ON v.equment_id = a.equipment_id
        LEFT JOIN miluo_site s ON s.id = v.site_id
        LEFT JOIN sys_user u ON u.user_id = s.fzr

airAlarmQueryExp
===   
     SELECT 
           s.id site_id,a.id,s.name site_name,v.name equipment_name,
           CONCAT(a.`name`,'[', a.pollutant,']') pollutant,
           a.data_time,a.value,a.index_value,
           a.degree_text,a.text,a.status,
           a.serious_exceed,a.equipment_id
      FROM `miluo_site_alarm` a 
      LEFT JOIN miluo_video v ON v.equment_id = a.equipment_id
      LEFT JOIN miluo_site s ON s.id = v.site_id
      WHERE 1=1 
      @if(isEmpty(type) && type != 'air'){
          AND a.alarm_type IN ("2-1","2-2","2-3")
      @}
      @if(isEmpty(type) && type != 'water'){
          AND a.alarm_type IN ("1-1")
      @}
      @if(isEmpty(type) && type != 'pollute'){
          AND a.alarm_type IN ("3-1")
      @}
      @if(isEmpty(type) && type != 'polluteWater'){
          AND a.alarm_type IN ("4-1")
      @}
      @if(!isEmpty(startDate) && startDate !=''){
         AND a.data_time >= #startDate#
      @}
      @if(!isEmpty(endDate) && endDate !=''){
         AND a.data_time <= #endDate#
      @}
      @if(!isEmpty(siteIds) && siteIds !=''){
         AND s.id IN (#join(siteIds)#)
      @}
      @if(!isEmpty(status) && status !=''){
         AND a.status = #status#
      @}
      order by a.data_time desc    
      

airAlarmQuerySituationExp
===   
     SELECT 
           s.id site_id,v.equment_id id,s.name site_name,v.name equipment_name,
           CONCAT(a.`name`,'[', a.pollutant,']') pollutant,
           a.data_time,a.value,a.index_value,
           a.degree_text,a.text,a.status,
           a.serious_exceed,a.equipment_id
      FROM `miluo_site_alarm` a 
      LEFT JOIN miluo_video v ON v.equment_id = a.equipment_id
      LEFT JOIN miluo_site s ON s.id = v.site_id
      WHERE 1=1 
      @if(isEmpty(type) && type != 'air'){
          AND a.alarm_type IN ("2-1","2-2","2-3")
      @}
      @if(isEmpty(type) && type != 'water'){
          AND a.alarm_type IN ("1-1")
      @}
      @if(isEmpty(type) && type != 'pollute'){
          AND a.alarm_type IN ("3-1")
      @}
      @if(isEmpty(type) && type != 'polluteWater'){
          AND a.alarm_type IN ("4-1")
      @}
      @if(!isEmpty(startDate) && startDate !=''){
         AND a.data_time >= #startDate#
      @}
      @if(!isEmpty(endDate) && endDate !=''){
         AND a.data_time <= #endDate#
      @}
      @if(!isEmpty(siteIds) && siteIds !=''){
         AND s.id IN (#join(siteIds)#)
      @}
      @if(!isEmpty(status) && status !=''){
         AND a.status = #status#
      @}
      order by a.data_time desc  
 
buttonAirAlarm 
===      
               select 
               @pageTag(){
               t.id,t.site_code,t.exceedsBidDate,t.pollutant,t.value,t.index_value,
               t.status,t.serious_exceed,
               t.name,t.siteId
               @}	
               from 
               (select 
               t1.id,
               t1.site_code,DATE_FORMAT(t1.exceeds_bid_date,"%Y-%m-%d %T") exceedsBidDate,t1.pollutant,t1.value,t1.index_value,
               t1.status,t1.serious_exceed,
               t2.name,t2.id siteId
               from( select * from miluo_site t where  t.site_type =2 and t.status=1 )t2
               left join (select *from miluo_video)t3
               on t3.site_id=t2.id
               left join (SELECT * FROM miluo_exceeds_bid where to_days(exceeds_bid_date)=to_days(now()) and  exceeds_type in('2-1','2-2','2-3') and status=0 ) t1
               on t1.site_code=t3.equment_id
               ) t where t.pollutant is not null
               and t.siteId in (#join(siteIds2)#)
               order by t.exceedsBidDate desc

latestTiming
===
      select t.site_code,t.pollutant,DATE_FORMAT(t.exceeds_bid_date,"%Y-%m-%d %T") exceedsBidDate,t.value from  miluo_exceeds_bid t 
      where  t.exceeds_bid_date BETWEEN  #startDate#  and  #endDate#  and t.status=0

siteByEquipment
===
         
          select * from miluo_site t
          left join miluo_video t1
          on t.id=t1.site_id
          where 1=1
          @if(!isEmpty(equipmentId)&&equipmentId!=''){
          and t1.equment_id in (#join(equipmentId)#)
          @}
     
airExceedById
===
      select *from miluo_exceeds_bid where id=#id#
  
getAlertListByParams2
===
        SELECT
    	t1.*,
        t3.`name`,
        t3.id site_id,
        t3.fzr
    FROM
    	`miluo_site_alarm` t1 
        LEFT JOIN miluo_video t2 ON t1.equipment_id = t2.equment_id
        LEFT JOIN miluo_site t3 on t2.site_id = t3.id
    WHERE 1=1
        @if(!isEmpty(siteIds)){
            AND t3.id IN (#join(siteIds)#) 
        @}
        @if(!isEmpty(siteType) && siteType != ''){
            AND t3.site_type = #siteType#
        @}
    	@if(!isEmpty(startTime) && startTime != '' && !isEmpty(endTime) && endTime != ''){
    	    AND t1.data_time >= #startTime#
            AND t1.data_time <= #endTime#
    	@}
    	@if(!isEmpty(status)){
    	    AND t1.`status` = #status#
    	@}
    ORDER BY
    	t1.data_time DESC 
    	LIMIT #pageNum#,#pageSize#;
        
getAlertListByParams
===
    SELECT
    	t1.*,
        t3.`name`,
        t3.id site_id,
        t3.fzr
    FROM
    	`miluo_exceeds_bid` t1 
        LEFT JOIN miluo_video t2 ON t1.site_code = t2.equment_id
        LEFT JOIN miluo_site t3 on t2.site_id = t3.id
    WHERE 1=1
        @if(!isEmpty(siteIds)){
            AND t3.id IN (#join(siteIds)#) 
        @}
        @if(!isEmpty(siteType) && siteType != ''){
            AND t3.site_type = #siteType#
        @}
    	@if(!isEmpty(startTime) && startTime != '' && !isEmpty(endTime) && endTime != ''){
    	    AND t1.exceeds_bid_date >= #startTime#
            AND t1.exceeds_bid_date <= #endTime#
    	@}
    	@if(!isEmpty(status)){
    	    AND t1.`status` = #status#
    	@}
    ORDER BY
    	t1.exceeds_bid_date DESC 
    	LIMIT #pageNum#,#pageSize#;
    	
    	
getAlertPollutantWater
===
    SELECT
        *
    FROM
    	(
    SELECT
    	t1.id,
    	t2.site_code,
    	DATE_FORMAT( t1.exceeds_bid_date, "%Y-%m-%d %T" ) exceedsBidDate,
    	t1.pollutant,
    	t1.`value`,
    	t1.index_value,
    	t1.`status`,
    	t2.`name`,
    	t2.id siteId,
    	t2.fzr
    FROM
    	( SELECT * FROM miluo_site t WHERE t.site_type = 4 AND t.STATUS = 1 ) t2
    	LEFT JOIN ( SELECT * FROM miluo_video ) t3 ON t3.site_id = t2.id
    	LEFT JOIN ( SELECT * FROM miluo_exceeds_bid WHERE exceeds_bid_date BETWEEN #startTime#  and #endTime# and  exceeds_type ='3-1' ) t1
    	ON t1.site_code = t3.equment_id ) t 
    WHERE
    	t.pollutant IS NOT NULL 
    	@if( !isEmpty(siteIds)) { 
    	    AND t.siteId IN ( #join(siteIds)#)
    	@} 
    	@if( !isEmpty ( status ) && status != '' ) {
    	    AND t.`status` = #status#
    	@} 
    ORDER BY
    	t.exceedsBidDate DESC
    LIMIT #pageNum#,#pageSize#

cols
===
	id,site_code,exceeds_bid_date,pollutant,exceeds_bid_ratio,value,status,index_value,handler,handling_time,remark,exceeds_type

updateSample
===
	
	id=#id#,site_code=#siteCode#,exceeds_bid_date=#exceedsBidDate#,pollutant=#pollutant#,exceeds_bid_ratio=#exceedsBidRatio#,value=#value#,status=#status#,index_value=#indexValue#,handler=#handler#,handling_time=#handlingTime#,remark=#remark#,exceeds_type=#exceedsType#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(siteCode)){
	 and site_code=#siteCode#
	@}
	@if(!isEmpty(exceedsBidDate)){
	 and exceeds_bid_date=#exceedsBidDate#
	@}
	@if(!isEmpty(pollutant)){
	 and pollutant=#pollutant#
	@}
	@if(!isEmpty(exceedsBidRatio)){
	 and exceeds_bid_ratio=#exceedsBidRatio#
	@}
	@if(!isEmpty(value)){
	 and value=#value#
	@}
	@if(!isEmpty(status)){
	 and status=#status#
	@}
	@if(!isEmpty(indexValue)){
	 and index_value=#indexValue#
	@}
	@if(!isEmpty(handler)){
	 and handler=#handler#
	@}
	@if(!isEmpty(handlingTime)){
	 and handling_time=#handlingTime#
	@}
	@if(!isEmpty(remark)){
	 and remark=#remark#
	@}
	@if(!isEmpty(exceedsType)){
	 and exceeds_type=#exceedsType#
	@}
	