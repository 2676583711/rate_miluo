getListByQueryCondition
===
    SELECT  @pageTag(){
            a.*
            @} 
     FROM 
    (SELECT t.id ,t.name,t.site_code, t.longitude,t.latitude,t.site_type,t.area_type,t.params
    FROM miluo_site t) a 
    where 1=1
    @if(!isEmpty(positionName)&& positionName !=""){
    	 and a.name like #'%'+positionName+'%'#
    	@} 