package cn.ntt.oa.service;

import java.util.Collection;
import java.util.List;

import cn.ntt.oa.base.DaoSupport;
import cn.ntt.oa.domain.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege> {
	// 查找顶级权限
	List<Privilege> findTopList();

	// 查找权限的URL
	Collection<String> getAllPrivilegeUrls();

}
