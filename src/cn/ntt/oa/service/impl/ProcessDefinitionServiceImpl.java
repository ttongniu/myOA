package cn.ntt.oa.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ntt.oa.service.ProcessDefinitionService;

@Service
@Transactional
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private ProcessEngine processEngine;

	@Override
	public List<ProcessDefinition> findAllLatestVersions() {
		// 查詢所有的流程
		List<ProcessDefinition> all = processEngine.getRepositoryService()
				//
				.createProcessDefinitionQuery()
				.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)//
				.list();
		// 过滤最新版本的流程
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
		for (ProcessDefinition pd : all) {
			map.put(pd.getKey(), pd);
		}

		return new ArrayList<ProcessDefinition>(map.values());
	}

	@Override
	public void deleteByKey(String key) {
		// 根据key查询流程
		List<ProcessDefinition> list = processEngine.getRepositoryService()
				.createProcessDefinitionQuery().processDefinitionKey(key)
				.list();
		// 循环删除

		for (ProcessDefinition pd : list) {
			processEngine.getRepositoryService().deleteDeploymentCascade(
					pd.getDeploymentId());
		}
	}

	@Override
	public void deploy(ZipInputStream zipInputStream) {
		System.out.println("__________");
		processEngine.getRepositoryService()//
				.createDeployment()//
				.addResourcesFromZipInputStream(zipInputStream)//
				.deploy();
	}

	@Override
	public InputStream getProcessImageResourceAsStream(
			String processDefinitionId) {
		ProcessDefinition pd = processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.processDefinitionId(processDefinitionId)//
				.uniqueResult();
		// 返回圖片資源
		return processEngine.getRepositoryService().getResourceAsStream(
				pd.getDeploymentId(), pd.getImageResourceName());
	}

}
