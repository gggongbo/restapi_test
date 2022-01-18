package com.test.restapi.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
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

	public void uploadFile(MultipartFile multipartFile, Long boardNo) throws Exception {
		Path filePath = Paths.get(this.fileUploadPath).toAbsolutePath().normalize();
		String fileName = multipartFile.getOriginalFilename();
		FileVo fileVo = FileVo.builder().path(filePath.toString())
				  		.name(fileName).build();
		BoardVo boardVo = BoardVo.builder().no(boardNo).build();
		fileVo.setBoardVo(boardVo);
		int delFlag = 0;

		if (!Files.exists(filePath)) {
			init();
		}

		Files.copy(multipartFile.getInputStream(), filePath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

		//같은 게시글에 이미 같은 파일이 첨부된 경우에는 file 테이블에 데이터 입력하지 않음
		List<FileVo> otherFileVos = fileRepository.findByPathAndNameAndDelflagAndBoardVo(filePath.toString(), fileName, delFlag, boardVo);
		if(otherFileVos==null || otherFileVos.isEmpty()) {
			fileRepository.save(fileVo);
		}
	}

	public void uploadFile(MultipartFile multipartFile) throws Exception {
		Path filePath = Paths.get(this.fileUploadPath).toAbsolutePath().normalize();
		String fileName = multipartFile.getOriginalFilename();
		FileVo fileVo = FileVo.builder().path(filePath.toString())
		  		.name(fileName).build();

		if (!Files.exists(filePath)) {
			init();
		}

		Files.copy(multipartFile.getInputStream(), filePath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
		fileRepository.save(fileVo);
	}

	public Map<String, Object> downloadFile(String fileName) throws Exception {
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
	
	@Transactional
	public void deleteFile(FileVo fileVo) throws Exception {
		String strFilePath = fileVo.getPath();
		String fileName = fileVo.getName();
		Long fileNo = fileVo.getNo();
		Path filePath = Paths.get(strFilePath).toAbsolutePath().normalize();
		int delFlag = 0; 
		
		//다른 게시글에서 파일 이용하지 않는 경우에는 파일 삭제 진행
		List<FileVo> otherFileVos = fileRepository.findByPathAndNameAndDelflagAndNoNot(strFilePath, fileName, delFlag, fileNo);
		if(otherFileVos==null || otherFileVos.isEmpty()) {
			Files.deleteIfExists(filePath.resolve(fileName));
		}
		
		delFlag = 1; //삭제되는 경우 file_del_flag 값 1로 변경
		fileVo.setDelflag(delFlag);
	}

	public void deleteFiles(BoardVo boardVo) throws Exception {
		List<FileVo> fileVos = fileRepository.findByBoardVo(boardVo);
		
		if(fileVos !=null) {
			for (FileVo fileVo : fileVos) {
				deleteFile(fileVo);
			}
		}
		
	}

}
