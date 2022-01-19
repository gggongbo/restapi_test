package com.test.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.test.restapi.vo.BoardVo;

public interface BoardRepository extends JpaRepository<BoardVo, Long>{
		
}
