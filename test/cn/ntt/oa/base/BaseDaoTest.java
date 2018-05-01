package cn.ntt.oa.base;

import org.junit.Test;

import cn.ntt.oa.dao.RoleDao;
import cn.ntt.oa.dao.UserDao;
import cn.ntt.oa.dao.impl.RoleDaoImpl;
import cn.ntt.oa.dao.impl.UserDaoImpl;

public class BaseDaoTest {

	@Test
	public void testSave() {
		UserDao userDao = new UserDaoImpl();
		RoleDao roleDao = new RoleDaoImpl();
	}

}
