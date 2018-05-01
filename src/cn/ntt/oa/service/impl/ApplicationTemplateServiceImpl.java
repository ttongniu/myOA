package cn.ntt.oa.service.impl;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ntt.oa.base.DaoSupportImpl;
import cn.ntt.oa.domain.ApplicationTemplate;
import cn.ntt.oa.service.ApplicationTemplateService;

@Service
@Transactional
public class ApplicationTemplateServiceImpl extends
		DaoSupportImpl<ApplicationTemplate> implements
		ApplicationTemplateService {
	@Override
	public void delete(Long id) {
		// 删除数据库记录
		ApplicationTemplate applicationTemplate = getById(id);
		getSession().delete(applicationTemplate);

		// 删除文件
		File file = new File(applicationTemplate.getPath());
		if (file.exists()) {
			file.delete();
		}
	}

}
