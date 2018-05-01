package cn.ntt.oa.service;

import java.util.List;
import java.util.Set;

import cn.ntt.oa.base.DaoSupport;
import cn.ntt.oa.domain.Application;
import cn.ntt.oa.domain.ApproveInfo;
import cn.ntt.oa.domain.TaskView;
import cn.ntt.oa.domain.User;

public interface ApplicationService extends DaoSupport<Application> {
	/**
	 * 保存申请信息，并启动流程开始流转
	 * 
	 * @param application
	 */
	void submit(Application application);

	/**
	 * 查询我的任务信息列表
	 * 
	 * @param currentUser
	 * @return
	 */
	List<TaskView> getMyTaskViewList(User currentUser);

	/**
	 * 获取指定任务活动中所有流出的连线名称
	 * 
	 * @param taskId
	 * @return
	 */
	Set<String> getOutcomesByTaskId(String taskId);

	/**
	 * 保存本次审批信息，并办理完任务，并维护申请的状态
	 * 
	 * @param approveInfo
	 * @param taskId
	 * @param outcome
	 */
	void approve(ApproveInfo approveInfo, String taskId, String outcome);

}
