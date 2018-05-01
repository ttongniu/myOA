package cn.ntt.oa.view.action;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.ntt.oa.base.ModelDrivenBaseAction;
import cn.ntt.oa.domain.Department;
import cn.ntt.oa.domain.Role;
import cn.ntt.oa.domain.User;
import cn.ntt.oa.service.UserService;
import cn.ntt.oa.util.DepartmentUtils;
import cn.ntt.oa.util.QueryHelper;
import cn.ntt.oa.util.RandomNumUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAction extends ModelDrivenBaseAction<User> {
	private Long departmentId;
	private Long[] roleIds;
	private String name;
	//private String loginName;
	private ByteArrayInputStream inputStream;
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/** 列表 */
	public String list() throws Exception {
		/*
		 * List<User> userList = userService.findAll();
		 * ActionContext.getContext().put("userList", userList);
		 */

		// 准备数据
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		Department department = departmentService.getById(departmentId);
		String sname = "'%" + model.getName() + "%'";
		new QueryHelper(User.class, "u")
				.addCondition("u.isdel=?", User.ISNOTDEL)
				.addCondition(model.getName() != null, "u.name like " + sname,
						null)
				.addCondition(department != null, "u.department=?", department)
				.preparePageBean(userService, pageNum, pageSize);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		// userService.delete(model.getId());
		// 拿到原数据
		User user = userService.getById(model.getId());
		user.setIsdel(User.ISDEL);
		userService.update(user);
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备数据 department
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		// 准备数据 role
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 其余属性自动封装
		model.setIsdel(User.ISNOTDEL);
		// 设置部门
		model.setDepartment(departmentService.getById(departmentId));
		// 设置岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		// 设置密码 默认为1234
		// MD5加密字符串(使用MD5摘要)
		String md5Digest = DigestUtils.md5Hex("1234");
		model.setPassword(md5Digest);
		userService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备数据 department
		List<Department> topList = departmentService.findAll();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		// 准备数据 role
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		// 回显数据准备
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);

		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		if (user.getRoles() != null) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 拿到原数据
		User user = userService.getById(model.getId());
		// 修改数据
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// 设置部门
		user.setDepartment(departmentService.getById(departmentId));
		// 设置岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		// 更新到数据库
		userService.update(user);
		return "toList";
	}

	/** 初始化密码 */
	public String initPassword() throws Exception {
		// 拿到原数据
		User user = userService.getById(model.getId());
		// 修改数据
		String md5Digest = DigestUtils.md5Hex("1234");
		user.setPassword(md5Digest);
		// 更新到数据库
		userService.update(user);
		return "toList";
	}

	/** 导出用户 */
	public String exportUser() throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("公司员工信息汇总");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		for (int i = 0; i < 6; i++) {
			sheet.setColumnWidth(i, 4000);
		}
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);

		cell = row.createCell((short) 1);
		cell.setCellValue("登录名");
		cell.setCellStyle(style);

		cell = row.createCell((short) 2);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);

		cell = row.createCell((short) 3);
		cell.setCellValue("所属部门");
		cell.setCellStyle(style);

		cell = row.createCell((short) 4);
		cell.setCellValue("岗位");
		cell.setCellStyle(style);

		cell = row.createCell((short) 5);
		cell.setCellValue("备注");
		cell.setCellStyle(style);

		List<User> userList = userService.findAll();
		for (int i = 0; i < userList.size(); i++) {
			row = sheet.createRow((int) i + 1);
			User user = (User) userList.get(i);
			row.createCell(0).setCellValue(i + 1);
			row.createCell(1).setCellValue(user.getLoginName());
			row.createCell(2).setCellValue(user.getName());
			row.createCell(3).setCellValue(
					user.getDepartment().getName() == null ? "" : user
							.getDepartment().getName());
			Set<Role> roleSet = (Set<Role>) user.getRoles();
			String roleName = "";
			Iterator<Role> it = roleSet.iterator();
			while (it.hasNext()) {
				roleName += it.next().getName() + " ";
			}
			row.createCell(4).setCellValue(roleName);
			row.createCell(5).setCellValue(
					user.getDescription() == null ? "" : user.getDescription());
			for (int j = 0; j <= 5; j++) {
				row.getCell(j).setCellStyle(style);
			}
		}

		try {
			FileOutputStream fout = new FileOutputStream(
					"C:/Users/Administrator/Desktop/公司员工信息汇总.xls");
			// setResult("1");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			// setResult("0");
			e.printStackTrace();
		}
		return "toExport";
	}

	/*// 验证用户
	public String validateLoginName() {
		System.out.println("进入验证");
		// User user = (User)
		// ActionContext.getContext().getSession().get("user");
		System.out.println(loginName);
		User user = userService.findByLoginName(loginName);
		System.out.println(user);
		if (user != null) {
			result = "用户名已存在，请重新输入";
		}
		System.out.println(result);
		return SUCCESS;
	}*/

	/* 登录页面 */
	public String loginUI() throws Exception {

		return "loginUI";

	}

	// 登录
	public String login() throws Exception {
		System.out.println("进入");
		User user = userService.findByLoginNameAndPassword(
				model.getLoginName(), model.getPassword());
		if (user == null) {
			addFieldError("login", "用户名或密码不正确！！！");
			return "loginUI";
		} else {

			// 登录用户
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}

	}

	// 注销
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}

	/*
	 * //创建验证码 public String getRandomPictrue() throws Exception{
	 * System.out.println("***************"); RandomNumUtil
	 * rdnu=RandomNumUtil.Instance();
	 * this.setInputStream(rdnu.getImage());//取得带有随机字符串的图片
	 * ActionContext.getContext().getSession().put("random",
	 * rdnu.getString());//取得随机字符串放入HttpSession return SUCCESS; }
	 */
	// ----------------------
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	/*public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}*/

}
