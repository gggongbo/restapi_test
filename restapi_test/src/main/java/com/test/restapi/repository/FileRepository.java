package com.test.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.restapi.vo.FileVo;
import com.test.restapi.vo.BoardVo;
import java.util.List;
import java.lang.String;

public interface FileRepository extends JpaRepository<FileVo, Long>{
	
	List<FileVo> findByBoardVo(BoardVo boardvo);
	
	List<FileVo> findByPathAndNameAndDelflagAndNoNot(String path, String name, int delflag, Long no);
	
	List<FileVo> findByPathAndNameAndDelflagAndBoardVo(String path, String name, int delflag, BoardVo boardVo);

}
