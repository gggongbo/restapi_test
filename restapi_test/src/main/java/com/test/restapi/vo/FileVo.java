package com.test.restapi.vo;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "file")
public class FileVo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "file_no")
	private Long no;

	@Column(name = "file_name", length = 1000)
	private String name;

	@Column(name = "file_path", columnDefinition = "TEXT")
	private String path;

	@CreationTimestamp
	@Column(name = "insert_date", nullable = false, updatable = false)
	private LocalDateTime insertdate;

	@Column(name = "insert_user", length = 50)
	private String insertuser;

	@UpdateTimestamp
	@Column(name = "update_date", nullable = false)
	private LocalDateTime updatedate;

	@Column(name = "update_user", length = 50)
	private String updateuser;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "board_no")
	private BoardVo boardVo;

	@Builder
	public FileVo(Long no, String path, String name) {
		this.no = no;
		this.path = path;
		this.name = name;

	}

	public void setInsertuser(String insertuser) {
		this.insertuser = insertuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public void setBoardVo(BoardVo boardVo) {
		this.boardVo = boardVo;
	}

}
