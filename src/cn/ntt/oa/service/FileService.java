package cn.ntt.oa.service;

import cn.ntt.oa.base.DaoSupport;
import cn.ntt.oa.domain.File;

public interface FileService extends DaoSupport<File> {
	/**
	 * 删除记录同时删除文件
	 */
	@Override
	public void delete(Long id);
}
