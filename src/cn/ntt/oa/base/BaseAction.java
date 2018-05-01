package cn.ntt.oa.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import cn.ntt.oa.domain.User;
import cn.ntt.oa.service.AddressBookService;
import cn.ntt.oa.service.ApplicationService;
import cn.ntt.oa.service.ApplicationTemplateService;
import cn.ntt.oa.service.CardService;
import cn.ntt.oa.service.CardcaseService;
import cn.ntt.oa.service.DepartmentService;
import cn.ntt.oa.service.FileService;
import cn.ntt.oa.service.FolderService;
import cn.ntt.oa.service.ForumService;
import cn.ntt.oa.service.PrivilegeService;
import cn.ntt.oa.service.ProcessDefinitionService;
import cn.ntt.oa.service.ReplyService;
import cn.ntt.oa.service.RoleService;
import cn.ntt.oa.service.TopicService;
import cn.ntt.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {

	// 分页参数准备
	protected int pageNum = 1;
	protected int pageSize = 5;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取当前登录的用户
	 * 
	 * @return
	 */
	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}

	/**
	 * 保存上传的文件，并返回文件在服务端的真实存储路径
	 * 
	 * @param upload
	 * @return
	 */
	protected String saveUploadFile(File upload) {
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		// >> 获取路径
		String basePath = ServletActionContext.getServletContext().getRealPath(
				"/WEB-INF/upload_files");
		String subPath = sdf.format(new Date());
		// >> 如果文件夹不存在，就创建
		File dir = new File(basePath + subPath);
		if (!dir.exists()) {
			dir.mkdirs(); // 递归的创建不存在的文件夹
		}
		// >> 拼接路径
		String path = basePath + subPath + UUID.randomUUID().toString();
		// >> 移动文件
		upload.renameTo(new File(path)); // 如果目标文件夹不存在，或是目标文件已存在，就会不成功，返回false，但不报错。
		return path;
	}

	// =============== Service实例的声明 ==================
	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;
	@Resource
	protected ForumService forumService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected ReplyService replyService;
	@Resource
	protected AddressBookService addressBookService;
	@Resource
	protected CardcaseService cardcaseService;
	@Resource
	protected CardService cardService;
	@Resource
	protected FolderService folderService;
	@Resource
	protected FileService fileService;
	@Resource
	protected ProcessDefinitionService processDefinitionService;
	@Resource
	protected ApplicationTemplateService applicationTemplateService;
	@Resource
	protected ApplicationService applicationService;
}
