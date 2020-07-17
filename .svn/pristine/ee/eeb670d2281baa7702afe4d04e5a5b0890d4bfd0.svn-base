sample
===
* 注释

	select #use("cols")# from sys_task  where  #use("condition")#

cols
===
	id,cron_expression,method_name,is_concurrent,description,update_by,bean_class,create_date,job_status,job_group,update_date,create_by,spring_bean,job_name

updateSample
===
	
	id=#id#,cron_expression=#cronExpression#,method_name=#methodName#,is_concurrent=#isConcurrent#,description=#description#,update_by=#updateBy#,bean_class=#beanClass#,create_date=#createDate#,job_status=#jobStatus#,job_group=#jobGroup#,update_date=#updateDate#,create_by=#createBy#,spring_bean=#springBean#,job_name=#jobName#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(cronExpression)){
	 and cron_expression=#cronExpression#
	@}
	@if(!isEmpty(methodName)){
	 and method_name=#methodName#
	@}
	@if(!isEmpty(isConcurrent)){
	 and is_concurrent=#isConcurrent#
	@}
	@if(!isEmpty(description)){
	 and description=#description#
	@}
	@if(!isEmpty(updateBy)){
	 and update_by=#updateBy#
	@}
	@if(!isEmpty(beanClass)){
	 and bean_class=#beanClass#
	@}
	@if(!isEmpty(createDate)){
	 and create_date=#createDate#
	@}
	@if(!isEmpty(jobStatus)){
	 and job_status=#jobStatus#
	@}
	@if(!isEmpty(jobGroup)){
	 and job_group=#jobGroup#
	@}
	@if(!isEmpty(updateDate)){
	 and update_date=#updateDate#
	@}
	@if(!isEmpty(createBy)){
	 and create_by=#createBy#
	@}
	@if(!isEmpty(springBean)){
	 and spring_bean=#springBean#
	@}
	@if(!isEmpty(jobName)){
	 and job_name=#jobName#
	@}
	
batchRemove
===

	delete from sys_task where id in (#join(ids)#)
	
queryByCondition
===

	select #use("cols")# from sys_task  where  1=1 
	@if(!isEmpty(params['jobName']) && params['jobName'] !=''){
	 and job_name like #'%'+params['jobName']+'%'#
	@}
	@if(!isEmpty(params['ordername']) && params['ordername'] !=''){
	 order by #params['ordername']# 
	@if(!isEmpty(params['order']) && params['order'] !=''){
	 #params['order']#
	@}}	else{
	 order by id desc
	@}