package com.example.demo.common;

import com.example.demo.user.domain.entity.ImageFile;
import com.example.demo.user.dto.CkPhotoDto;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CkPhotoController {

  @Resource(name = "fileUtils")
  private com.example.demo.common.FileUtils fileUtils;

  //단일파일업로드
  @RequestMapping(value = "/ckPhotoUpload.do", produces = "text/html; charset=utf8")
  @ResponseBody
  public String ckPhotoUpload(HttpServletRequest request, CkPhotoDto ckPhotoDto) {
    System.out.println("START");

    String path ="C:\\Users\\htoh\\demo\\src\\main\\resources\\static\\images\\ckeditor\\";

    System.out.println("0 : " + path);
    MultipartFile upload = ckPhotoDto.getUpload();
    String CKEditorFuncNum = "";
    String msg = "";
    String fileUrl = "";

    System.out.println("1 : " + path);

    if (upload != null) {
      String original_name = upload.getOriginalFilename();
      String ext = original_name.substring(original_name.lastIndexOf(".") + 1);
      System.out.println("2 : " + original_name);

      ext = ext.toLowerCase();

      if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif")) {

        ckPhotoDto.setFilename(original_name);
        CKEditorFuncNum = ckPhotoDto.getCKEditorFuncNum();
        try {
          File file = new File(path);
          System.out.println("3 : " + path);
          //디렉토리 존재하지 않을경우 디렉토리 생성
          if (!file.exists()) {
            file.mkdirs();
            System.out.println("4 : " + path);
          }

          //서버에 업로드 할 파일명(한글문제로 인해 원본파일은 올리지 않는것이 좋음)
          String realname = UUID.randomUUID().toString() + "." + ext;
          System.out.println("5 : " + realname);
          upload.transferTo(new File(path + realname));
          System.out.println("6 : " + path + realname);

          //fileUrl = "/img.do?realname=" + realname + "&original_name=" + original_name;
          //fileUrl = "http://localhost:8080/images/ckeditor/"+realname;
            fileUrl = "/photoDownload?realname=" + realname + "&original_name=" + original_name;
          msg = "이미지를 업로드 하였습니다";
        } catch (IOException e) {
          e.printStackTrace();
        }

      } else {
        msg = "사진 파일만 업로드 할수있습니다";
      }
    }
    System.out.println("7 : Done");
    return "<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction('"
        + CKEditorFuncNum + "', '" + fileUrl + "', '" + msg + "');</script>";

  }

  //이미지 다운로드
  @GetMapping(value="/photoDownload")
  public void photoDownload(HttpServletRequest request, HttpServletResponse response) {

    fileUtils.imageDown(response, request);
  }
}
