
sample
===
* 注释

	select #use("cols")# from miluo_vidicon  where  #use("condition")#

cols
===
	id,site_id,vidicon_name,series_number,owner_id,supplier,hlshd_url,hls_url,rtmphd_url,rtmp_url,ezopenhd_url,ezopen_url,ezopen_playback_url,vidicon_location,installation_date

updateSample
===
	
	id=#id#,site_id=#siteId#,vidicon_name=#vidiconName#,series_number=#seriesNumber#,owner_id=#ownerId#,supplier=#supplier#,hlshd_url=#hlshdUrl#,hls_url=#hlsUrl#,rtmphd_url=#rtmphdUrl#,rtmp_url=#rtmpUrl#,ezopenhd_url=#ezopenhdUrl#,ezopen_url=#ezopenUrl#,ezopen_playback_url=#ezopenPlaybackUrl#,vidicon_location=#vidiconLocation#,installation_date=#installationDate#

getVidiconByVidiconId
===
	select v.*,vo.access_token accessToken
	from miluo_vidicon v 
	left join miluo_vidicon_owner vo on v.owner_id=vo.owner_id
	where  v.id=#vidiconId#

getVidiconsBySiteId
===
	select v.*,vo.access_token accessToken
	from miluo_vidicon v
	left join miluo_vidicon_owner vo on v.owner_id=vo.owner_id
	where  v.site_id=#siteId#
	
	
getVidiconsById
===
	select v.*,vo.access_token accessToken
	from miluo_vidicon v
	left join miluo_vidicon_owner vo on v.owner_id=vo.owner_id
	where v.site_id=#id#	

findVidiconList
===
    SELECT s.`name` site_name,v.*,o.`owner_name` FROM `miluo_vidicon` v LEFT JOIN `miluo_site` s ON v.`site_id` = s.`id` LEFT JOIN `miluo_vidicon_owner` o 
    ON v.`owner_id` = o.`owner_id`
    WHERE s.`status` = 1
    @if(!isEmpty(ids) && ids != ''){
        and v.id in (#join(ids)#)
    @}
    @if(!isEmpty(name) && name != ''){
       and v.vidicon_name like #'%'+name+'%'#
    @}
    order by v.site_id
    
findSiteListByUserId    
===
    SELECT * FROM `miluo_site` 
    WHERE id IN (SELECT site_code FROM `miluo_site_power` WHERE user_id = #userId#)
    ORDER BY id
    
condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(siteId)){
	 and site_id=#siteId#
	@}
	@if(!isEmpty(vidiconName)){
	 and vidicon_name=#vidiconName#
	@}
	@if(!isEmpty(seriesNumber)){
	 and series_number=#seriesNumber#
	@}
	@if(!isEmpty(ownerId)){
	 and owner_id=#ownerId#
	@}
	@if(!isEmpty(supplier)){
	 and supplier=#supplier#
	@}
	@if(!isEmpty(hlshdUrl)){
	 and hlshd_url=#hlshdUrl#
	@}
	@if(!isEmpty(hlsUrl)){
	 and hls_url=#hlsUrl#
	@}
	@if(!isEmpty(rtmphdUrl)){
	 and rtmphd_url=#rtmphdUrl#
	@}
	@if(!isEmpty(rtmpUrl)){
	 and rtmp_url=#rtmpUrl#
	@}
	@if(!isEmpty(ezopenhdUrl)){
	 and ezopenhd_url=#ezopenhdUrl#
	@}
	@if(!isEmpty(ezopenUrl)){
	 and ezopen_url=#ezopenUrl#
	@}
	@if(!isEmpty(ezopenPlaybackUrl)){
	 and ezopen_playback_url=#ezopenPlaybackUrl#
	@}
	@if(!isEmpty(vidiconLocation)){
	 and vidicon_location=#vidiconLocation#
	@}
	@if(!isEmpty(installationDate)){
	 and installation_date=#installationDate#
	@}
	
	