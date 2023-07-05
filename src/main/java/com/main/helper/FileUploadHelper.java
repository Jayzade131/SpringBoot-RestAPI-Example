package com.main.helper;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
	//public final String UPLOAD_DIR="C:\\Users\\HP\\Documents\\workspace-spring-tool-suite-4-4.19.0.RELEASE\\RestApiPro\\src\\main\\resources\\static\\image"; 
	public final String UPLOAD_DIR=new ClassPathResource("static/image/").getFile().getAbsolutePath();
	public FileUploadHelper() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean uploadFile(MultipartFile multipartfile) 
	{
		boolean f=false;
		try {
			
			Files.copy(multipartfile.getInputStream(), Paths.get(UPLOAD_DIR+"//"+multipartfile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			
			f=true;
		} catch (Exception e) {
		e.printStackTrace();
		}
		return f;
		
	}

}
