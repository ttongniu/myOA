package cn.ntt.oa.domain;

/**
 * 回复
 * 
 * @author NTT
 * 
 */
public class Reply extends Article {

	private static final long serialVersionUID = -2337703089203005680L;
	private Topic topic; // 所属的主题

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
