package cn.ntt.oa.service;

import java.util.List;

import cn.ntt.oa.base.DaoSupport;
import cn.ntt.oa.domain.Department;

public interface DepartmentService extends DaoSupport<Department> {

	/*
	 * List<Department> findAll();
	 * 
	 * void delete(Long id);
	 * 
	 * void save(Department model);
	 * 
	 * Department getById(Long id);
	 * 
	 * void update(Department department);
	 */
	/**
	 * 查询顶级部门列表
	 * 
	 * @return
	 */
	List<Department> findTopList();

	/**
	 * 查询子级部门列表
	 * 
	 * @param parentId
	 * @return
	 */
	List<Department> findChildren(Long parentId);

}
