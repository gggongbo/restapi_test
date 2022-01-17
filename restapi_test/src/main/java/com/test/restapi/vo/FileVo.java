package com.test.restapi.vo;

import java.time.LocalDateTime;

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
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "file")
public class FileVo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long file_no;
	
	@Column(length = 1000)
	private String file_name;
	
	@Column(columnDefinition = "TEXT")
	private String file_path;
	
	@CreationTimestamp
    @Column(nullable = false, updatable = false)
	private LocalDateTime insert_date;
	
	@Column(length = 50)
	private String insert_user;
	
	@UpdateTimestamp
    @Column(nullable = false)
	private LocalDateTime update_date;
	
	@Column(length = 50)
	private String update_user;
	
	@ManyToOne
	@JoinColumn(name = "board_no", referencedColumnName = "board_no")
    private BoardVo boardVo;

	public void setInsert_user(String insert_user) {
		this.insert_user = insert_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}
	
	public void setBoardVo(BoardVo boardVo) { 
		//if (this.boardVo != null) { 
		//   this.boardVo.getFileVos().remove(this); 
		// } 
	    this.boardVo = boardVo;            
	    //boardVo.getFileVos().add(this);
	} 
	
	@Builder
	public FileVo(String file_path, String file_name) {
		this.file_name = file_name;
		this.file_path = file_path;
			
	}
	
}
