package cn.ntt.oa.service;

import java.util.List;

import cn.ntt.oa.base.DaoSupport;
import cn.ntt.oa.domain.Forum;
import cn.ntt.oa.domain.Topic;

public interface TopicService extends DaoSupport<Topic> {

	/**
	 * //根据版块查找主题
	 * 
	 * @param forum
	 * @return
	 */
	List<Topic> findByForum(Forum forum);

	/**
	 * //主题移动版块
	 * 
	 */
	void move(Topic topic);

}
