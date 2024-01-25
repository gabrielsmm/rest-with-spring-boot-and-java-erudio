package br.com.erudio.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.erudio.data.vo.v1.UploadFileReponseVO;
import br.com.erudio.services.FileStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "File Endpoint")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {

	private Logger logger = Logger.getLogger(FileController.class.getName());
	
	@Autowired
	private FileStorageService service;
	
	@PostMapping("/uploadFile")
	public UploadFileReponseVO uploadFile(@RequestParam("file") MultipartFile file) {
		logger.info("Storing file to disk");
		
		var fileName = service.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/file/v1/downloadFile/")
				.path(fileName)
				.toUriString();
		
		return new UploadFileReponseVO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
}
