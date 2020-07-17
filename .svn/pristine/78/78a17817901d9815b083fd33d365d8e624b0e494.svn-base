sample
===
* 注释

	select #use("cols")# from miluo_site_deviced_param  where  #use("condition")#

cols
===
	id,video_id,param_id

airList
=== 
	   select 
		@pageTag(){
			ap.*,pram.plu_name,vi.name
		@}
		from miluo_site_deviced_param ap
		left join miluo_air_pram pram on ap.param_id=pram.id
		left join  miluo_video vi on ap.video_id=vi.id
		where pram.plu_name like #'%' +pluName+ '%'#  and ap.video_id=#videoId#


updateSample
===
	
	id=#id#,video_id=#videoId#,param_id=#paramId#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(videoId)){
	 and video_id=#videoId#
	@}
	@if(!isEmpty(paramId)){
	 and param_id=#paramId#
	@}
	