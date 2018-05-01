package cn.ntt.oa.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 日程表
 * 
 * @author NTT
 * 
 */
public class Schedule implements Serializable {

	private static final long serialVersionUID = -2445060918328347724L;
	private Long id;
	private String title;
	private String description;
	private Date createTime;
	private User createBy;

	public Schedule(Long id, String title, String description, Date createTime,
			User createBy) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.createTime = createTime;
		this.createBy = createBy;
	}

	public Schedule() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

}
