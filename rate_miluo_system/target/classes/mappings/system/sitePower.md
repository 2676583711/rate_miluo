
sample
===
* 注释

	select #use("cols")# from air_site_power  where  #use("condition")#

cols
===
	id,site_code,role_id

updateSample
===
	
	id=#id#,site_code=#siteCode#,role_id=#roleId#

listSiteCodesByRoleId
===
	
	select site_code from air_site_power  where #use("condition")#

deleteByRoleId
===
	
	delete from miluo_site_power where #use("condition")#

getSiteCodesByUser
===
	SELECT
	site_code 
    FROM
	air_site_power 
    WHERE
	role_id in ( SELECT role_id FROM sys_user_role WHERE user_id = #userId#)
	
getSiteCodesByUserId
===
    select site_code from miluo_site_power
    where user_id = #userId#

getCountByUserId
===
    select count(user_id) from miluo_site_power
    where user_id = #userId#

deleteByUserId
===
    delete from miluo_site_power
    where user_id = #userId#
    
deleteBySiteId
===
    delete from miluo_site_power
    where site_code = #siteCode#
getAllSiteId
===
    select id from miluo_site

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(siteCode)){
	 and site_code=#siteCode#
	@}
	@if(!isEmpty(roleId)){
	 and role_id=#roleId#
	@}
	
	
	
getSitePowerList
===
        SELECT site_code FROM miluo_site_power
        WHERE role_id=#roleId#	
	