package cn.ntt.oa.base;

import java.util.List;

import cn.ntt.oa.domain.PageBean;
import cn.ntt.oa.util.QueryHelper;

public interface DaoSupport<T> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 按id查询
	 * 
	 * @param id
	 * @return
	 */
	T getById(Long id);

	/**
	 * 按id查询
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 分页查讯
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param hql
	 * @param parameters
	 * @return
	 */
	@Deprecated
	PageBean getPageBean(int pageNum, int pageSize, String hql,
			List<Object> parameters);

	PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper);

}
