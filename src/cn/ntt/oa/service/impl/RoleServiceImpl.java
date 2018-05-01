package cn.ntt.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ntt.oa.base.DaoSupportImpl;
import cn.ntt.oa.domain.Role;
import cn.ntt.oa.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements
		RoleService {
	/*
	 * @Resource private RoleDao roleDao;
	 * 
	 * @Override public List<Role> findAll() { return roleDao.findAll(); }
	 * 
	 * @Override public void delete(Long id) { roleDao.delete(id); }
	 * 
	 * @Override public void save(Role role) { roleDao.save(role); }
	 * 
	 * @Override public Role getById(Long id) { // TODO Auto-generated method
	 * stub return roleDao.getById(id); }
	 * 
	 * @Override public void update(Role role) { roleDao.update(role); }
	 */

}
