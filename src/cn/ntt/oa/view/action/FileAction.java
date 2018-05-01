package cn.ntt.oa.view.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.ntt.oa.base.ModelDrivenBaseAction;
import cn.ntt.oa.domain.File;
import cn.ntt.oa.domain.Folder;
import cn.ntt.oa.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class FileAction extends ModelDrivenBaseAction<File> {
	private Long folderId;
	private java.io.File upload;
	private String uploadFileName;
	private String uploadContentType;
	private Long uploadContentLength;

	private InputStream inputStream;

	/** 列表 */
	public String list() throws Exception {
		Folder folder = folderService.getById(model.getId());
		ActionContext.getContext().put("folder", folder);
		new QueryHelper(File.class, "f").addCondition("f.folder=?", folder)
				.preparePageBean(fileService, pageNum, pageSize);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		fileService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */

	public String addUI() throws Exception {
		Folder folder = folderService.getById(folderId);
		ActionContext.getContext().put("folder", folder);
		return "addUI";
	}

	/** 上传文件 */
	public String add() throws Exception {
		System.out.println("__**************" + folderId);
		// 上传代码
		String path = saveUploadFile(upload);
		model.setAction(path);
		model.setCreateTime(new Date());
		model.setDownLoadNum(0);
		// model.setName(upload.getName());
		model.setName(uploadFileName);
		model.setSize(uploadContentLength);

		/*
		 * int beginIndex=upload.getName().indexOf('.'); String
		 * format=upload.getName().substring(beginIndex);
		 * model.setFormat(format);
		 */
		model.setFormat(uploadContentType);
		model.setFolder(folderService.getById(folderId));
		fileService.save(model);
		return "toList";
	}

	/* 修改 */
	public String editUI() throws Exception {
		// 回显数据
		List<Folder> folderList = folderService.findAll();
		ActionContext.getContext().put("folderList", folderList);

		File file = fileService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(file);

		return "editUI";
	}

	/** 修改文件夹 */
	public String edit() throws Exception {
		File file = fileService.getById(model.getId());
		file.setFolder(folderService.getById(folderId));
		fileService.update(file);
		ActionContext.getContext().put("folderId", folderId);
		return "toListedit";
	}

	/** 下载文件 */
	public String download() throws Exception {
		File file = fileService.getById(model.getId());
		file.setDownLoadNum(file.getDownLoadNum() + 1);
		fileService.update(file);
		inputStream = new FileInputStream(file.getAction());

		// 准备文件名（解决乱码问题）
		String fileName = URLEncoder.encode(file.getName(), "utf-8"); // 方法一
		// String fileName = new
		// String(applicationTemplate.getName().getBytes("gbk"), "iso8859-1");
		// // 方法二
		ActionContext.getContext().put("fileName", fileName);

		return "download";
	}

	// ---
	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public java.io.File getUpload() {
		return upload;
	}

	public void setUpload(java.io.File upload) {
		this.upload = upload;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public Long getUploadContentLength() {
		return uploadContentLength;
	}

	public void setUploadContentLength(Long uploadContentLength) {
		this.uploadContentLength = uploadContentLength;
	}
}
