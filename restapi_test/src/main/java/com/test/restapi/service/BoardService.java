package com.test.restapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.restapi.repository.BoardRepository;
import com.test.restapi.repository.FileRepository;
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
			getBoardVo.setBoard_views(getBoardVo.getBoard_views() + 1);
		}

		return getBoardVo;
	}

	public List<BoardVo> findAll() throws Exception {
		List<BoardVo> boardList = new ArrayList<>();
		boardList = boardRepository.findAll();

		if (boardList == null || boardList.isEmpty()) {
			throw new Exception("null:getList");
		}

		return boardList;
	}
	
	public BoardVo insert (BoardVo boardVo) throws Exception {
		BoardVo newBoardVo = boardRepository.save(boardVo);
		
		if(newBoardVo==null) {
			throw new Exception("error:insert");
		}
		
		return newBoardVo;
	}

	public BoardVo insert (List<MultipartFile> multipartFiles, String boardVoStr) throws Exception {
		BoardVo boardVo  = new ObjectMapper().readValue(boardVoStr, BoardVo.class);
		BoardVo newBoardVo = boardRepository.save(boardVo);
		
		if(newBoardVo==null) {
			throw new Exception("error:insert");
		}
		
		if (multipartFiles != null) {
			fileService.uploadMultiFiles(multipartFiles, newBoardVo.getBoard_no());
		}
		
		return newBoardVo;
	}
	
	@Transactional
	public BoardVo update(BoardVo boardVo) throws Exception {
		BoardVo newBoardVo = boardRepository.findById(boardVo.getBoard_no()).orElse(null);

		if (newBoardVo == null) {
			throw new Exception("null:update");
		}

		if (!StringUtils.isEmpty(boardVo.getBoard_subject())) {
			newBoardVo.setBoard_content(boardVo.getBoard_subject());
		}
		if (!StringUtils.isEmpty(boardVo.getBoard_type())) {
			newBoardVo.setBoard_type(boardVo.getBoard_type());
		}
		if (!StringUtils.isEmpty(boardVo.getBoard_content())) {
			newBoardVo.setBoard_content(boardVo.getBoard_content());
		}
		if (!StringUtils.isEmpty(boardVo.getBoard_fr_date())) {
			newBoardVo.setBoard_fr_date(boardVo.getBoard_fr_date());
		}
		if (!StringUtils.isEmpty(boardVo.getBoard_ed_date())) {
			newBoardVo.setBoard_ed_date(boardVo.getBoard_ed_date());
		}
		if (!StringUtils.isEmpty(boardVo.getInsert_user())) {
			newBoardVo.setInsert_user(boardVo.getInsert_user());
		}
		if (!StringUtils.isEmpty(boardVo.getUpdate_user())) {
			newBoardVo.setUpdate_user(boardVo.getUpdate_user());
		}

		return newBoardVo;
	}

	@Transactional
	public BoardVo update(List<MultipartFile> multipartFiles, String boardVoStr) throws Exception {
		BoardVo boardVo  = new ObjectMapper().readValue(boardVoStr, BoardVo.class);
		BoardVo newBoardVo = boardRepository.findById(boardVo.getBoard_no()).orElse(null);

		if (newBoardVo == null) {
			throw new Exception("null:update");
		}

		if (!StringUtils.isEmpty(boardVo.getBoard_subject())) {
			newBoardVo.setBoard_content(boardVo.getBoard_subject());
		}
		if (!StringUtils.isEmpty(boardVo.getBoard_type())) {
			newBoardVo.setBoard_type(boardVo.getBoard_type());
		}
		if (!StringUtils.isEmpty(boardVo.getBoard_content())) {
			newBoardVo.setBoard_content(boardVo.getBoard_content());
		}
		if (!StringUtils.isEmpty(boardVo.getBoard_fr_date())) {
			newBoardVo.setBoard_fr_date(boardVo.getBoard_fr_date());
		}
		if (!StringUtils.isEmpty(boardVo.getBoard_ed_date())) {
			newBoardVo.setBoard_ed_date(boardVo.getBoard_ed_date());
		}
		if (!StringUtils.isEmpty(boardVo.getInsert_user())) {
			newBoardVo.setInsert_user(boardVo.getInsert_user());
		}
		if (!StringUtils.isEmpty(boardVo.getUpdate_user())) {
			newBoardVo.setUpdate_user(boardVo.getUpdate_user());
		}
		
		if (multipartFiles != null) {
			fileService.uploadMultiFiles(multipartFiles, newBoardVo.getBoard_no());
		}

		return newBoardVo;
	}

	public void delete(BoardVo boardVo) throws Exception {
		long beforeCnt = boardRepository.count();
		boardRepository.delete(boardVo);
		long afterCnt = boardRepository.count();

		if (afterCnt >= beforeCnt) {
			throw new Exception("error:delete");
		}
	}

}
