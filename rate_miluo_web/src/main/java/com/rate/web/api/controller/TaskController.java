package com.rate.web.api.controller;

import com.rate.system.rate_system.api.service.ApiLoginService;
import com.rate.system.rate_system.config.BootdoConfig;
import com.rate.system.rate_system.entity.FileDO;
import com.rate.system.rate_system.service.FileService;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.*;
import com.rate.web.site.service.SiteService;
import com.rate.web.task.entity.MiluoTaskInfo;
import com.rate.web.task.service.MiluoTaskInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * 任务管理
 *
 * @ClassName TaskController
 * @Author liuyong
 * @Date 2019/6/11 13:59
 * @Version 1.0
 **/
@RestController
@RequestMapping("api/task")
public class TaskController {

    @Autowired
    private ApiLoginService apiLoginService;

    @Autowired
    private MiluoTaskInfoService miluoTaskInfoService;

    @Autowired
    private SiteService siteService;


    @Autowired
    private FileService sysFileService;

    @Autowired
    private UserService userService;

    @Autowired
    private BootdoConfig bootdoConfig;

    /**
     * 任务管理
     *
     * @param params
     * @return
     */
    @RequestMapping("getTaskList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "任务状态", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "起始值", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的数量", paramType = "query", dataType = "Integer"),
    })
    public Result getTaskList(@RequestParam Map<String, Object> params) {
        try {
            if (params == null) {
                return Result.error(CodeMsg.PARAMETER_ISNULL);
            }
            boolean checkLogin = apiLoginService.checkLogin((String) params.get("userId"), (String) params.get("code"));
            if (!checkLogin) {
                //用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }
            List<Long> roleList = miluoTaskInfoService.findRoleList(Long.parseLong((String) params.get("userId")));

            //查询该用户能查看到的站点任务
//            List<String> siteIds = siteService.querySiteByUser((String) params.get("userId"));
//            if(siteIds == null || siteIds.isEmpty()){
//                return Result.success();
//            }
//            params.put("siteIds",siteIds);
           /* params.put("pageNumber", Integer.parseInt((String) params.get("pageNum")));
            params.put("pageSize", Integer.parseInt((String) params.get("pageSize")));*/
            String pageNumber = String.valueOf(params.get("pageNum"));
            String pageSize = String.valueOf(params.get("pageSize"));
            Long s= Long.valueOf(pageNumber);
            Long s2= Long.valueOf(pageSize);
            if (roleList == null || roleList.isEmpty()) {
                return Result.success();
            } else {
                List<MiluoTaskInfo> miluoTaskInfos= new ArrayList<>();

                String status = (String) params.get("status");
                if ("0".equals(status)){
                    params.put("roleId", roleList.get(0));
                    PageQuery<MiluoTaskInfo> pageQuery = new PageQuery<>(s, s2, params);
                    PageQuery<MiluoTaskInfo> page = miluoTaskInfoService.list(pageQuery);
                    miluoTaskInfos.addAll(page.getList());
                }else {
                    params.put("roleId", roleList.get(0));
                    PageQuery<MiluoTaskInfo> pageQuery = new PageQuery<>(s, s2, params);
                    PageQuery<MiluoTaskInfo> page = miluoTaskInfoService.historyList(pageQuery);
                    miluoTaskInfos.addAll(page.getList());
                }

             /*   for (int i = 0; i < roleList.size(); i++) {
                    //如果roleId 为1表示系统管理员，可以查看所有任务
                    if (roleList.get(i) == 1L) {
                        params.put("roleId", roleList.get(i));
                        List<MiluoTaskInfo> miluoTaskInfos1 = miluoTaskInfoService.queryTaskList(params);
                        miluoTaskInfos.addAll(miluoTaskInfos1);
                        break;
                        //如果roleId 为77表示环保局，可以发布人是自己的所有任务
                    } else if (roleList.get(i) == 77L) {
                        // 其他 表示只能看见处理人为自己的任务
                        params.put("roleId", roleList.get(i));
                        List<MiluoTaskInfo> miluoTaskInfos1 = miluoTaskInfoService.queryTaskList(params);
                        miluoTaskInfos.addAll(miluoTaskInfos1);
                        break;
                    } else {
                        params.put("roleId", roleList.get(i));
                        List<MiluoTaskInfo> miluoTaskInfos1 = miluoTaskInfoService.queryTaskList(params);
                        miluoTaskInfos.addAll(miluoTaskInfos1);
                        break;
                    }
                }*/
                for (MiluoTaskInfo miluoTaskInfo : miluoTaskInfos) {
                    String pictures="";
                    String picture = miluoTaskInfo.getPhoto();
                    ArrayList<FileDO> fileDOS = new ArrayList<>();
                    if (picture!=null && picture!="") {
                        if (picture.split(",").length > 0) {
                            for (String picId : picture.split(",")) {

                                if (StringUtils.isNotBlank(picId)) {
                                    FileDO fileDO = sysFileService.get(picId);
                                    if (fileDO!=null) {
                                        fileDOS.add(fileDO);
                                        /*String url = fileDO.getUrl();
                                        if (url != null) {
                                            pictures = pictures + "," +fileDO.+ url;
                                        }*/
                                    }
                                }

                            }
                        }
                    }
                    miluoTaskInfo.setFileDOlist(fileDOS);
                }
                return Result.success(miluoTaskInfos);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

    /**
     * 添加任务
     *
     * @param taskInfo 任务对象
     * @return
     */
    @RequestMapping("saveTask")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "stationCode", value = "设备编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "任务名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "taskType", value = "任务类型", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "degreeEmergency", value = "紧急程度", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "任务描述", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "chargePersonId", value = "负责人", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "预计结束时间", paramType = "query", dataType = "String"),

    })
    public Result saveTask(String userId, String code, MiluoTaskInfo taskInfo, String startTime, String endTime) {
        try {
            boolean checkLogin = apiLoginService.checkLogin(userId, code);
            if (!checkLogin) {
                //用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }
            taskInfo.setId(IdGen.uuid());
            taskInfo.setPubPersonId(userId);
            taskInfo.setStartTime(DateUtils.format(startTime, "yyyy-MM-dd HH:mm:ss"));
            taskInfo.setEndTime(DateUtils.format(endTime, "yyyy-MM-dd HH:mm:ss"));
            miluoTaskInfoService.save(taskInfo);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

    /**
     * 修改保存
     *
     * @param userId
     * @param code
     * @param taskInfo
     * @return
     */
    @RequestMapping("updateTaskById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "stationCode", value = "设备编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "任务名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "taskType", value = "任务类型", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "degreeEmergency", value = "紧急程度", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "任务描述", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "chargePersonId", value = "负责人", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "预计结束时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "photo", value = "照片id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "id", value = " id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "taskNo", value = "任务编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(1:已处理0未处理)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pubPersonId", value = "发布人id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "seriousExceed", value = "是否严重超标（1是，其他否）", paramType = "query", dataType = "String")
    })
    public Result updateTaskById(String userId, String code, MiluoTaskInfo taskInfo) {
        try {
            boolean checkLogin = apiLoginService.checkLogin(userId, code);
            if (!checkLogin) {
                //用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }
            //修改任务
            miluoTaskInfoService.updateTemplateById(taskInfo);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

    /**
     * 删除图片
     *
     * @param imgId        图片id
     * @param workerId     职工id
     * @param enterpriseId 企业id
     * @return java.lang.String
     * @date 2019/2/13 16:18
     **/
    @Value(value = "${rate.uploadPath}")
    private String path;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "imgId", value = "图片id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "miluoTaskInfoId", value = "id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String")
//            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String")
    })
    @GetMapping("/deleteImg")
    @ResponseBody
    public Result deleteImg(String imgId, String miluoTaskInfoId,String userId) {
        try {
           /* boolean b = apiLoginService.checkLogin(userId, code);
            if (!b) {
                return Result.error(CodeMsg.VALIDATA_ERROR);
            } else {*/
            //全部照片id
            String pic = null;
            //被删除照片id
            String url = sysFileService.get(imgId).getUrl();
            if (miluoTaskInfoId != null && StringUtils.isNotBlank(miluoTaskInfoId)) {
                MiluoTaskInfo miluoTaskInfo	=miluoTaskInfoService.TaskInfoById(miluoTaskInfoId);
                pic = miluoTaskInfo.getPhoto();
                pic = pic.replace(imgId + ",", "");
                miluoTaskInfo.setPhoto(pic);

                miluoTaskInfoService.updateTemplateById(miluoTaskInfo);
            }
            url = path + url.replace("/files/", "");
            url.replaceAll("//", "/");
            sysFileService.remove(imgId);
            File file = new File(url);
            file.delete();
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_EXCEPTION);
        }
    }

    /**
     * @Param [file3]
     * @Return java.lang.String
     * @author xiaoshi
     * @Description  图片的上传功能
     * @Date 2019/7/6
     * @Time 13:15
     **/
    @PostMapping("/upload")
    @ResponseBody
    public Result upload(String fileList) {

        String[] fileLists = fileList.split("uploadImg");
        List<String> urlList=new ArrayList<>();
        try {
            List<FileDO> fileDOS=  new ArrayList<>();
            for (int i = 0; i < fileLists.length; i++) {
                String filePath = bootdoConfig.getUploadPath();
                File targetFile = new File(filePath);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }

                //转成图片并保存到本地服务器
                String url= UUID.randomUUID()+".png";
                boolean image = Base64Utils.generateImage(fileLists[i],filePath+url);
                if (image){
                    //插入文件表
                    FileDO filedo = new FileDO();
                    String fileId = IdGen.uuid();
                    filedo.setId(fileId);
                    filedo.setFilesize( new BigDecimal(fileLists[i].length()));
                    filedo.setFileext(".png");
                    filedo.setPathName(url);
                    filedo.setUrl("/files/"+url);
                    filedo.setCreateDate(new Date());
                    sysFileService.save(filedo);
                    fileDOS.add(filedo);
                    urlList.add(filedo.getId());
                }else {
                    return Result.error(CodeMsg.SERVER_EXCEPTION);
                }
            }
          //  String url = String.join(",", urlList);
            return Result.success(fileDOS);

        } catch (Exception e1) {
            e1.printStackTrace();
            return Result.error(CodeMsg.SERVER_EXCEPTION);
        }

    }

    @RequestMapping("getHandler")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fzr", value = "负责人", paramType = "query", dataType = "String"),
    })
    public Result getHandler(@RequestParam("userId") String userId, @RequestParam("code") String code,
                             @RequestParam("fzr") String fzr) {
        try {
            boolean checkLogin = apiLoginService.checkLogin(userId, code);
            if (!checkLogin) {
                //用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }
            return Result.success(userService.queryById(fzr));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

}
