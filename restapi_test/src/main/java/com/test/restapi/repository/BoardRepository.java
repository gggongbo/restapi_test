package com.test.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.test.restapi.vo.BoardVo;
import java.util.List;

public interface BoardRepository extends JpaRepository<BoardVo, Long>{
	List<BoardVo> findByDelflag(int delflag);
		
}
