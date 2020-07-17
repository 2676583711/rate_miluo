
sample
===
* 注释

	select #use("cols")# from miluo_task_info  where  #use("condition")#

cols
===
	id,station_code,name,degree_emergency,dept_id,charge_person_id,task_type,start_time,end_time,status,pub_person_id,remark

updateSample
===
	
	id=#id#,station_code=#stationCode#,name=#name#,degree_emergency=#degreeEmergency#,dept_id=#deptId#,charge_person_id=#chargePersonId#,task_type=#taskType#,start_time=#startTime#,end_time=#endTime#,status=#status#,pub_person_id=#pubPersonId#,remark=#remark#

list
===
      select @pageTag(){
        t.*,s.name as pub_person_name
      @}
      from
	  ( select 
      p.*,sd.name as dpname,su.name as fzr,concat(IFNULL(site.name,'')) as site_name ,site.longitude,site.latitude
      ,site.site_type,site.id as siteId
      FROM miluo_task_info p 
      LEFT JOIN sys_user su
         on su.user_id=p.charge_person_id
         LEFT JOIN miluo_video vi
         on vi.equment_id=p.station_code
         LEFT JOIN miluo_site site
         on site.id=vi.site_id    
         LEFT JOIN sys_dept sd
         on sd.dept_id=p.dept_id
         	WHERE p.status=0
         	@if(!isEmpty(fzr)&& fzr!=''){
             and su.name  like #'%'+fzr+'%'#
          @}
    	@if(!isEmpty(taskName)&& taskName!=''){
                              and p.name like #'%'+taskName+'%'#
                            @}
          @if(roleId == 77){
          	
            and p.charge_person_id = #userId#
               or p.pub_person_id = #userId#
          @}
          
           @if(roleId != 77 && roleId != 1){
                  and p.charge_person_id = #userId#
                @}   
		) t
			 left join sys_user s on t.pub_person_id = s.user_id
			 
listExp
===
      select 
        t.*,s.name as pub_person_name
      from
	  ( select 
      p.*,sd.name as dpname,su.name as fzr,concat(IFNULL(site.name,'')) as site_name
      FROM miluo_task_info p 
      LEFT JOIN sys_user su
         on su.user_id=p.charge_person_id
         LEFT JOIN miluo_video vi
         on vi.equment_id=p.station_code
         LEFT JOIN miluo_site site
         on site.id=vi.site_id    
         LEFT JOIN sys_dept sd
         on sd.dept_id=p.dept_id
         	WHERE p.status=0
         	@if(!isEmpty(fzr)&& fzr!=''){
             and su.name  like #'%'+fzr+'%'#
          @}
    		@if(!isEmpty(taskName)&& taskName!=''){
            and p.name   like #'%'+taskName+'%'#
          @}
          	@if(!isEmpty(taskName)&& taskName!=''){
                                and p.name like #'%'+taskName+'%'#
                              @}
          @if(roleId == 77){
          
                     and p.charge_person_id = #userId#
                        or p.pub_person_id = #userId#
                   @}
             @if(roleId != 77 && roleId != 1){
            and p.charge_person_id = #userId#
           
          @}        
		) t
			 left join sys_user s on t.pub_person_id = s.user_id
			 			 
taskInfoCount
===
       		                		
             select 
             @pageTag(){
             p.*,sd.name as dpname,su.name as fzr,concat(IFNULL(site.name,''),IFNULL(sites.name,'')) as site_name
             @} 
             FROM miluo_task_info p 
             LEFT JOIN sys_user su
             on su.user_id=p.charge_person_id
             LEFT JOIN miluo_video vi
             on vi.equment_id=p.station_code
             LEFT JOIN miluo_site site
             on site.id=vi.site_id
             LEFT JOIN miluo_site sites
             on sites.id=p.station_code
             LEFT JOIN sys_dept sd
             on sd.dept_id=p.dept_id
             WHERE p.status=0
             @if(roleId == 77){
			    and p.pub_person_id = #userId#
			 @}else if(roleId != 1){
			    and p.charge_person_id = #userId#
			 @}
