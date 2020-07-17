sample
===
* 注释

	select #use("cols")# from miluo_pollutant_factor  where  #use("condition")#

cols
===
	id,code2017,name,code2005,unit_name,unit_conc,data_type,ranges_type,ranges,up_value,down_value,float_value,create_time

list
===
     select @pageTag(){
     sp.*
     @}
      from miluo_pollutant_factor sp
            where 1=1
             @if(!isEmpty(name)&&name!=''){
               and sp.name  like #'%'+name+'%'#
          @} 
          ORDER BY sp.create_time desc
findMiluoFactorInfoById
===
    select #use("cols")# from miluo_pollutant_factor  where  #use("condition")#
    



updateSample
===
	
	id=#id#,code2017=#code2017#,name=#name#,code2005=#code2005#,unit_name=#unitName#,unit_conc=#unitConc#,data_type=#dataType#,ranges_type=#rangesType#,ranges=#ranges#,up_value=#upValue#,down_value=#downValue#,float_value=#floatValue#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(code2017)){
	 and code2017=#code2017#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(code2005)){
	 and code2005=#code2005#
	@}
	@if(!isEmpty(unitName)){
	 and unit_name=#unitName#
	@}
	@if(!isEmpty(unitConc)){
	 and unit_conc=#unitConc#
	@}
	@if(!isEmpty(dataType)){
	 and data_type=#dataType#
	@}
	@if(!isEmpty(rangesType)){
	 and ranges_type=#rangesType#
	@}
	@if(!isEmpty(ranges)){
	 and ranges=#ranges#
	@}
	@if(!isEmpty(upValue)){
	 and up_value=#upValue#
	@}
	@if(!isEmpty(downValue)){
	 and down_value=#downValue#
	@}
	@if(!isEmpty(floatValue)){
	 and float_value=#floatValue#
	@}
	
	