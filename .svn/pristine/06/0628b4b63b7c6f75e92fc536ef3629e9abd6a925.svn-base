sample
===
* 注释

	select #use("cols")# from miluo_exceeds_bid  where  #use("condition")#

polluteWaterAlarm
===
     SELECT 
     @pageTag(){
          a.id,s.name site_name,v.name equipment_name, s.id site_id,
          CONCAT(a.`name`,'[', a.pollutant,']') pollutant,
          a.data_time,a.value,a.index_value,
          a.degree_text,a.text ,a.status
     @}
     FROM `miluo_site_alarm` a 
     LEFT JOIN miluo_video v ON v.equment_id = a.equipment_id
     LEFT JOIN miluo_site s ON s.id = v.site_id
     WHERE a.alarm_type = "4-1"
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

polluteWaterAlarmExp
===

           select 
            t.id,
            t.site_code,t.exceedsBidDate,t.pollutant,t.value,t.index_value,
            t.status,t.serious_exceed,
            t.name,t.siteId,t.fzr,t.phone
            from 
            (select 
            t1.id,
            t1.site_code,DATE_FORMAT(t1.exceeds_bid_date,"%Y-%m-%d %T") exceedsBidDate,t1.pollutant,t1.value,t1.index_value,
            t1.status,t1.serious_exceed,
            t2.name,t2.id siteId,t2.fzr,t2.phone
            from( select * from miluo_site t where  t.site_type =4 and t.status=1 )t2
    		left join (select *from miluo_video)t3
    		on t3.site_id=t2.id
            left join (SELECT * FROM miluo_exceeds_bid where  exceeds_bid_date BETWEEN  #startDate#  and #endDate# and  exceeds_type ='3-1' ) t1
    		on t1.site_code=t3.equment_id
            ) t where t.pollutant is not null  
            @if(!isEmpty(siteIds)&&siteIds!=''){
            and t.siteId in (#join(siteIds)#)
            @}else{
            and t.siteId in (#join(siteIds2)#)
            @}
            @if(!isEmpty(status)&&status!=''){
            and t.status =#status#  
            @}
            order by t.exceedsBidDate desc
                        
buttonPolluteWaterAlarm
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
            from( select * from miluo_site t where  t.site_type =4 and t.status=1 )t2
            left join (select *from miluo_video)t3
            on t3.site_id=t2.id
            left join (SELECT * FROM miluo_exceeds_bid where to_days(exceeds_bid_date)=to_days(now()) and  exceeds_type ='3-1' and status=0 ) t1
            on t1.site_code=t3.equment_id
            ) t where t.pollutant is not null
            and t.siteId in (#join(siteIds2)#)  
            order by t.exceedsBidDate desc
            
            
polluteWaterExceedById
===
      select *from miluo_exceeds_bid where id=#id#
      
updatePolluteWaterTask      
===
       update  miluo_exceeds_bid  set status=2 where id=#id#

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
	