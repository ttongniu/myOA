package cn.ntt.oa.view.action;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.ntt.oa.base.ModelDrivenBaseAction;
import cn.ntt.oa.domain.User;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class PersonAction extends ModelDrivenBaseAction<User> {
	private String phoneNumber;
	private String email;
	private String passWord;
	private String result;
	// 个人信息修改UI
	public String editInfoUI() {
		// 数据准备
		User user = (User) ActionContext.getContext().getSession().get("user");
		System.out.println(user.getName());
		System.out.println(user.getRoles());
		ActionContext.getContext().getValueStack()
				.push(userService.getById(user.getId()));
		return "editInfoUI";
	}

	// 个人信息修改
	public String editInfo() {
		System.out.println("***");
		User user = null;
		user = (User) ActionContext.getContext().getSession().get("user");
		// System.out.println(model.getPhoneNumber());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());

		userService.update(user);
		result = "个人信息修改成功";
		return SUCCESS;
	}

	// 修改密码UI
	public String editPassWordUI() {

		return "editPassWordUI";
	}

	// 验证用户
		public String validateLoginName() {
			System.out.println("进入验证");
			// User user = (User)
			// ActionContext.getContext().getSession().get("user");
			System.out.println(model.getLoginName());
			User user = userService.findByLoginName(model.getLoginName());
			System.out.println(user);
			if (user != null) {
				result = "用户名已存在，请重新输入";
			}else{
			result = "格式正确";
			}
			System.out.println(result);
			return SUCCESS;
		}
	
	// 验证原密码
	public String validatePassWord() {
		// System.out.println("进入验证");
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (DigestUtils.md5Hex(passWord).equals(user.getPassword())) {
			result = "原密码正确";

		} else {
			result = "原密码错误，请重新输入";
		}
		return SUCCESS;
	}

	// 修改密码
	public String editPassWord() {
		System.out.println("************");
		User user = (User) ActionContext.getContext().getSession().get("user");
		user.setPassword(DigestUtils.md5Hex(passWord));
		userService.update(user);
		result = "密码修改成功";
		return SUCCESS;
	}

	// --
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
