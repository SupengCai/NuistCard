package com.linklife.service.impl;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.linklife.service.IUploadService;

@Service
public class UploadServiceImpl implements IUploadService {
	Logger logger = Logger.getLogger(UploadServiceImpl.class);

	public void upLoadFiles(String savepath, MultipartFile[] files) {
		for (MultipartFile myfile : files) {
			if (!myfile.isEmpty()) {
				System.out.println("文件长度: " + myfile.getSize());
				System.out.println("文件类型: " + myfile.getContentType());
				System.out.println("文件名称: " + myfile.getName());
				System.out.println("文件原名: " + myfile.getOriginalFilename());
				System.out.println("========================================");
				try {
					myfile.transferTo(new File(savepath, myfile
							.getOriginalFilename()));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					throw new RuntimeException(e.getMessage());
				}
			}
		}
	}
}
