package com.example.demo.common;

import com.example.demo.user.service.FileService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
@ResponseBody
public class EditorController {

  @Resource(name = "fileUtils")
  private com.example.demo.common.FileUtils fileUtils;

  private FileService fileService;
/*
  @PostMapping(value="/photoUpload")
  @ResponseBody
  public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request)
      throws Exception {
    String referer = (String)request.getHeader("REFERER");
    String whatkind = "summernoteImage";
    String fileRoot = "";

    List<Map<String, Object>> file_list = fileUtils
        .parseInsertFileInfo(request, "file", whatkind, true);

    ImageFileDto imageFileDto = new ImageFileDto();
    imageFileDto.setOriginalFileName((String) file_list.get(0).get("ORIGINAL_FILE_NAME"));
    imageFileDto.setStoredFileName((String) file_list.get(0).get("STORED_FILE_NAME"));
    imageFileDto.setFileSize(file_list.get(0).get("FILE_SIZE") + "");
    imageFileDto.setFilePath((String) file_list.get(0).get("FILE_PATH"));

    fileService.imageFileSave(imageFileDto);


    String imageSeq =  Integer.toString(fileService.getFileSeq());

    //String result = "<img src=\"http://localhost:8080/photoDownload?fileSeq="+imageSeq+"\">";
    String result = "<img src=\"http://localhost:8080/photoDownload?fileSeq="+imageSeq+"\">";



    return result;
  }
*/

  //이미지 다운로드
  /*@GetMapping(value="/photoDownload")
  public void photoDownload(@RequestParam int imageFileSeq, HttpServletRequest request, HttpServletResponse response) {
    ImageFile imageFile = fileService.selectImageFile(imageFileSeq);

    fileUtils.DownloadFile("summernoteImage", imageFile, response, request);
  }*/


  /*@PostMapping(value="/photoUpload")
  @ResponseBody
  public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request){
    String whatkind = "summernoteImage";
    String fileRoot = "";


    fileRoot = "C:\\Users\\htoh\\demo\\src\\main\\resources\\static\\images\\"+whatkind+"\\"; //저장될 외부 파일 경로  (개발)
    //fileRoot = "/engine/tomcat_home/webapps/ROOT/assets/images/"+whatkind+"/";	//저장될 외부 파일 경로  (운영)

    String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
    String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

    String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명

    File targetFile = new File(fileRoot + savedFileName);
    String result = "";

    try {
      InputStream fileStream = multipartFile.getInputStream();
      org.apache.commons.io.FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장

      //result = "<img src=\"http://localhost:8080/images/"+whatkind+"/"+ savedFileName + "\">";
      result =  "<img src=\"http://localhost:8080/images/"+whatkind+"/"+ savedFileName + "\">";
    } catch (IOException e) {
      org.apache.commons.io.FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
      e.printStackTrace();
    }

    return result;
  }*/

  @PostMapping(value="/photoUpload")
  @ResponseBody
  public JSONObject uploadSummernoteImageFile (@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request){

    String whatkind = "summernoteImage";
    String fileRoot = "";

    fileRoot = "C:\\Users\\htoh\\demo\\src\\main\\resources\\static\\images\\"+whatkind+"\\"; //저장될 외부 파일 경로  (개발)

    String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
    String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

    String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명

    File targetFile = new File(fileRoot + savedFileName);
    String uploadPath = "";
    JSONObject json = new JSONObject();

    try {
      InputStream fileStream = multipartFile.getInputStream();
      org.apache.commons.io.FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장

      //result = "<img src=\"http://localhost:8080/images/"+whatkind+"/"+ savedFileName + "\">";
      uploadPath =  "/images/"+whatkind+"/"+ savedFileName;
      json.put("url", uploadPath);

    } catch (IOException e) {
      org.apache.commons.io.FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
      e.printStackTrace();
    }


    return json;
  }

}
