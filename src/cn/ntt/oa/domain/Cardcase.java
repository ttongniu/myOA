package cn.ntt.oa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 名片夹
 * 
 * @author NTT
 * 
 */

public class Cardcase implements Serializable {

	private static final long serialVersionUID = 2836415356849866575L;

	/** 私有 */
	public static final int TYPE_PRIVATE = 0;

	/** 共享 */
	public static final int TYPE_SHARE = 1;

	private Long id;
	private String name; // 名片夹名称
	private String description;// 名片夹说明
	private int type;// 类型
	private Date createTime;// 创建时间
	private User createBy;// 创建者
	private Set<Card> cards; // 名片

	public Cardcase() {
		super();
	}

	public Cardcase(Long id, String name, String description, int type,
			Date createTime, User createBy, Set<Card> cards) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.createTime = createTime;
		this.createBy = createBy;
		this.cards = cards;
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

	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
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
