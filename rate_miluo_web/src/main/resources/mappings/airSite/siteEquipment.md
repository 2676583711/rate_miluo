getEquBySiteId
===
        SELECT * FROM miluo_video t WHERE 1=1 
         @if(!isEmpty(siteId)&& siteId !=""){
        AND t.site_id=#siteId#
        @} 
        
        
getEquBySiteIdAndName
===
             SELECT * FROM miluo_video t WHERE 1=1  
                    @if(!isEmpty(siteId)&& siteId !=""){
                           AND t.site_id=#siteId#
                           @} 
                            @if(!isEmpty(name)&& name !=""){
                                   AND t.name like #'%'+name+'%'#
                                   @}    
                                   
                                   
insertM
===
         insert into `miluo_video` (`site_id`,`sxj`,`spllk`,`scllk`,`ptdz`,`appdzqx`,`azDate`,`remark`,`equment_id`,`name`,`address`,`vidicon_id`,`type`) VALUES
        
         (
                #siteEquipment.siteId#,
             #siteEquipment.sxj#,
             #siteEquipment.spllk#,
              #siteEquipment.scllk#,
               #siteEquipment.ptdz#,
                #siteEquipment.appdzqx#,
                 #siteEquipment.azDate#,
                  #siteEquipment.remark#,
                   #siteEquipment.equmentId#,
                    #siteEquipment.name#,
                    #siteEquipment.address#,
                    #siteEquipment.vidiconId#,
                    #siteEquipment.type#
         ) 
         
countEquipments
===
    SELECT COUNT(*) FROM `miluo_video` WHERE equment_id = #equmentId#         