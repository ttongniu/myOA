package cn.ntt.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ntt.oa.base.DaoSupportImpl;
import cn.ntt.oa.domain.Forum;
import cn.ntt.oa.domain.PageBean;
import cn.ntt.oa.domain.Reply;
import cn.ntt.oa.domain.Topic;
import cn.ntt.oa.service.ReplyService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements
		ReplyService {

	public List<Reply> findByTopic(Topic topic) {
		return getSession().createQuery(//
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
				.setParameter(0, topic)//
				.list();
	}

	@Override
	public void save(Reply reply) {
		// 1，保存
		getSession().save(reply);

		// 2，维护相关的信息
		Topic topic = reply.getTopic();
		Forum forum = topic.getForum();

		forum.setArticleCount(forum.getArticleCount() + 1); // 文章数量（主题数+回复数）
		topic.setReplyCount(topic.getReplyCount() + 1); // 回复数量
		topic.setLastReply(reply); // 最后发表的回复
		topic.setLastUpdateTime(reply.getPostTime()); // 最后更新时间（主题的发表时间或最后回复的时间）

		getSession().update(topic);
		getSession().update(forum);
	}

	/*
	 * @Override public void delete(Long id) { System.out.println("__-"+id); //
	 * 1，维护相关的信息 Reply reply=this.getById(id); Topic topic = reply.getTopic();
	 * Forum forum = topic.getForum();
	 * forum.setArticleCount(forum.getArticleCount() - 1); // 文章数量（主题数+回复数）
	 * topic.setReplyCount(topic.getReplyCount() - 1); // 回复数量
	 * getSession().update(topic); getSession().update(forum);
	 * System.out.println("__-"+id+"*******"); // 2，删除 getSession().delete(id);
	 * }
	 */
	@Override
	@Deprecated
	public PageBean getPageBeanByTopic(int pageNum, int pageSize, Topic topic) {
		// TODO Auto-generated method stub
		// 查询本页的数据列表
		List<Reply> list = getSession().createQuery(//
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
				.setParameter(0, topic)//
				.setFirstResult((pageNum - 1) * pageSize)//
				.setMaxResults(pageSize)//
				.list();

		// 查询总记录数量
		Long count = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Reply r WHERE r.topic=?")//
				.setParameter(0, topic)//
				.uniqueResult();

		return new PageBean(pageNum, pageSize, count.intValue(), list);

	}
}