queryTaskList
===
    SELECT
        tab1.id,
        ms.site_type,
        ms.`name` site_name,
    	s.`name` pub_person_name,
    	tab1.* ,
    	ms.longitude,
    	ms.latitude,
    	ms.site_category,
    	ms.site_code,
    	ms.id site_id,
    	ms.fzr
    FROM
    	( SELECT t2.`name` charge_person_name, t1.* FROM miluo_task_info t1 LEFT JOIN sys_user t2 ON t1.charge_person_id = t2.user_id ) tab1
    	LEFT JOIN sys_user s ON tab1.pub_person_id = s.user_id 
    		LEFT JOIN miluo_video v ON v.equment_id = tab1.station_code 
            	LEFT JOIN miluo_site ms ON ms.id = v.site_id 
    WHERE
    	1 = 1 
    	@if(!isEmpty(status) && status != ''){
    	    AND tab1.`status` = #status#
    	@}
    	@if(!isEmpty(name) && name != ''){
    	    AND CONCAT( tab1.charge_person_name, tab1.`name` ) LIKE #'%' + name + '%'#
    	@}
    	@if(roleId == 1){
           
        @}else if(roleId == 77){
            AND tab1.pub_person_id = #userId# OR tab1.charge_person_id = #userId#
        @}else {
            AND tab1.charge_person_id = #userId#
        @}
    ORDER BY
        tab1.degree_emergency DESC
    @if(!isEmpty(pageNum) && !isEmpty(pageSize)){
        LIMIT #pageNum#,#pageSize#
    @}
    	



historyList
===
	 select 
       @pageTag(){
           p.*,sd.name as dpname,su.name as fzr,concat(IFNULL(site.name,'')) as site_name,site.longitude,site.latitude
           ,site.site_type,site.id as siteId
       @} 
    	FROM miluo_task_info p 
    
    LEFT JOIN sys_user su
    on su.user_id=p.charge_person_id
    
    LEFT JOIN miluo_video vi
         on vi.equment_id=p.station_code
         LEFT JOIN miluo_site site
         on site.id=vi.site_id       
    LEFT JOIN sys_dept sd
    on sd.dept_id=p.dept_id
    
    	WHERE p.status=1
    		@if(!isEmpty(fzr)&& fzr!=''){
                and su.name  like #'%'+fzr+'%'#
            @}
    		@if(!isEmpty(taskName)&& taskName!=''){
                and p.name   like #'%'+taskName+'%'#
            @}
           
		 
			 @if(roleId == 77){
			    and p.charge_person_id = #userId#
			     or p.pub_person_id = #userId#
			 @}
			    @if(roleId != 77 && roleId != 1){
                 and p.charge_person_id = #userId#
                
               @}  

historyListExp
===
	 select 
           p.*,sd.name as dpname,su.name as fzr,concat(IFNULL(site.name,'')) as site_name
    	FROM miluo_task_info p 
    
    LEFT JOIN sys_user su
    on su.user_id=p.charge_person_id
    
    LEFT JOIN miluo_video vi
         on vi.equment_id=p.station_code
         LEFT JOIN miluo_site site
         on site.id=vi.site_id       
    LEFT JOIN sys_dept sd
    on sd.dept_id=p.dept_id
    
    	WHERE p.status=1
    		@if(!isEmpty(fzr)&& fzr!=''){
                and su.name  like #'%'+fzr+'%'#
            @}
    		@if(!isEmpty(taskName)&& taskName!=''){
                and p.name like #'%'+taskName+'%'#
            @}
            
		 @if(roleId == 77){
                and p.charge_person_id = #userId#
                 or p.pub_person_id = #userId#
             @}
                @if(roleId != 77 && roleId != 1){
                      and p.charge_person_id = #userId#
                    @} 
findMiluoTaskInfoById
===
    select p.*,sd.name as dpname,su.name as fzr,concat(IFNULL(site.name,''),IFNULL(sites.name,'')) as site_name FROM miluo_task_info p  LEFT JOIN sys_user su
    on su.user_id=p.charge_person_id
    LEFT JOIN sys_dept sd
    on sd.dept_id=p.dept_id
	 		LEFT JOIN miluo_video vi
	         on vi.equment_id=p.station_code
	         LEFT JOIN miluo_site site
	         on site.id=vi.site_id
         
         LEFT JOIN miluo_site sites
         on sites.id=p.station_code
    	WHERE p.status=#status# and p.id=#id#

TaskInfoById
===
      	 select * FROM miluo_task_info p  
          	WHERE p.id=#id#
	
condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(stationCode)){
	 and station_code=#stationCode#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(degreeEmergency)){
	 and degree_emergency=#degreeEmergency#
	@}
	@if(!isEmpty(deptId)){
	 and dept_id=#deptId#
	@}
	@if(!isEmpty(chargePersonId)){
	 and charge_person_id=#chargePersonId#
	@}
	@if(!isEmpty(taskType)){
	 and task_type=#taskType#
	@}
	@if(!isEmpty(startTime)){
	 and start_time=#startTime#
	@}
	@if(!isEmpty(endTime)){
	 and end_time=#endTime#
	@}
	@if(!isEmpty(status)){
	 and status=#status#
	@}
	@if(!isEmpty(pubPersonId)){
	 and pub_person_id=#pubPersonId#
	@}
	@if(!isEmpty(remark)){
	 and remark=#remark#
	@}
	