package com.example.demo.user.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@EqualsAndHashCode(callSuper = false)
public class CkPhotoVo {
	 private static final long serialVersionUID = 1L;
	
	 String attach_path;
     private MultipartFile upload;
     private String filename;
     private String CKEditorFuncNum;

}
