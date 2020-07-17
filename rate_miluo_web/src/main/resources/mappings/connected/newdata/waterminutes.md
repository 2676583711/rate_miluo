sample
===
* 注释

	select #use("cols")# from miluo_realtime_treatment_plant  where  #use("condition")#

waterLatest
===
        
        select 
        @pageTag(){ 
        t3.name,t3.id,t.equment_id,t.recording_time,t.bo1,t.ph,t.pb,t.cd,t.shen,t.zn,t.cu,t.tp,t.tn,t.cod,t.nh3,t1.name videoName
        @}
        from miluo_site t3
        left  join miluo_video t1
        on t3.id=t1.site_id
        left join  ( select t2.equment_id,t2.recording_time,t2.bo1,t2.ph,t2.pb,t2.cd,t2.shen,t2.zn,t2.cu,t2.tp,t2.tn,t2.cod,t2.nh3 from 
        (select equment_id,recording_time,bo1,ph,pb,cd,shen,zn,cu,tp,tn,cod,nh3 from miluo_realtime_treatment_plant  ORDER BY recording_time desc LIMIT 1000 ) t2 group by t2.equment_id)t
        on t.equment_id=t1.equment_id
        where t.equment_id is not null
         @if(!isEmpty(siteIds)&&siteIds!=''){
         and t3.id in (#join(siteIds)#)
         @}else{
         and t3.id in (#join(siteIds2)#)         
         @}
         ORDER BY t.recording_time desc

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