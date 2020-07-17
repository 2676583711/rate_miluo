sample
===
* 注释

	select #use("cols")# from miluo_statement_type  where  #use("condition")#
	
lx
===  
     
      select #page("x.*")# from (
      SELECT 
      @pageTag(){ 
      t1.site_code,max(t.time), t1.name, sum(CASE t.site_statement WHEN 1 THEN 1 ELSE 0 END) lx, t3.site_statement,t3.equipment_id
      @}
      FROM miluo_site t1
      left join miluo_video t7
      on t1.id=t7.site_id
      left JOIN (select * from miluo_statement_type where time <=#endTime# AND time >= #beginTime#) t
      on t7.equment_id = t.equipment_id
      left join (SELECT t4.equipment_id, t4.site_statement
      FROM (select * from `miluo_statement_type`where site_type in ('21', '22', '23') order by `time` desc limit 10000)t4
      GROUP BY t4.equipment_id) t3
      on t7.equment_id = t3.equipment_id
      where t1.site_type ='2' and status = 1
      @if(!isEmpty(siteIds)&&siteIds!=''){
      and t1.id in (#join(siteIds)#)
      @}else{
      and t1.id in (#join(siteIds2)#)
      @}
      GROUP BY t1.site_code)x

lxPolluteWater
===
       select #page("x.*")# from (
       SELECT 
       @pageTag(){ 
       t1.site_code,t1.id, max(t.time), t1.name, sum(CASE t.site_statement WHEN 1 THEN 1 ELSE 0 END) lx, t3.site_statement,t1.equipment_id,t7.name videoName
       @}
       FROM (  select t2.site_code ,t2.name ,t6.equipment_id,t2.site_type,t2.status,t2.id
                    from miluo_site t2
                           left join miluo_video t5
                             on t2.id=t5.site_id
                           left join miluo_statement_type t6
                             on t6.equipment_id=t5.equment_id
                    group by t5.equment_id) t1
       left join miluo_video t7
       on t1.id=t7.site_id
       left JOIN (select * from miluo_statement_type where time <=#endTime# AND time >= #beginTime#) t
       on t7.equment_id = t.equipment_id
       left join (SELECT t4.equipment_id, t4.site_statement
       FROM (select * from `miluo_statement_type`where  site_type ='31' order by `time` desc limit 10000)t4
       GROUP BY t4.equipment_id) t3
       on t7.equment_id = t3.equipment_id
       where t1.site_type ='4' and status = 1
       @if(!isEmpty(siteIds)&&siteIds!=''){
       and t1.id in (#join(siteIds)#)
       @}else{
       and t1.id in (#join(siteIds2)#)
       @}
       GROUP BY t1.equipment_id)x
    
lxWaterAuto
===

        select #page("x.*")# from (
           SELECT 
           @pageTag(){ 
           t1.site_code,max(t.time), t1.name, sum(CASE t.site_statement WHEN 1 THEN 1 ELSE 0 END) lx, t3.site_statement,t1.equipment_id,t7.name videoName
           @}
           FROM (  select t2.site_code ,t2.name,t6.equipment_id,t2.site_type,t2.status,t2.id
                        from miluo_site t2
                               left join miluo_video t5
                                 on t2.id=t5.site_id
                               left join miluo_statement_type t6
                                 on t6.equipment_id=t5.equment_id
                        group by t5.equment_id) t1
           left join miluo_video t7
           on t1.id=t7.site_id
           left JOIN (select * from miluo_statement_type where time <=#endTime# AND time >= #beginTime#) t
           on t7.equment_id = t.equipment_id
           left join (SELECT t4.equipment_id, t4.site_statement
           FROM (select * from `miluo_statement_type`
           where site_type ='11' order by `time` desc limit 10000)t4
           GROUP BY t4.equipment_id) t3
           on t7.equment_id = t3.equipment_id
           where t1.site_type ='1' and status = 1
           @if(!isEmpty(siteIds)&&siteIds!=''){
           and t1.id in (#join(siteIds)#)
           @}else{
           and t1.id in (#join(siteIds2)#)
           @}
           GROUP BY t1.equipment_id)x
           
idList
===
      select  GROUP_CONCAT(s.id) as idList from miluo_statement_type s
       where s.time <=#endTime# AND s.time >= #beginTime# and s.site_type in('21','22','23')
       group by s.equipment_id   
       
idPolluteWaterList
===
        select  GROUP_CONCAT(s.id) as idList from miluo_statement_type s
        where s.time <=#endTime# AND s.time >= #beginTime# and s.site_type ='31'
        group by s.equipment_id   
                     
    
idWaterAutoList
===     
        select  GROUP_CONCAT(s.id) as idList from miluo_statement_type s
        where s.time <=#endTime# AND s.time >= #beginTime# and s.site_type ='11'
        group by s.equipment_id 
          
      
oSList
===
		select *  from miluo_statement_type where id in (#join(id)#)
		 and time >= #beginTime# and  time <= #endTime#
		 order by time asc
cols
===
	     id,time,site_statement,site_type,equipment_id

updateSample
===
	
	id=#id#,time=#time#,site_statement=#siteStatement#,site_type=#siteType#,equipment_id=#equipmentId#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(time)){
	 and time=#time#
	@}
	@if(!isEmpty(siteStatement)){
	 and site_statement=#siteStatement#
	@}
	@if(!isEmpty(siteType)){
	 and site_type=#siteType#
	@}
	@if(!isEmpty(equipmentId)){
	 and equipment_id=#equipmentId#
	@}
	