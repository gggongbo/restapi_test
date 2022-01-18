package com.test.restapi.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import com.test.restapi.vo.BoardVo;
import com.test.restapi.vo.FileVo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//FileService 메소드 통합 테스트 (파일 업다운로드 기능 통합 테스트)
public class FileServiceTest {

	@Autowired
	FileService fileService;

	@Test
	private void uploadMultiFilesTest() throws Exception {
		List<MultipartFile> multipartFiles = null;
		Long boardNo = 1L;
		fileService.uploadMultiFiles(multipartFiles, boardNo);
		fileService.uploadMultiFiles(multipartFiles);
	}

	@Test
	private void uploadFileTest() throws Exception {
		MultipartFile multiFile = null;
		Long boardNo = 1L;
		fileService.uploadFile(multiFile, boardNo);
		fileService.uploadFile(multiFile);
	}

	@Test
	private void downloadFileTest() throws Exception {
		String filename = "";
		fileService.downloadFile(filename);
	}
	
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
