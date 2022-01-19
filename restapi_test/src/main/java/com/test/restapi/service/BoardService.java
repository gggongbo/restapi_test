package com.test.restapi.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.restapi.repository.BoardRepository;
import com.test.restapi.service.FileService;
import com.test.restapi.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private FileService fileService;

	@Transactional
	public BoardVo find(String boardNo) throws Exception {
		BoardVo getBoardVo = boardRepository.findById(Long.parseLong(boardNo)).orElse(null);

		if (getBoardVo == null) {
			throw new Exception("null:get");
		} else {
			getBoardVo.setViews(getBoardVo.getViews() + 1);
		}

		return getBoardVo;
	}

	@Transactional(readOnly = true)
	public List<BoardVo> findAll() throws Exception {
		List<BoardVo> boardList = new ArrayList<>();
		boardList = boardRepository.findAll();

		if (boardList == null || boardList.isEmpty()) {
			throw new Exception("null:getList");
		}

		return boardList;
	}

	@Transactional
	public BoardVo insert (List<MultipartFile> multipartFiles, String boardVoStr) throws Exception {
		
		BoardVo boardVo  = new ObjectMapper().readValue(boardVoStr, BoardVo.class);
		BoardVo newBoardVo = boardRepository.save(boardVo);
		
		if(newBoardVo==null) {
			throw new Exception("error:insert");
		}
		
		if (multipartFiles != null) {
			fileService.uploadMultiFiles(multipartFiles, newBoardVo.getNo());
		} 
		
		return newBoardVo;
	}

	@Transactional
	public BoardVo update(List<MultipartFile> multipartFiles, String boardVoStr) throws Exception {
		BoardVo boardVo  = new ObjectMapper().readValue(boardVoStr, BoardVo.class);
		BoardVo newBoardVo = boardRepository.findById(boardVo.getNo()).orElse(null);
		
		if (newBoardVo == null) {
			throw new Exception("null:update");
		} 

		if (!StringUtils.isEmpty(boardVo.getSubject())) {
			newBoardVo.setSubject(boardVo.getSubject());
		}
		if (!StringUtils.isEmpty(boardVo.getType())) {
			newBoardVo.setType(boardVo.getType());
		}
		if (!StringUtils.isEmpty(boardVo.getContent())) {
			newBoardVo.setContent(boardVo.getContent());
		}
		if (!StringUtils.isEmpty(boardVo.getFrdate())) {
			newBoardVo.setFrdate(boardVo.getFrdate());
		}
		if (!StringUtils.isEmpty(boardVo.getEddate())) {
			newBoardVo.setEddate(boardVo.getEddate());
		}
		if (!StringUtils.isEmpty(boardVo.getInsertuser())) {
			newBoardVo.setInsertuser(boardVo.getInsertuser());
		}
		if (!StringUtils.isEmpty(boardVo.getUpdateuser())) {
			newBoardVo.setUpdateuser(boardVo.getUpdateuser());
		}
		
		if (multipartFiles != null) {
			fileService.uploadMultiFiles(multipartFiles, newBoardVo.getNo());
		}

		return newBoardVo;
	}
	
	@Transactional
	public void delete(String boardVoStr) throws Exception {
		BoardVo boardVo  = new ObjectMapper().readValue(boardVoStr, BoardVo.class);
		fileService.deleteFiles(boardVo);

		boardRepository.delete(boardVo);
		
	}

}
