getJianCeParamList
===
        select * 
         from miluo_pollutant_factor 
         
getParamListByIds
===
           select * 
                    from miluo_pollutant_factor t
                    where 1=1
                  @if(!isEmpty(ids)&&ids!=""){
                  	and t.id in(#join(ids)#)
                  	@}  
                          