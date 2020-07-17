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
    @if(!isEmpty(siteCodes)&& siteCodes !=""){
    	 and a.site_code in (#join(siteCodes)#)
    	@} 
    	
    	
findById
===
        SELECT t.id ,t.name,t.oldName,t.site_code,t.longitude,t.latitude,t.mn,t.site_type,t.address ,
        	t.fzr,t.phone,t.params,t.remark,t.`status`,t.area_type
         from miluo_site t
        where id=#id#    
        
        
        
deleteBySiteCodes
===
        DELETE from miluo_site_power 
        WHERE site_code in (#join(siteCodes)#) 
        
               
bySiteCode
===  
                 	select  t.fzr from  miluo_site t
      				left join miluo_video t1
      				 on t.id=t1.site_id
      				 left join  miluo_exceeds_bid t2 
      				 on t1.equment_id=t2.site_code
      				 where t2.site_code=#siteCode#
      				 group by t2.site_code
updateStatusById
===
    update miluo_site set status = 0
    where id=#id#


         	
         				 