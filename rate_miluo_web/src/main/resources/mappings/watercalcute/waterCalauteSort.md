sample
===
	select #use("cols")# from water_calaute_sort  where  #use("condition")#

cols
===
	id,name,description,lh_code,wp_code,symbol,Type1,Type2,Type3,Type4,Type5,unit,sortorder,is_calculate



waterSort
===
     select 
         if(t.symbol = '<=',
         (case   
             when #dataValue# <= t.type1 then 1  
             when #dataValue# > t.type1 and #dataValue# <= t.type2 then 2  
             when #dataValue# > t.type2 and #dataValue# <= t.type3 then 3 
             when #dataValue# > t.type3 and #dataValue# <= t.type4 then 4
         	 when #dataValue#  > t.type4 and #dataValue#  <= t.type5 then 5
         	 when #dataValue# > t.type5  then 6
           end),
         (case   
             when #dataValue# >= t.type1 then 1  
             when #dataValue# < t.type1 and #dataValue# >= t.type2 then 2 
             when #dataValue# < t.type2 and #dataValue# >= t.type3 then 3 
             when #dataValue# < t.type3 and #dataValue# >= t.type4 then 4
         	when #dataValue# < t.type4 and #dataValue# >= t.type5 then 5
            when #dataValue# < t.type5 and #dataValue# > 0 then 6
           end)
         ) as count
         from miluo_water_param_new t where t.description = #paramName#    


updateSample
===
	
	id=#id#,name=#name#,description=#description#,lh_code=#lhCode#,wp_code=#wpCode#,symbol=#symbol#,Type1=#type1#,Type2=#type2#,Type3=#type3#,Type4=#type4#,Type5=#type5#,unit=#unit#,sortorder=#sortorder#,is_calculate=#isCalculate#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(description)){
	 and description=#description#
	@}
	@if(!isEmpty(lhCode)){
	 and lh_code=#lhCode#
	@}
	@if(!isEmpty(wpCode)){
	 and wp_code=#wpCode#
	@}
	@if(!isEmpty(symbol)){
	 and symbol=#symbol#
	@}
	@if(!isEmpty(type1)){
	 and Type1=#type1#
	@}
	@if(!isEmpty(type2)){
	 and Type2=#type2#
	@}
	@if(!isEmpty(type3)){
	 and Type3=#type3#
	@}
	@if(!isEmpty(type4)){
	 and Type4=#type4#
	@}
	@if(!isEmpty(type5)){
	 and Type5=#type5#
	@}
	@if(!isEmpty(unit)){
	 and unit=#unit#
	@}
	@if(!isEmpty(sortorder)){
	 and sortorder=#sortorder#
	@}
	@if(!isEmpty(isCalculate)){
	 and is_calculate=#isCalculate#
	@}