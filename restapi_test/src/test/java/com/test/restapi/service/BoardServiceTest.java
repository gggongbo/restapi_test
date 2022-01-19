package com.test.restapi.service;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
//BoardService �޼ҵ� ����/���� �׽�Ʈ (�Խñ� ���, ����, ��ȸ, ���� ��� ����/���� �׽�Ʈ)
public class BoardServiceTest {
	
	@Autowired
	BoardService boardService;
	
	@Transactional
	@Test
	public void insertTest() throws Exception {
		List<MultipartFile> multipartFiles = null;
		String boardVoStr = "{\"subject\":\"test\"}";
		boardService.insert(multipartFiles, boardVoStr);
	}
	
	@Transactional
	@Test
	public void updateTest() throws Exception {
		List<MultipartFile> multipartFiles = null;
		String boardVoStr = "{\"no\":6,\"subject\":\"test\"}";
		boardService.update(multipartFiles, boardVoStr);
	}

	@Transactional
	@Test
	public void findTest() throws Exception {
		String boardNo = "5";
		boardService.find(boardNo);
	}
	
	@Transactional
	@Test
	public void findAllTest() throws Exception {
		boardService.findAll();
	}
	
	@Transactional
	@Test
	public void deleteTest() throws Exception {
		String boardVoStr = "{\"no\":1}";
		boardService.delete(boardVoStr);
	}

}
