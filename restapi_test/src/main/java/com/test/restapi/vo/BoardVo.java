package com.test.restapi.vo;

import lombok.*;
import lombok.Builder.Default;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Entity
@Table(name = "board")
public class BoardVo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long board_no;
	
	@Column(length=3)
	private String board_type;
	
	@Column(length = 1000)
	private String board_subject;
	
	@Column(columnDefinition = "TEXT")
	private String board_content;
	
	@Column
	private Long board_views;
	
	@Column(length = 8)
	private String board_fr_date;
	
    @Column(length = 8)
	private String board_ed_date;
	
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
	
	//@OneToMany(mappedBy = "file")
	//private List<FileVo> fileVos = new ArrayList<FileVo>();
	
    @PrePersist
    public void prePersist() {
        this.board_views = this.board_views == null ? 0 : this.board_views;
    }
	
    public void setBoard_views(Long board_views) {
		this.board_views = board_views;
	}

	public void setInsert_user(String insert_user) {
		this.insert_user = insert_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}

	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public void setBoard_fr_date(String board_fr_date) {
		this.board_fr_date = board_fr_date;
	}

	public void setBoard_ed_date(String board_ed_date) {
		this.board_ed_date = board_ed_date;
	}
	
	
	@Builder
	public BoardVo(Long board_no, String board_subject, String board_content, Long board_views, String board_fr_date, String board_ed_date, String insert_user, String update_user) {
		this.board_no = board_no;
		this.board_subject = board_subject;
		this.board_content = board_content;
		this.board_views = board_views;
		this.board_fr_date = board_fr_date;
		this.board_ed_date = board_ed_date;
		this.insert_user = insert_user;
		this.update_user = update_user;
			
	}

}
