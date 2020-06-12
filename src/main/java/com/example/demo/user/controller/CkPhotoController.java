package com.example.demo.user.controller;

import com.example.demo.user.dto.CkPhotoVo;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CkPhotoController {

		//단일파일업로드
		@RequestMapping(value = "/ckeditor/ckPhotoUpload.do", produces = "text/html; charset=utf8")
		@ResponseBody
		public String ckPhotoUpload(HttpServletRequest request, CkPhotoVo ckPhotoVO) {
	System.out.println("START");		
		    //String path = "/engine/tomcat_home/webapps/ROOT/assets/images/";
			String path = "C:\\workspace\\alcard_mngt_2020\\webapp\\assets\\images\\";
	System.out.println("0 : " + path);
		    MultipartFile upload = ckPhotoVO.getUpload();
		    String CKEditorFuncNum = "";
		    String msg = "";
		    String fileUrl = "";

	System.out.println("1 : " + path);
		    
		    if (upload != null) {
		    	String original_name = upload.getOriginalFilename();
	            String ext = original_name.substring(original_name.lastIndexOf(".")+1);
	System.out.println("2 : " + original_name);
		        
				ext = ext.toLowerCase();
				
				if(ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif")) {
						
			        ckPhotoVO.setFilename(original_name);
			        CKEditorFuncNum = ckPhotoVO.getCKEditorFuncNum();
			        try {
			            File file = new File(path);
	System.out.println("3 : " + path);
			          //디렉토리 존재하지 않을경우 디렉토리 생성
			            if(!file.exists()) {
			                file.mkdirs();
	System.out.println("4 : " + path);
			            }
			            
			            //서버에 업로드 할 파일명(한글문제로 인해 원본파일은 올리지 않는것이 좋음)
			            String realname = UUID.randomUUID().toString() + "." + ext;
	System.out.println("5 : " + realname);
			            upload.transferTo(new File(path+realname));
	System.out.println("6 : " + path+realname);
		
						fileUrl = "/ckeditor/ckImgSubmit.do?realname=" + realname + "&original_name=" + original_name;
						msg = "이미지를 업로드 하였습니다";
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			        
				} else {
					msg = "사진 파일만 업로드 할수있습니다";
				}
		    }
		    
	System.out.println("7 : Done");
		     return "<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction('"+CKEditorFuncNum+"', '"+fileUrl+"', '"+msg+"');</script>";

	    }
		
		
		@RequestMapping(value="/ckeditor/ckImgSubmit.do")
		public void ckSubmit(@RequestParam(value="realname") String realname
									, @RequestParam(value="original_name") String original_name
		                            , HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		        
			//서버에 저장된 이미지 경로
			String path = "C:\\workspace\\alcard_mngt_2020\\webapp\\assets\\images\\";
			//String sDirPath = path + realname + "_" + fileName;
			
			File imgFile = new File(path+realname);
			
			//사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
			if(imgFile.isFile()){
			    byte[] buf = new byte[1024];
			    int readByte = 0;
			    int length = 0;
			    byte[] imgBuf = null;
			    
			    FileInputStream fileInputStream = null;
			    ByteArrayOutputStream outputStream = null;
			    ServletOutputStream out = null;
			    
			    try{
			        fileInputStream = new FileInputStream(imgFile);
			        outputStream = new ByteArrayOutputStream();
			        out = response.getOutputStream();
			        
			        while((readByte = fileInputStream.read(buf)) != -1){
			            outputStream.write(buf, 0, readByte);
			        }
			        
			        imgBuf = outputStream.toByteArray();
			        length = imgBuf.length;
			        out.write(imgBuf, 0, length);
			        out.flush();
			        
			    }catch(IOException e){
			    	e.printStackTrace();
			    }finally {
			    		
			    }
			}
		}
	    
	}





