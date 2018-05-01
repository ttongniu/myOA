package cn.ntt.oa.service;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ProcessDefinition;

public interface ProcessDefinitionService {
	/**
	 * 查询版本最新的流程
	 * 
	 * @return
	 */
	List<ProcessDefinition> findAllLatestVersions();

	/**
	 * 删除流程
	 * 
	 * @param key
	 */

	void deleteByKey(String key);

	/**
	 * 部署流程
	 * 
	 * @param zipInputStream
	 */
	void deploy(ZipInputStream zipInputStream);

	/**
	 * 获取流程的图片
	 * 
	 * @param processDefinitionId
	 * @return
	 */
	InputStream getProcessImageResourceAsStream(String processDefinitionId);

}
