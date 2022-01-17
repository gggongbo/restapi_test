package com.test.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.restapi.vo.FileVo;

public interface FileRepository extends JpaRepository<FileVo, Long>{

}
