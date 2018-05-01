package cn.ntt.oa.util;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.ntt.oa.domain.Privilege;
import cn.ntt.oa.domain.User;

//import cn.ntt.oa.domain.User;

@Component
public class Installer {

	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 执行安装
	 */
	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();

		// ==============================================================
		// 保存超级管理员用户
		User user = new User();
		user.setLoginName("admin");
		user.setName("超级管理员");
		user.setPassword(DigestUtils.md5Hex("admin"));
		session.save(user); // 保存

		// ==============================================================
		/*
		 * // 保存权限数据 Privilege menu, menu1, menu2, menu3, menu4, menu5;
		 * 
		 * //1.-------------------- menu = new Privilege("日程管理", null, null);
		 * menu1 = new Privilege("日程安排", "/schedule_list", menu);
		 * 
		 * session.save(menu); session.save(menu1);
		 * 
		 * // 2.审批流转--------------------/schedule_list menu = new
		 * Privilege("审批流转", null, null); menu1 = new Privilege("审批流程管理",
		 * "/processDefinition_list", menu); menu2 = new Privilege("申请模板管理",
		 * "/applicationTemplate_list", menu); menu3 = new Privilege("起草申请",
		 * "/flow_applicationTemplateList", menu); menu4 = new Privilege("待我审批",
		 * "/flow_myTaskList", menu); menu5 = new Privilege("我的申请查询",
		 * "/flow_myApplicationList", menu); session.save(menu);
		 * session.save(menu1); session.save(menu2); session.save(menu3);
		 * session.save(menu4); session.save(menu5);
		 * 
		 * 
		 * //3.网上交流-------------------- menu = new Privilege("网上交流", null,
		 * null); menu1 = new Privilege("论坛管理", "/forumManage_list", menu);
		 * menu2 = new Privilege("论坛", "/forum_list", menu); session.save(menu);
		 * session.save(menu1); session.save(menu2);
		 * 
		 * session.save(new Privilege("版块添加", "/forumManage_add", menu1));
		 * session.save(new Privilege("版块修改", "/forumManage_edit", menu1));
		 * session.save(new Privilege("版块删除", "/forumManage_delete", menu1));
		 * session.save(new Privilege("版块移动", "/forumManage_move", menu1));
		 * 
		 * session.save(new Privilege("主题添加", "/topic_add", menu2));
		 * session.save(new Privilege("修改帖子类型", "/topic_set", menu2));
		 * session.save(new Privilege("主题删除", "/topic_delete", menu2));
		 * session.save(new Privilege("主题移动", "/topic_move", menu2));
		 * session.save(new Privilege("回复删除", "/reply_delete", menu2));
		 * session.save(new Privilege("回复添加", "/reply_add", menu2));
		 * 
		 * 
		 * 
		 * //4.公司通讯录-------------------- menu = new Privilege("公司通讯录", null,
		 * null); menu1 = new Privilege("通讯录管理", "/addressBook_list", menu);
		 * menu2 = new Privilege("通讯录", "/addressBook_show", menu);
		 * session.save(menu); session.save(menu1); session.save(menu2);
		 * 
		 * //5.名片夹-------------------- menu = new Privilege("名片夹", null, null);
		 * menu1 = new Privilege("名片夹管理", "/cardcase_list", menu); menu2 = new
		 * Privilege("个人名片夹", "/cardcase_listPrivate", menu); menu3 = new
		 * Privilege("共享名片夹", "/cardcase_listShare", menu); session.save(menu);
		 * session.save(menu1); session.save(menu2); session.save(menu3);
		 * 
		 * //6.知识管理-------------------- menu = new Privilege("知识管理", null,
		 * null); menu1 = new Privilege("文件夹管理", "/folder_list", menu); menu2 =
		 * new Privilege("个人文件夹", "/folder_privateList", menu); menu3 = new
		 * Privilege("共享文件夹", "/folder_shareList", menu); session.save(menu);
		 * session.save(menu1); session.save(menu2); session.save(menu3);
		 * 
		 * //7.个人设置-------------------- menu = new Privilege("个人设置", null,
		 * null); menu1 = new Privilege("个人信息", "/person_editInfoUI", menu);
		 * menu2 = new Privilege("修改密码", "/person_editPassWordUI", menu);
		 * 
		 * session.save(menu); session.save(menu1); session.save(menu2);
		 * //8.系统管理 // -------------------- menu = new Privilege("系统管理", null,
		 * null); menu1 = new Privilege("岗位管理", "/role_list", menu); menu2 = new
		 * Privilege("部门管理", "/department_list", menu); menu3 = new
		 * Privilege("用户管理", "/user_list", menu); session.save(menu);
		 * session.save(menu1); session.save(menu2); session.save(menu3);
		 * 
		 * session.save(new Privilege("岗位列表", "/role_list", menu1));
		 * session.save(new Privilege("岗位删除", "/role_delete", menu1));
		 * session.save(new Privilege("岗位添加", "/role_add", menu1));
		 * session.save(new Privilege("岗位修改", "/role_edit", menu1));
		 * 
		 * session.save(new Privilege("部门列表", "/department_list", menu2));
		 * session.save(new Privilege("部门删除", "/department_delete", menu2));
		 * session.save(new Privilege("部门添加", "/department_add", menu2));
		 * session.save(new Privilege("部门修改", "/department_edit", menu2));
		 * 
		 * session.save(new Privilege("用户列表", "/user_list", menu3));
		 * session.save(new Privilege("用户删除", "/user_delete", menu3));
		 * session.save(new Privilege("用户添加", "/user_add", menu3));
		 * session.save(new Privilege("用户修改", "/user_edit", menu3));
		 * session.save(new Privilege("初始化密码", "/user_initPassword", menu3));
		 */

	}

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();
	}
}
