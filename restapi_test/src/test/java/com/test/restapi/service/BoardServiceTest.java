package com.test.restapi.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//BoardService 메소드 통합 테스트 (게시글 등록, 수정, 조회, 삭제 기능 통합 테스트)
public class BoardServiceTest {
	
	@Autowired
	BoardService boardService;
	
	@Test
	private void insertTest() throws Exception {
		List<MultipartFile> multipartFiles = null;
		String boardVoStr = "{\"subject\":\"test\"}";
		boardService.insert(multipartFiles, boardVoStr);
	}
	
	@Test
	private void updateTest() throws Exception {
		List<MultipartFile> multipartFiles = null;
		String boardVoStr = "{\"no\":5,\"subject\":\"test\"}";
		boardService.update(multipartFiles, boardVoStr);
	}

	@Test
	private void findTest() throws Exception {
		String boardNo = "5";
		boardService.find(boardNo);
	}
	
	@Test
	private void findAllTest() throws Exception {
		boardService.findAll();
	}
	
	@Test
	private void deleteTest() throws Exception {
		String boardVoStr = "{\"no\":5}";
		boardService.delete(boardVoStr);
	}

}
