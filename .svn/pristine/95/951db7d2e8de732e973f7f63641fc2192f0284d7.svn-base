findParamList
===
      SELECT s.name site_name,
      v.name video_name,
      p.name,
      p.video_type,
      p.unit,
      pv.* 
      FROM miluo_factor_params_video pv 
      LEFT JOIN miluo_factor_params p ON pv.param_id = p.id
      LEFT JOIN miluo_video v ON pv.video_id = v.equment_id
      LEFT JOIN miluo_site s ON s.id = v.site_id
      WHERE s.id = #siteId#
      @if(!isEmpty(pluName) && pluName != ''){
          AND (p.name like #'%' +pluName+ '%'# OR p.sign_name like #'%' +pluName+ '%'#)
      @}
      ORDER BY video_name
      
findVideoInfo
===
    SELECT s.name site_name,v.name video_name,equment_id video_id,type video_type FROM miluo_video v 
    LEFT JOIN miluo_site s ON s.id = v.site_id
    WHERE s.id = #siteId#
    
findInputFactorList
===    
    SELECT * FROM `miluo_factor_params` p
    WHERE video_type = (SELECT `type` FROM miluo_video WHERE equment_id = #videoId#)

findParamListByType
===
    SELECT * FROM `miluo_factor_params` WHERE video_type = #type#
    
findParamAndLimit
=== 
    SELECT p.id param_id,p.name,p.sign_name,p.video_type,p.unit,
    pv.id,pv.video_id,pv.need_alarm,
    pv.limit_top1,pv.limit_top2,pv.limit_top3,
    pv.limit_bottom1,pv.limit_bottom2,pv.limit_bottom3,
    pv.alarm_level_top_to_fzr,pv.alarm_level_top_to_hbj,
    pv.alarm_level_bottom_to_fzr,pv.alarm_level_bottom_to_hbj,
    pv.update_time
    FROM miluo_factor_params p LEFT JOIN `miluo_factor_params_video` pv
    ON p.id = pv.param_id AND video_id = #equmentId#
    WHERE video_type = #type#