package com.rate.system.rate_system.utils;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rate.system.rate_system.config.BootdoConfig;
import com.rate.system.rate_system.entity.FileDO;
import com.rate.system.rate_system.service.FileService;

/**
 * 
 * @author jinjichang
 * @date 2018-04-12
 */
@Component
public class FileSaveUtil {
	// Bootdo配置
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService fileService;

	public FileDO saveFile(MultipartFile file) {
		if(file != null && !file.isEmpty()) {
			String fileTitl = file.getOriginalFilename();
			String fileName = FileUtil.renameToUUID(fileTitl);
			BigDecimal fileSize = new BigDecimal(file.getSize());
			String fileExt = "." + fileTitl.substring(fileTitl.lastIndexOf(".") + 1);
			FileDO sysFile = new FileDO(IdGen.uuid(), FileType.fileType(fileName), fileExt, "", fileTitl, fileSize,
					fileName, "/files/" + fileName, new Date());
			try {
				FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
				fileService.save(sysFile);
				return sysFile;
			} catch (Exception e) {
				return null;
			}
		}else {
			return null;
		}
	}
	
	public FileDO savebyte(byte[] bytes,MultipartFile file) {
		if(file != null ) {
			String fileTitl = file.getOriginalFilename();
			String fileName = FileUtil.renameToUUID(fileTitl);
			BigDecimal fileSize = new BigDecimal(file.getSize());
			String fileExt = "." + fileTitl.substring(fileTitl.lastIndexOf(".") + 1);
			FileDO sysFile = new FileDO(IdGen.uuid(), FileType.fileType(fileName), fileExt, "", fileTitl, fileSize,
					fileName, "/files/" + fileName, new Date());
			try {
				FileUtil.uploadFile(bytes, bootdoConfig.getUploadPath(), fileName);
//                Long id = (Long)fileService.save(sysFile);
				fileService.save(sysFile);
//				sysFile.setId(id);
				return sysFile;
			} catch (Exception e) {
				return null;
			}
		}else {
			return null;
		}
	}


	public FileDO saveByte(byte[] bytes,MultipartFile file) {
		if(file != null ) {
			String fileTitl = file.getOriginalFilename();
			String fileName = FileUtil.renameToUUID(fileTitl);
			BigDecimal fileSize = new BigDecimal(file.getSize());
			String fileExt = "." + fileTitl.substring(fileTitl.lastIndexOf(".") + 1);
			FileDO sysFile = new FileDO(IdGen.uuid(),FileType.fileType(fileName), fileExt, "", fileTitl, fileSize,
					fileName, "/files/" + fileName, new Date());
			try {
				FileUtil.uploadFile(bytes, bootdoConfig.getUploadPath(), fileName);
				String  id = (String)fileService.saves(sysFile);
				sysFile.setId(id);
				return sysFile;
			} catch (Exception e) {
				return null;
			}
		}else {
			return null;
		}
	}

}
