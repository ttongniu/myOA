package cn.ntt.oa.domain;

import java.io.Serializable;

/**
 * 通讯录
 * 
 * @author NTT
 * 
 */
public class AddressBook implements Serializable {

	private static final long serialVersionUID = 2237351275609973410L;
	private Long id;
	private String name;// 姓名
	private String gender;// 性别
	private String phoneNumber; // 电话号码
	private String email; // 电子邮件
	private String description; // 备注说明
	private String address;// 家庭住址
	private String QQ;// QQ
	private Department department;// 部门

	public AddressBook() {
		super();
	}

	public AddressBook(Long id, String name, String gender, String phoneNumber,
			String email, String description, String address, String qQ,
			Department department) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.description = description;
		this.address = address;
		QQ = qQ;
		this.department = department;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
