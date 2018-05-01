package cn.ntt.oa.domain;

import java.io.Serializable;
import java.util.Date;

public class File implements Serializable {

	private static final long serialVersionUID = -149475018079491671L;
	private Folder folder;// 文件夹
	private Long id;// UUID
	private String name;// 文件名
	private Long size;// 文件大小
	private String action;// 存放位置
	private int downLoadNum;// 下载次数
	private Date createTime;// 上传时间
	private String format;// 文件格式
	private String description;// 文件描述

	public File() {
		super();
	}

	public File(Folder folder, Long id, String name, Long size, String action,
			int downLoadNum, Date createTime, String format, String description) {
		super();
		this.folder = folder;
		this.id = id;
		this.name = name;
		this.size = size;
		this.action = action;
		this.downLoadNum = downLoadNum;
		this.createTime = createTime;
		this.format = format;
		this.description = description;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getDownLoadNum() {
		return downLoadNum;
	}

	public void setDownLoadNum(int downLoadNum) {
		this.downLoadNum = downLoadNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}