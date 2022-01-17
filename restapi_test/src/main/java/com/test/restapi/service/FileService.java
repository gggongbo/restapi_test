package com.test.restapi.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.restapi.repository.FileRepository;
import com.test.restapi.vo.BoardVo;
import com.test.restapi.vo.FileVo;

@Service
public class FileService {

	@Value("${file.upload.path}")
	private String fileUploadPath;

	@Autowired
	private FileRepository fileRepository;

	// exception 로직 나중에 throw exception 에서 aop에서 처리하게 변경
	@PostConstruct
	public void init() throws IOException {
		Files.createDirectories(Paths.get(this.fileUploadPath).toAbsolutePath().normalize());
	}

	public void uploadMultiFiles(List<MultipartFile> multipartFiles, Long boardNo) throws Exception {
		if (multipartFiles == null) {
			throw new Exception("null:file");
		}
		for (MultipartFile multipartFile : multipartFiles) {
			uploadFile(multipartFile, boardNo);
		}
	}

	public void uploadMultiFiles(List<MultipartFile> multipartFiles) throws Exception {
		if (multipartFiles == null) {
			throw new Exception("null:file");
		}
	}

	public void uploadFile(MultipartFile multipartFile, Long boardNo) throws IOException {
		Path filePath = Paths.get(this.fileUploadPath).toAbsolutePath().normalize();
		String fileName = multipartFile.getOriginalFilename();
		FileVo fileVo = FileVo.builder().file_path(filePath.toString())
				  		.file_name(fileName).build();
		fileVo.setBoardVo(BoardVo.builder().board_no(boardNo).build());

		if (!Files.exists(filePath)) {
			init();
		}

		Files.deleteIfExists(filePath.resolve(fileName));
		Files.copy(multipartFile.getInputStream(), filePath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
		fileRepository.save(fileVo);
	}

	public void uploadFile(MultipartFile multipartFile) throws IOException {
		Path filePath = Paths.get(this.fileUploadPath).toAbsolutePath().normalize();
		String fileName = multipartFile.getOriginalFilename();

		if (!Files.exists(filePath)) {
			init();
		}

		Files.deleteIfExists(filePath.resolve(fileName));
		Files.copy(multipartFile.getInputStream(), filePath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
	}

	public Map<String, Object> downloadFile(String fileName) throws IOException {
		Map<String, Object> fileMap = new HashMap<String, Object>();
		Path filePath = Paths.get(this.fileUploadPath).resolve(fileName).normalize();
		Resource resource = new UrlResource(filePath.toUri());
		String mimeType = "";
		if (resource.exists() || resource.isReadable()) {
			mimeType = Files.probeContentType(filePath);
			fileMap.put("mimetype", mimeType);
			fileMap.put("resource", resource);
			return fileMap;
		} else {
			throw new FileNotFoundException();
		}
	}

}
