sample
===
* 注释

	select #use("cols")# from miluo_realtime_treatment_plant  where  #use("condition")#

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
	
	