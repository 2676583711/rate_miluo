
sample
===
* 注释

	select #use("cols")# from miluo_hours_treatment_plant  where  #use("condition")#
	
	
polluteWaterIds
===

	       
        select  GROUP_CONCAT(s.id) as idList from miluo_hours_treatment_plant s
        where s.recording_time BETWEEN  #beginTime#  and #endTime# 
        group by s.equment_id

hourPolluteInter
===

    SELECT 
    t.name,t1.*
    FROM 
    (SELECT equment_id,recording_time,ph,cod,nh3,tp,tn,cu,zn,pb,cd,shen FROM miluo_hours_treatment_plant) t1
    LEFT JOIN miluo_video t2
    ON t2.equment_id = t1.equment_id
    LEFT JOIN (SELECT `id`,`name` FROM miluo_site) t
    ON t.id=t2.site_id
    WHERE 1=1
    AND  t1.equment_id =#siteCode#
    AND t1.recording_time BETWEEN #startTime# AND #endTime#
    ORDER BY t1.recording_time DESC

polluteWaterRate
===     

     
        select t.equment_id,count(t.bo1) bo1,count(t.ph) ph,count(t.pb) pb ,
        count(t.cd) cd,count(t.shen) shen,count(t.zn) zn,count(t.cu) cu,
        count(t.tp) tp,count(t.tn) tn,count(t.cod) cod,count(t.nh3) nh3
         from    
        (select *  from miluo_hours_treatment_plant where id in (#join(ids)#)) t
         where t.recording_time BETWEEN  #beginTime# and #endTime# 
         
         
polluteWaterDetails  
===
    
			 select 
             @pageTag(){     
             t.name, t.site_code,t1.recording_time, t1.equment_id,  t1.bo1,  t1.ph,  t1.pb , t1.cd,  t1.shen,  t1.zn, t1.cu,
              t1.recording_time,t1.tp,t1.tn,t1.cod,t1.nh3,t2.name videoName
             @}
             from 
             (select 
             equment_id, bo1, ph, pb ,cd, shen, zn,cu,recording_time,tp,tn,cod,nh3
             from miluo_hours_treatment_plant
             where recording_time >=(now()-INTERVAL 25 hour)
             ) t1
             left join miluo_video t2
             on t2.equment_id=t1.equment_id
             left join 
             (select site_code,name,id from miluo_site)t
             on t.id=t2.site_id
             where t1.equment_id=#siteCode#
             ORDER BY t1.recording_time desc  
				     
cols
===
	id,equment_id,recording_time,bo1,ph,pb,cd,shen,zn,cu

updateSample
===
	
	id=#id#,equment_id=#equmentId#,recording_time=#recordingTime#,bo1=#bo1#,ph=#ph#,pb=#pb#,cd=#cd#,shen=#shen#,zn=#zn#,cu=#cu#

condition
===
          
	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(equmentId)){
	 and equment_id=#equmentId#
	@}
	@if(!isEmpty(recordingTime)){
	 and recording_time=#recordingTime#
	@}
	@if(!isEmpty(bo1)){
	 and bo1=#bo1#
	@}
	@if(!isEmpty(ph)){
	 and ph=#ph#
	@}
	@if(!isEmpty(pb)){
	 and pb=#pb#
	@}
	@if(!isEmpty(cd)){
	 and cd=#cd#
	@}
	@if(!isEmpty(shen)){
	 and shen=#shen#
	@}
	@if(!isEmpty(zn)){
	 and zn=#zn#
	@}
	@if(!isEmpty(cu)){
	 and cu=#cu#
	@}
	