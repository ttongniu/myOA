package cn.ntt.oa.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 名片
 * 
 * @author NTT
 * 
 */
public class Card implements Serializable {

	private static final long serialVersionUID = 4138528537124795301L;
	private Long id;
	private String name;// 姓名
	private String nickName;// 昵称
	private String englishName;// 英文名
	private String company;// 公司
	private String vocation;// 职业
	private String email;// 邮箱
	private String telBusiness;// 公司电话
	private String telHome;// 个人电话
	private String departmentName; // 部门名称
	private String address;// 地址
	private String zipCode; // 邮编
	private String QQ;// QQ
	private String description;// 备注说明
	private Date createDate;// 创建时间
	private Cardcase cardcase;// 名片夹

	public Card() {
		super();
	}

	public Card(Long id, String name, String nickName, String englishName,
			String company, String vocation, String email, String telBusiness,
			String telHome, String departmentName, String address,
			String zipCode, String qQ, String description, Date createDate,
			Cardcase cardcase) {
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.englishName = englishName;
		this.company = company;
		this.vocation = vocation;
		this.email = email;
		this.telBusiness = telBusiness;
		this.telHome = telHome;
		this.departmentName = departmentName;
		this.address = address;
		this.zipCode = zipCode;
		QQ = qQ;
		this.description = description;

		this.createDate = createDate;
		this.cardcase = cardcase;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getVocation() {
		return vocation;
	}

	public void setVocation(String vocation) {
		this.vocation = vocation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelBusiness() {
		return telBusiness;
	}

	public void setTelBusiness(String telBusiness) {
		this.telBusiness = telBusiness;
	}

	public String getTelHome() {
		return telHome;
	}

	public void setTelHome(String telHome) {
		this.telHome = telHome;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Cardcase getCardcase() {
		return cardcase;
	}

	public void setCardcase(Cardcase cardcase) {
		this.cardcase = cardcase;
	}

}
