package com.test.restapi.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.test.restapi.service.BoardService;
import com.test.restapi.service.FileService;
import com.test.restapi.vo.BoardVo;

@RestController
@RequestMapping("/board")
public class BoardController {

	// �α�
	// private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// return type ����ȭ �ϱ�

	@Autowired
	BoardService boardService;

	@Autowired
	FileService fileService;

	// ��ȸ
	@GetMapping(value = "/notice/get")
	public ResponseEntity<Object> getNotice(@RequestParam (value = "no") String boardNo) {
		BoardVo rtnBoardVo = null;
		try {
			rtnBoardVo = boardService.find(boardNo);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(rtnBoardVo, HttpStatus.OK);
	}

	// ��ü��ȸ
	@GetMapping(value = "/notice/getList")
	public ResponseEntity<Object> getNoticeList() {
		List<BoardVo> rtnBoardList = null;
		try {
			rtnBoardList = boardService.findAll();
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(rtnBoardList, HttpStatus.OK);
	}

	// ���� �� ���Ͼ��ε�
	@PostMapping(value = "/notice/insert")
	public ResponseEntity<Object> insertNotice(@RequestParam(value="file", required = false) List<MultipartFile> multipartFiles, @RequestParam(value="board") String boardVoStr) {
		BoardVo rtnBoardVo = null;
		try {
			rtnBoardVo = boardService.insert(multipartFiles, boardVoStr);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(rtnBoardVo, HttpStatus.OK);
	}
	
	// ���� �� ���� ���ε�
	@PutMapping(value = "/notice/update")
	public ResponseEntity<Object> updateNotice(@RequestParam(value = "file", required=false) List<MultipartFile> multipartFiles, @RequestParam(value="board") String boardVoStr) {
		BoardVo rtnBoardVo = null;
		try {
			rtnBoardVo = boardService.update(multipartFiles, boardVoStr);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(rtnBoardVo, HttpStatus.OK);
	}

	// ����
	@DeleteMapping(value = "/notice/delete")
	public ResponseEntity<Object> deleteNotice(@RequestParam(value="board") String boardVoStr) {
		try {
			boardService.delete(boardVoStr);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}

	@PostMapping(value = "/file/upload")
	public ResponseEntity<Object> uploadFile(@RequestParam(value = "file") List<MultipartFile> multipartFiles) {
		// filevo ���̺� ������ �߰� + insert update�� ���� �߰�
		try {
			fileService.uploadMultiFiles(multipartFiles);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}

	@GetMapping(value = "/file/download")
	public ResponseEntity<Object> fileDownload(@RequestParam(value = "filename") String fileName) {
		// file no���� file name �о ó���ϰ� ���߿� ����
		Map<String, Object> fileMap = null;
		String contentType = null;
		Resource file = null;
		try {
			fileMap = fileService.downloadFile(fileName);
			contentType = (String) fileMap.get("mimetype");
			file = (Resource) fileMap.get("resource");
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.toString(), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);

	}

}
