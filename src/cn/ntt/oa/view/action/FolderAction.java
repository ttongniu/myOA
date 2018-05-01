package cn.ntt.oa.view.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.ntt.oa.base.ModelDrivenBaseAction;
import cn.ntt.oa.domain.Folder;
import cn.ntt.oa.domain.User;
import cn.ntt.oa.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class FolderAction extends ModelDrivenBaseAction<Folder> {
	private String userName;
	

	/** 列表 */
	public String list() throws Exception {
		new QueryHelper(Folder.class, "f").addCondition("f.user=?",
				getCurrentUser()).preparePageBean(folderService, pageNum,
				pageSize);
		return "list";
	}

	/** 个人文件夹列表 */
	public String privateList() throws Exception {
		String sname = "'%" + model.getName() + "%'";
		new QueryHelper(Folder.class, "f")//
				.addCondition(model.getName() != null, "f.name like " + sname,null)//
				.addCondition("f.user=?", getCurrentUser())//
				.addCondition("f.type=?", Folder.PRIVATE)//
				.preparePageBean(folderService, pageNum, pageSize);
		return "listPrivate";
	}

	/** 共享文件夹列表 */
	public String shareList() throws Exception {
		String sname = "'%" + model.getName() + "%'";
		new QueryHelper(Folder.class, "f")//
		.addCondition(model.getName() != null, "f.name like " + sname,null)//
		.addCondition("f.type=?",Folder.SHARE)//
		.preparePageBean(folderService, pageNum, pageSize);
		return "listShare";
	}

	/** 删除 */
	public String delete() throws Exception {
		folderService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {

		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		model.setCreateTime(new Date());
		model.setType(Folder.PRIVATE);
		model.setUser(getCurrentUser());
		folderService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备回显数据
		Folder folder = folderService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(folder);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		Folder folder = folderService.getById(model.getId());

		folder.setName(model.getName());
		folder.setDescription(model.getDescription());

		folderService.update(folder);

		return "toList";
	}

	/** 设为私有 */
	public String setPrivate() throws Exception {
		Folder folder = folderService.getById(model.getId());

		folder.setType(Folder.PRIVATE);

		folderService.update(folder);

		return "toList";
	}

	/** 设为共享 */
	public String setShare() throws Exception {
		Folder folder = folderService.getById(model.getId());

		folder.setType(Folder.SHARE);

		folderService.update(folder);

		return "toList";
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
