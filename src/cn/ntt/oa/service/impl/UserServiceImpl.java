package cn.ntt.oa.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ntt.oa.base.DaoSupportImpl;
import cn.ntt.oa.domain.User;
import cn.ntt.oa.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements
		UserService {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {

		return getSession().createQuery(//
				"FROM User u WHERE u.isdel=? ")//
				.setParameter(0, User.ISNOTDEL).list();
	}

	@Override
	public User findByLoginNameAndPassword(String loginName, String password) {
		String md5Digest = DigestUtils.md5Hex(password);

		return (User) getSession().createQuery(//
				"FROM User u WHERE u.loginName=? AND u.password=?")
				//
				.setParameter(0, loginName).setParameter(1, md5Digest)
				.uniqueResult();
	}

	@Override
	public User findByLoginName(String loginName) {
		return (User) getSession().createQuery(//
				"FROM User u WHERE u.loginName=?")//
				.setParameter(0, loginName).uniqueResult();
	}
/*
	@Override
	public User findByName(String userName) {
		// TODO Auto-generated method stub
		return (User) getSession().createQuery(//
				"FROM User u WHERE u.loginName=?")//
				.setParameter(0, loginName).uniqueResult();
	}*/

}
