package com.test.restapi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.test.restapi.vo.BoardVo;
import com.test.restapi.vo.FileVo;

@SpringBootTest
//FileService 메소드 단위/통합 테스트 (파일 업다운로드 기능 단위/통합 테스트)
public class FileServiceTest {

	@Autowired
	FileService fileService;
	
	private MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws IOException {
	      FileInputStream fileInputStream = new FileInputStream(new File(path));
	      return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
	}

	@Transactional
	@Test
	public void uploadMultiFilesTest() throws Exception {
		String fileName = "test";
	    String contentType = "jpg";
	    String filePath = "src/test/resources/restapi_test_file/test.jpg";
	    MockMultipartFile mockMultipartFile = getMockMultipartFile(fileName, contentType, filePath);
		List<MultipartFile> multipartFiles = new ArrayList<MultipartFile>();
		multipartFiles.add(mockMultipartFile);
		
		fileService.uploadMultiFiles(multipartFiles);
	}

	@Transactional
	@Test
	public void uploadFileTest() throws Exception {
		String fileName = "test";
	    String contentType = "jpg";
	    String filePath = "src/test/resources/restapi_test_file/test.jpg";
		MultipartFile multiFile = getMockMultipartFile(fileName, contentType, filePath);
		fileService.uploadFile(multiFile);
	}

	@Transactional
	@Test
	public void downloadFileTest() throws Exception {
		//파일 업로드 경로에 있는 파일의 다운로드 테스트
		String filename = "test";
		fileService.downloadFile(filename);
	}
	
	@Transactional
	@Test
	public void deleteFileTest() throws Exception {
		String fileName = "test";
	    String filePath = "src/test/resources/restapi_test_file/test.jpg";
		FileVo fileVo = FileVo.builder().no(1L).path(filePath).name(fileName).build();
		fileVo.setBoardVo(BoardVo.builder().build());
		fileService.deleteFile(fileVo);
	}
	
	@Transactional
	@Test
	public void deleteFilesTest() throws Exception {
		BoardVo boardVo = BoardVo.builder().no(1L).build();
		fileService.deleteFiles(boardVo);
	}

}
