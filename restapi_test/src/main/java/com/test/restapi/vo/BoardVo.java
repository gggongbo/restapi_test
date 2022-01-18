package com.test.restapi.vo;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "board")
public class BoardVo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_no")
	private Long no;

	@Column(name = "board_type", length = 3)
	private String type;

	@Column(name = "board_subject", length = 1000)
	private String subject;

	@Column(name = "board_content", columnDefinition = "TEXT")
	private String content;

	@Column(name = "board_views")
	private Long views;

	@Column(name = "board_fr_date", length = 8)
	private String frdate;

	@Column(name = "board_ed_date", length = 8)
	private String eddate;

	@Column(name = "board_del_flag", length = 1)
	private int delflag;

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

	@PrePersist
	public void prePersist() {
		this.views = this.views == null ? 0 : this.views;
	}

	@Builder
	public BoardVo(Long no, String subject, String content, Long views, String frdate,
			String eddate, int delflag, String insertuser, String updateuser) {
		this.no = no;
		this.subject = subject;
		this.content = content;
		this.views = views;
		this.frdate = frdate;
		this.eddate = eddate;
		this.delflag = delflag;
		this.insertuser = insertuser;
		this.updateuser = updateuser;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setViews(Long views) {
		this.views = views;
	}

	public void setFrdate(String frdate) {
		this.frdate = frdate;
	}

	public void setEddate(String eddate) {
		this.eddate = eddate;
	}

	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}

	public void setInsertuser(String insertuser) {
		this.insertuser = insertuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

}
