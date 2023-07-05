package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.main.helper.FileUploadHelper;



@RestController
public class FileController {
	
	@Autowired
	private FileUploadHelper fileuploadhelper;
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFiles(@RequestParam("file") MultipartFile file)
	{
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getSize());
//		System.out.println(file.getContentType());
//		System.out.println(file.getName());
		
		//validation
		if(file.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("request must contain file");
		}
		
		if(!file.getContentType().equals("image/jpeg"))
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only jpeg content are allowed");
		}
		
		//uploading file
		
		boolean f = fileuploadhelper.uploadFile(file);
		if(f)
		{
			//return ResponseEntity.ok("file uploaded successfully");
			return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
				
	}
	

}
