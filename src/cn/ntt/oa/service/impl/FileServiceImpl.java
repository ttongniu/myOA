package cn.ntt.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ntt.oa.base.DaoSupportImpl;
import cn.ntt.oa.domain.File;
import cn.ntt.oa.service.FileService;

@Service
@Transactional
public class FileServiceImpl extends DaoSupportImpl<File> implements
		FileService {
	@Override
	public void delete(Long id) {
		// 删除数据库记录
		File fileItem = getById(id);
		getSession().delete(fileItem);

		// 删除文件
		java.io.File filer = null;
		String path = fileItem.getAction();
		filer = new java.io.File(path);
		if (filer.exists()) {
			filer.delete();
		}
	}
}
