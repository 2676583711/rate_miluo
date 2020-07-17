sample
===
* 注释

	select #use("cols")# from miluo_air_pram  where  #use("condition")#

cols
===
	id,plu_name,avg_time,con_limit_1,con_limit_2,unit,con_limit_3,con_limit_4,con_limit_5,con_limit_6,con_limit_7

updateSample
===
	
	id=#id#,plu_name=#pluName#,avg_time=#avgTime#,con_limit_1=#conLimit1#,con_limit_2=#conLimit2#,unit=#unit#,con_limit_3=#conLimit3#,con_limit_4=#conLimit4#,con_limit_5=#conLimit5#,con_limit_6=#conLimit6#,con_limit_7=#conLimit7#

condition
===

	condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(pluName)){
	 and plu_name=#pluName#
	@}
	@if(!isEmpty(avgTime)){
	 and avg_time=#avgTime#
	@}
	@if(!isEmpty(conLimit1)){
	 and con_limit_1=#conLimit1#
	@}
	@if(!isEmpty(conLimit2)){
	 and con_limit_2=#conLimit2#
	@}
	@if(!isEmpty(unit)){
	 and unit=#unit#
	@}
	@if(!isEmpty(conLimit3)){
	 and con_limit_3=#conLimit3#
	@}
	@if(!isEmpty(conLimit4)){
	 and con_limit_4=#conLimit4#
	@}
	@if(!isEmpty(conLimit5)){
	 and con_limit_5=#conLimit5#
	@}
	@if(!isEmpty(conLimit6)){
	 and con_limit_6=#conLimit6#
	@}
	@if(!isEmpty(conLimit7)){
	 and con_limit_7=#conLimit7#
	@}
	
	
list
===
     SELECT #page("ap.*")# FROM miluo_air_pram ap
     WHERE 1 = 1 
     ORDER BY ap.id + 0 asc
    
findLastId
===

	SELECT max(id + 0) FROM miluo_air_pram 
	WHERE 1 = 1	 

insertObject
===

	INSERT INTO miluo_air_pram(id,plu_name,avg_time,con_limit_1,con_limit_2,con_limit_3,con_limit_4,con_limit_5,con_limit_6,con_limit_7,unit,create_time,is_alarm,is_excessive)
	VALUE(#id#,#pluName#,#avgTime#,#conLimit1#,#conLimit2#,#conLimit3#,#conLimit4#,#conLimit5#,#conLimit6#,#conLimit7#,#unit#,#createTime#,#isAlarm#,#isExcessive#)

updateObject
===

	UPDATE miluo_air_pram SET plu_name=#pluName#,avg_time=#avgTime#,con_Limit_1=#conLimit1#,con_Limit_2=#conLimit2#,con_Limit_3=#conLimit3#,con_Limit_4=#conLimit4#,con_Limit_5=#conLimit5#,con_Limit_6=#conLimit6#,con_Limit_7=#conLimit7#,unit=#unit#,create_time=#createTime#,is_alarm=#isAlarm#,is_excessive=#isExcessive#
	WHERE id=#id#
	
batchRemove
===
	DELETE FROM miluo_air_pram WHERE id in (#join(ids)#)

searchObjectByText
===

	select * from miluo_air_pram where plu_name like #'%' +searchText+ '%'#

queryBycondition
===
	select 
	@pageTag(){
		ap.*
	@}
	from miluo_air_pram ap 
	where ap.plu_name like #'%' +pluName+ '%'#
    order by ap.create_time desc
	