package com.test.restapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//BoardReopository �޼ҵ� ����/���� �׽�Ʈ
public class BoardRepositoryTest {
	
	@Autowired
	BoardRepository boardRepository;
	
	@Test
	public void test() {
		
		boardRepository.count();
	}

}
