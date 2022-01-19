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

@SpringBootTest
//BoardService 메소드 단위/통합 테스트 (게시글 등록, 수정, 조회, 삭제 기능 단위/통합 테스트)
public class BoardServiceTest {
	
	@Autowired
	BoardService boardService;
	
	private MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws IOException {
	      FileInputStream fileInputStream = new FileInputStream(new File(path));
	      return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
	}

	@Transactional
	@Test
	public void findAllTest() throws Exception {
		boardService.findAll();
	}

	@Transactional
	@Test
	public void findTest() throws Exception {
		List<BoardVo> boardVos = boardService.findAll();
		String boardNo = boardVos.get(0).getNo().toString();
		boardService.find(boardNo);
	}
	
	@Transactional
	@Test
	public void insertTest() throws Exception {
		String fileName = "test";
	    String contentType = "jpg";
	    String filePath = "src/test/resources/restapi_test_file/test.jpg";
	    MockMultipartFile mockMultipartFile = getMockMultipartFile(fileName, contentType, filePath);
		List<MultipartFile> multipartFiles = new ArrayList<MultipartFile>();
		multipartFiles.add(mockMultipartFile);
		
		String boardVoStr = "{\"subject\":\"test\"}";
		boardService.insert(multipartFiles, boardVoStr);
	}
	
	@Transactional
	@Test
	public void updateTest() throws Exception {
		String fileName = "test";
	    String contentType = "jpg";
	    String filePath = "src/test/resources/restapi_test_file/test.jpg";
	    MockMultipartFile mockMultipartFile = getMockMultipartFile(fileName, contentType, filePath);
		List<MultipartFile> multipartFiles = new ArrayList<MultipartFile>();
		multipartFiles.add(mockMultipartFile);
		
		List<BoardVo> boardVos = boardService.findAll();
		String boardNo = boardVos.get(0).getNo().toString();
		String boardVoStr = "{\"no\":"+boardNo+",\"subject\":\"test\"}";
		boardService.update(multipartFiles, boardVoStr);
	}
	
	@Transactional
	@Test
	public void deleteTest() throws Exception {
		List<BoardVo> boardVos = boardService.findAll();
		String boardNo = boardVos.get(0).getNo().toString();
		boardService.delete("{\"no\":"+boardNo+",\"subject\":\"test\"}");
	}

}
