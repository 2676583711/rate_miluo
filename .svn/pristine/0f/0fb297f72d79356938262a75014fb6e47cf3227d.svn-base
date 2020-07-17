package com.rate.system.rate_system.service;

import com.rate.system.rate_system.entity.FileDO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
public interface FileService {
	
	FileDO get(String id);
	
	List<FileDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	void save(FileDO sysFile);
	
	void save(MultipartFile file,String id,String flag);
	
	int update(FileDO sysFile);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
	
	/**
	 * 判断一个文件是否存在
	 * @param url FileDO中存的路径
	 * @return
	 */
    Boolean isExist(String url);
    
    //下载
    int down(HttpServletResponse response,String fileName,String realPath);

    /**
     * @Param [sysFile]
     * @Return java.lang.Object
     * @author xiaoshi       
     * @Description  
     * @Date 2019/7/6
     * @Time 17:41
     **/
	Object saves(FileDO sysFile);

	List<FileDO> getListByIds(String[] ids);

	void deleteByIds(String[] ids);
}
