package com.rate.system.rate_system.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.rate.system.rate_system.config.BootdoConfig;
import com.rate.system.rate_system.dao.FileDao;
import com.rate.system.rate_system.entity.FileDO;
import com.rate.system.rate_system.service.FileService;
import com.rate.system.rate_system.utils.FileType;
import com.rate.system.rate_system.utils.FileUtil;
import com.rate.system.rate_system.utils.IdGen;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileDao sysFileMapper;

	@Autowired
	private BootdoConfig bootdoConfig;

	@Override
	public FileDO get(String id) {
		return sysFileMapper.single(id);
	}

	@Override
	public List<FileDO> list(Map<String, Object> map) {
		List<FileDO> files = sysFileMapper.list(map);
		return files;
	}

	@Override
	public int count(Map<String, Object> map) {
		return sysFileMapper.count(map);
	}

	@Override
	public void save(FileDO sysFile) {
		sysFileMapper.insert(sysFile);
	}
   /**
    * @Param [sysFile]
    * @Return java.lang.Object
    * @author xiaoshi
    * @Description
    * @Date 2019/7/6
    * @Time 16:52
    **/
	@Override
	public Object saves(FileDO sysFile) {
		return sysFileMapper.insertReturnKey(sysFile).getKey();
	}

	@Override
	public List<FileDO> getListByIds(@Param("ids") String[] ids) {
		return sysFileMapper.getListByIds(ids);
	}

	@Override
	public void deleteByIds(String[] ids) {
		sysFileMapper.deleteByIds(ids);
	}

	@Override
	public int update(FileDO sysFile) {
		return sysFileMapper.updateTemplateById(sysFile);
	}

	@Override
	public int remove(String id) {
		return sysFileMapper.deleteById(id);
	}

	@Override
	public int batchRemove(String[] ids) {
		return sysFileMapper.batchRemove(ids);
	}

	@Override
	public Boolean isExist(String url) {
		Boolean isExist = false;
		if (!StringUtils.isEmpty(url)) {
			String filePath = url.replace("/files/", "");
			filePath = bootdoConfig.getUploadPath() + filePath;
			File file = new File(filePath);
			if (file.exists()) {
				isExist = true;
			}
		}
		return isExist;
	}

	@Override
	public int down(HttpServletResponse resp, String fileName,String realPath) {
		try {
			fileName = new String(fileName.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String path = realPath + File.separator + fileName;
		File file = new File(path);
		resp.reset();
		resp.setContentType("application/octet-stream");
		resp.setCharacterEncoding("utf-8");
		resp.setContentLength((int) file.length());
		resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = resp.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(file));
			int i = 0;
			while ((i = bis.read(buff)) != -1) {
				os.write(buff, 0, i);
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	@Override
	public void save(MultipartFile file, String id,String flag) {
		String fileTitl = file.getOriginalFilename();
		String fileName = FileUtil.renameToUUID(fileTitl);
		BigDecimal fileSize = new BigDecimal(file.getSize());
		String fileExt =  "." +fileTitl.substring(fileTitl.lastIndexOf(".") + 1);
		FileDO sysFile = new FileDO(id,FileType.fileType(fileName),fileExt,flag,fileTitl, fileSize,fileName,"/"+fileName, new Date());
		sysFileMapper.insert(sysFile, true);
	}
}
