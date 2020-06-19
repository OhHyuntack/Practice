package com.example.demo.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CkPhotoDto {

  String attach_path;
  private MultipartFile upload;
  private String filename;
  private String CKEditorFuncNum;


}
