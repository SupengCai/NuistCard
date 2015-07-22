package com.linklife.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
	public void upLoadFiles(String savepath,MultipartFile[] files);
}
