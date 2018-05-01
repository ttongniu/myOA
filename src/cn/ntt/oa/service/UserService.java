package cn.ntt.oa.service;

import java.util.List;

import cn.ntt.oa.base.DaoSupport;
import cn.ntt.oa.domain.User;

public interface UserService extends DaoSupport<User> {
	/**
	 * 根据用户名和密码查询用户
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);

	/**
	 * 按isdel 查所有
	 */
	@Override
	public List<User> findAll();

	/**
	 * 验证用户
	 * 
	 * @param loginName
	 * @return
	 */
	User findByLoginName(String loginName);
	/**
	 * 模糊查询用户
	 * 
	 * @param loginName
	 * @return
	 */
	/*
	User findByName(String userName);*/

}
