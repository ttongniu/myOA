package cn.ntt.oa.util;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;

import cn.ntt.oa.domain.Privilege;
import cn.ntt.oa.domain.TaskView;
import cn.ntt.oa.domain.User;
import cn.ntt.oa.service.ApplicationService;
import cn.ntt.oa.service.PrivilegeService;

/**
 * 准备初始数据
 * 
 * @author NTT
 * 
 */
public class InitListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		// 获取容器与相关的Service对象
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ac
				.getBean("privilegeServiceImpl");

		// 准备数据：topPrivilegeList
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		sce.getServletContext().setAttribute("topPrivilegeList",
				topPrivilegeList);
		System.out.println("------------> 已准备数据 <------------");
		// System.out.println(topPrivilegeList.toString());

		// 准备数据：allPrivilegeUrls
		Collection<String> allPrivilegeUrls = privilegeService
				.getAllPrivilegeUrls();
		sce.getServletContext().setAttribute("allPrivilegeUrls",
				allPrivilegeUrls);
		System.out.println("------------> 已准备数据allPrivilegeUrls <------------");
		
		/*ApplicationService applicationService = (ApplicationService) ac
				.getBean("applicationServiceImpl");
		
		List<TaskView> taskViewList  =   applicationService.getMyTaskViewList(getCurrentUser());
		sce.getServletContext().setAttribute("taskViewList",
				taskViewList);
		System.out.println("------------> 已准备数据taskViewList <------------");*/
	}
	/*protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}*/
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
