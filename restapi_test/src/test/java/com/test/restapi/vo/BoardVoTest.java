package com.test.restapi.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

//BoardVo get/set 단위 테스트
public class BoardVoTest {
	
	@Test
	public void getNo() {
		final BoardVo boardVo = BoardVo.builder().no(1L).build();
		final Long boadNo = boardVo.getNo();
		assertEquals(1L, boadNo);
	}
	
	@Test
	public void getSubject() {
		final BoardVo boardVo = BoardVo.builder().subject("test").build();
		final String boadSubject = boardVo.getSubject();
		assertEquals("test", boadSubject);
	}

}
