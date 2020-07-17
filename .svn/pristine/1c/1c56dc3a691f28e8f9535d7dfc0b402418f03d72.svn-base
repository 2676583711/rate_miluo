getListBySiteId
===
        SELECT * FROM miluo_vidicon 
        WHERE 1=1
          @if(!isEmpty(siteId)&& siteId !=""){
             and site_id=#siteId#
            @}    
        GROUP BY vidicon_name