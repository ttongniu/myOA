package cn.ntt.oa.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ntt.oa.base.DaoSupportImpl;
import cn.ntt.oa.domain.Privilege;
import cn.ntt.oa.service.PrivilegeService;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements
		PrivilegeService {

	@Override
	public List<Privilege> findTopList() {
		return getSession().createQuery(
				"FROM Privilege p WHERE p.parent IS NULL").list();
	}

	@Override
	public Collection<String> getAllPrivilegeUrls() {
		// TODO Auto-generated method stub
		return getSession()
				.createQuery(
						"SELECT  DISTINCT   p.url  FROM Privilege p WHERE p.url IS NOT NULL")
				.list();
	}

}
