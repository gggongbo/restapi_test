package com.test.restapi.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.test.restapi.vo.BoardVo;
import com.test.restapi.vo.FileVo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//FileService 메소드 통합 테스트 (파일 업다운로드 기능 통합 테스트)
public class FileServiceTest {

	@Autowired
	FileService fileService;

	@Transactional
	@Test
	private void uploadMultiFilesTest() throws Exception {
		List<MultipartFile> multipartFiles = null;
		Long boardNo = 1L;
		fileService.uploadMultiFiles(multipartFiles, boardNo);
		fileService.uploadMultiFiles(multipartFiles);
	}

	@Transactional
	@Test
	private void uploadFileTest() throws Exception {
		MultipartFile multiFile = null;
		Long boardNo = 1L;
		fileService.uploadFile(multiFile, boardNo);
		fileService.uploadFile(multiFile);
	}

	@Transactional
	@Test
	private void downloadFileTest() throws Exception {
		String filename = "";
		fileService.downloadFile(filename);
	}
	
	@Transactional
	@Test
	private void deleteFileTest() throws Exception {
		FileVo fileVo = FileVo.builder().build();
		fileService.deleteFile(fileVo);
	}
	
	@Test
	private void deleteFilesTest() throws Exception {
		BoardVo boardVo = BoardVo.builder().build();
		fileService.deleteFiles(boardVo);
	}

}
