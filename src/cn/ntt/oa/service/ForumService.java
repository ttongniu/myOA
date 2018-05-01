package cn.ntt.oa.service;

import cn.ntt.oa.base.DaoSupport;
import cn.ntt.oa.domain.Forum;

public interface ForumService extends DaoSupport<Forum> {
	/**
	 * 上移 最后一行不能上移
	 * 
	 * @param id
	 */
	void moveUp(Long id);

	/**
	 * 下移 最后一行不能下移
	 * 
	 * @param id
	 */
	void moveDown(Long id);

}
