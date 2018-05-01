package cn.ntt.oa.service;

import java.util.List;

import cn.ntt.oa.base.DaoSupport;
import cn.ntt.oa.domain.PageBean;
import cn.ntt.oa.domain.Reply;
import cn.ntt.oa.domain.Topic;

public interface ReplyService extends DaoSupport<Reply> {
	@Deprecated
	List<Reply> findByTopic(Topic topic);

	PageBean getPageBeanByTopic(int pageNum, int pageSize, Topic topic);
	/*
	 * @Override public void delete(Long id);
	 */

}
