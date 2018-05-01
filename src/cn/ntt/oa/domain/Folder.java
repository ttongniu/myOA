package cn.ntt.oa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Folder implements Serializable {

	private static final long serialVersionUID = 6226884067836300725L;
	public static final int PRIVATE = 0;// 私有
	public static final int SHARE = 1;// 共享
	private User user;// 创建者
	private Set<File> files;// 文件
	private Long id;// UUID
	private String name;// 文件夹名称
	private String description;// 类型
	private int type;// 类型
	private Date createTime;// 创建时间

	public Folder() {
		super();
	}

	public Folder(User user, Set<File> files, Long id, String name,
			String description, int type, Date createTime) {
		super();
		this.user = user;
		this.files = files;
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.createTime = createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<File> getFiles() {
		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
