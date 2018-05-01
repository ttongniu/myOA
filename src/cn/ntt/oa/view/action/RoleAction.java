package cn.ntt.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.ntt.oa.base.ModelDrivenBaseAction;
import cn.ntt.oa.domain.Privilege;
import cn.ntt.oa.domain.Role;
import cn.ntt.oa.util.QueryHelper;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class RoleAction extends ModelDrivenBaseAction<Role> {
	private Long[] privilegeIds;

	/**
	 * 列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		/*
		 * List<Role> roleList = roleService.findAll();
		 * ActionContext.getContext().put("roleList", roleList);
		 */
		// 分页信息
		new QueryHelper(Role.class, "r").preparePageBean(roleService, pageNum,
				pageSize);
		return "list";
	}

	/**
	 * 刪除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	/**
	 * 添加頁面
	 * 
	 * @return
	 * @throws Exception
	 */

	public String addUI() throws Exception {
		return "saveUI";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		/*
		 * //封装对象role Role role=new Role(); role.setName(model.getName());
		 * role.setDescription(model.getDescription()); //保存到数据库
		 * roleService.save(role);
		 */
		roleService.save(model);
		return "toList";
	}

	/**
	 * 修改頁面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		// 回显数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		// 查对象
		Role role = roleService.getById(model.getId());

		role.setName(model.getName());
		role.setDescription(model.getDescription());

		roleService.update(role);
		return "toList";
	}

	/**
	 * 设置权限頁面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setPrivilegeUI() throws Exception {
		// 回显数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		if (role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for (Privilege priv : role.getPrivileges()) {
				privilegeIds[index++] = priv.getId();
			}
		}
		// 准备数据
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);

		return "setPrivilegeUI";
	}

	/**
	 * 设置权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setPrivilege() throws Exception {
		// 查对象
		Role role = roleService.getById(model.getId());
		List<Privilege> privileges = privilegeService.getByIds(privilegeIds);
		// 该属性
		role.setPrivileges(new HashSet<Privilege>(privileges));
		// 更新
		roleService.update(role);
		return "toList";
	}

	// ------
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}
