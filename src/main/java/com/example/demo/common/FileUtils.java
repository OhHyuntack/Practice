package com.example.demo.common;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils {
	
//	@Value("#{configUpload}")
//   private HashMap<String, String> configUpload;
	
	public List<Map<String,Object>> parseInsertFileInfo(HttpServletRequest request, String paramName, String whatKind, boolean isPhoto) throws Exception{
		
//		String Path = configUpload.get("upload"+WordUtils.capitalize(whatKind));
		String Path = "C:/UPLOAD_FILES/";
		String Year = new java.text.SimpleDateFormat("yyyy").format(new java.util.Date());
		String Month = new java.text.SimpleDateFormat("MM").format(new java.util.Date());
		
		String path = Path+whatKind+"/"+Year+"/"+Month+"/";
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        List<MultipartFile> mf = multipartHttpServletRequest.getFiles(paramName);
         
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        String type = null;
         
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> listMap = null; 
                 
        File file = new File(path);
        if(file.exists() == false){
            file.mkdirs();
        }
        
        if (! (mf.size() == 1 && mf.get(0).getOriginalFilename().equals("")) ) {
	        for(int i=0; i<mf.size();i++) {	        	
	            originalFileName = mf.get(i).getOriginalFilename();
	            
	         // 해당 파라메터필드에는 파일이 비어있음이 아닐경우 처리
	            if(! (originalFileName==null || originalFileName.equals(""))) {
	            	type=originalFileName.substring(originalFileName.length()-3);
	            	if(getFileType(type)){
	            		//10M 제한
	            		if(mf.get(i).getSize() < 10485760){
				            originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				            storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
				            String savePath = path + storedFileName; // 저장 될 파일 경로
				            mf.get(i).transferTo(new File(savePath)); // 파일 저장
				            
				            listMap = new HashMap<String,Object>();
				            listMap.put("ORIGINAL_FILE_NAME", originalFileName);
				            listMap.put("STORED_FILE_NAME", storedFileName);
				            listMap.put("FILE_SIZE", mf.get(i).getSize());
				            listMap.put("FILE_TYPE", type);
				            //listMap.put("FILE_PATH", whatKind+"/"+Year+"/"+Month+"/");
				            listMap.put("FILE_PATH", Year+"/"+Month+"/");
				            //listMap.put("INSERT_YN", "Y");
				            list.add(listMap);
				            
				            if(isPhoto){
				            	createImgEachother(savePath, storedFileName, path, type);
				            }
	            		}else{
	            			listMap = new HashMap<String,Object>();
		            		listMap.put("ORIGINAL_FILE_NAME", originalFileName);
		            		//listMap.put("INSERT_YN", "S_N");
		            		list.add(listMap);
	            		}
	            	}else{
	            		listMap = new HashMap<String,Object>();
	            		listMap.put("ORIGINAL_FILE_NAME", originalFileName);
	            		//listMap.put("INSERT_YN", "E_N");
	            		list.add(listMap);
	            	}
	            }
	        }
        }
        return list;
    }
	//썸네일용 이미지 저장
	public void createImgEachother(String fpath, String fname, String path, String type) throws IOException{
	    ImageIcon introIc = new ImageIcon(fpath);
	    Image sIcon = introIc.getImage();
	    Image lIcon = introIc.getImage();
	     
	    File file = new File(path+"thumbnail/");
	    if(file.exists() == false){
	        file.mkdirs();
	    }
	    
	    double scaleS = 150;
	    double scaleL = 600;
	    double lWidth = 0;
	    double lHeight = 0;
	    String newPath = path+"thumbnail/";
	    
	    lWidth = introIc.getIconWidth()/scaleL;
	    lHeight = introIc.getIconHeight()/lWidth;
	    
	    int imageType = BufferedImage.TYPE_INT_RGB;
	    BufferedImage scaledS = new BufferedImage((int)scaleS, (int)scaleS, imageType);
	    BufferedImage scaledL = new BufferedImage((int)scaleL, (int)Math.round(lHeight), imageType);
	     
	    // 작은 이미지 
	    Graphics2D g2 = scaledS.createGraphics();
	    g2.drawImage(sIcon, 0, 0, (int)scaleS, (int)scaleS, null); 
	    g2.dispose();
	    newPath += "S"+fname;
	    ImageIO.write(scaledS, type, new File(newPath));
	    
	    // 큰 이미지
	    newPath = path+"thumbnail/";
	    g2 = scaledL.createGraphics();
	    g2.drawImage(lIcon, 0, 0, (int)scaleL, (int)Math.round(lHeight), null); 
	    g2.dispose();
	    newPath += "L"+fname;
	    ImageIO.write(scaledL, type, new File(newPath));
	}
	
  /*  public boolean DownloadFile(String whatKind, Object file, HttpServletResponse response, HttpServletRequest request) {
    	
    	FileDefaultVO fileInfo = (FileDefaultVO) file;
    	String Path = configUpload.get("upload"+WordUtils.capitalize(whatKind));
    	System.out.println("Path:"+Path);
    	
    	String ori_name = fileInfo.getOriginal_file_name();
    	String sto_name_arr[] = fileInfo.getStored_file_name().split("\\.");
    	String sto_name = fileInfo.getStored_file_name();
    	if(ori_name.indexOf("."+sto_name_arr[sto_name_arr.length-1]) < 0 ){
    		ori_name = ori_name+"."+sto_name_arr[sto_name_arr.length-1];
    	}
    	System.out.println("ori:"+ori_name);
    	System.out.println("sto:"+sto_name);
    	File downloadFile = new File(Path+whatKind+"/"+fileInfo.getFile_path()+"/"+sto_name);
    	FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(downloadFile);
			
			// get MIME type of the file
	        String mimeType = request.getSession().getServletContext().getMimeType(downloadFile.getPath());
	        if (mimeType == null) {
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
	        
	        // set content attributes for the response
	        response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String fileName = ori_name;
			String header = getBrowser(request);
			if (header.contains("MSIE")) {
	        	String docName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	            response.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
		    } else if (header.contains("Firefox")) {
		        String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		        response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
		    } else if (header.contains("Opera")) {
		        String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		        response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
		    } else if (header.contains("Chrome")) {
		        String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		        response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
		    }
			// get output stream of the response
	        OutputStream outStream = response.getOutputStream();
	 
	        byte[] buffer = new byte[(int)downloadFile.length()];
	        int bytesRead = -1;
	 
	        // write bytes read from the input stream into the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	 
	        inputStream.close();
	        outStream.close();
	        return true;
		} catch (FileNotFoundException e){
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }*/
    
 // 브라우저 구분 메소드
    private String getBrowser(HttpServletRequest request) {
        String header =request.getHeader("User-Agent");
        
        if (header.contains("MSIE") || header.contains("Trident/")) {
        	// Trident/7.0	IE11 Preview
        	// Trident/6.0	Internet Explorer 10
        	// Trident/5.0	Internet Explorer 9
        	// Trident/4.0	Internet Explorer 8
        	return "MSIE";
        } else if(header.contains("Chrome")) {
            return "Chrome";
        } else if(header.contains("Opera")) {
            return "Opera";
        } else 
        	return "Firefox";
    }
    private boolean getFileType(String type){
    	boolean result = false;
    	String type_auth = "hwp,doc,txt,xlsx,xls,pptx,ppt,jpg,jpeg,png,gif,bmp,tiff,pdf,zip";
    	type=type.toLowerCase();
    	
    	if(type_auth.indexOf(type) > -1){
    		result = true;
    	}else{
    		result = false;
    	}
    	
    	return result;
    }
}
