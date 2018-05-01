package cn.ntt.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ntt.oa.base.DaoSupportImpl;
import cn.ntt.oa.domain.Forum;
import cn.ntt.oa.service.ForumService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements
		ForumService {

	@Override
	public java.util.List<Forum> findAll() {
		return getSession().createQuery(//
				"FROM Forum f ORDER BY f.position")//
				.list();
	};

	@Override
	public void save(Forum forum) {
		super.save(forum);
		forum.setPosition(forum.getId().intValue());
	};

	@Override
	public void moveUp(Long id) {
		// TODO Auto-generated method
		// 找到自己
		Forum forum = getById(id);
		// 找到自己的上一个
		Forum other = (Forum) getSession()
				.createQuery(
						"FROM  Forum f WHERE f.position<? ORDER BY f.position DESC")
				//
				.setParameter(0, forum.getPosition()).setFirstResult(0)
				.setMaxResults(1).uniqueResult();
		// 第一个不能再移动了
		if (other == null) {
			return;
		}
		// 交换position
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		// 更新
		getSession().update(forum);
		getSession().update(other);
	}

	@Override
	public void moveDown(Long id) {
		// TODO Auto-generated method stub
		// 找到自己
		Forum forum = getById(id);
		// 找到自己的下一个
		Forum other = (Forum) getSession()
				.createQuery(
						"FROM  Forum f WHERE f.position>? ORDER BY f.position ASC")
				//
				.setParameter(0, forum.getPosition()).setFirstResult(0)
				.setMaxResults(1).uniqueResult();
		// 第一个不能再移动了
		if (other == null) {
			return;
		}
		// 交换position
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		// 更新
		getSession().update(forum);
		getSession().update(other);

	}

}
