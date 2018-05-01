package cn.ntt.oa.view.action;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.ntt.oa.base.ModelDrivenBaseAction;
import cn.ntt.oa.domain.Department;
import cn.ntt.oa.domain.Role;
import cn.ntt.oa.domain.User;
import cn.ntt.oa.util.DepartmentUtils;
import cn.ntt.oa.util.QueryHelper;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class DepartmentAction extends ModelDrivenBaseAction<Department> {

	private Long parentId;

	/** 列表 */
	public String list() throws Exception {
		// List<Department> departmentList= departmentService.findAll();
		// List<Department> departmentList = null;
		if (parentId == null) {
			// departmentList = departmentService.findTopList();

			new QueryHelper(Department.class, "d").addCondition(
					"d.parent IS NULL ", null).preparePageBean(
					departmentService, pageNum, pageSize);
		} else {
			// departmentList = departmentService.findChildren(parentId);
			new QueryHelper(Department.class, "d").addCondition(
					"d.parent.id=?", parentId).preparePageBean(
					departmentService, pageNum, pageSize);
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}

		// ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备departmentList 数据
		// List<Department> departmentList= departmentService.findAll();
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {

		Department parent = departmentService.getById(parentId);
		model.setParent(parent);
		departmentService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备departmentList 数据
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);

		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}

		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		Department department = departmentService.getById(model.getId());
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId));
		departmentService.update(department);
		return "toList";
	}

	/** 导出部门信息 */
	public String exportDepartment() throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("公司部门信息汇总");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		for (int i = 0; i < 4; i++) {
			sheet.setColumnWidth(i, 4000);
		}
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);

		cell = row.createCell((short) 1);
		cell.setCellValue("部门名称");
		cell.setCellStyle(style);

		cell = row.createCell((short) 2);
		cell.setCellValue("父部门");
		cell.setCellStyle(style);

		cell = row.createCell((short) 3);
		cell.setCellValue("备注");
		cell.setCellStyle(style);

		// List<User> userList = userService.findAll();
		List<Department> departmentList = departmentService.findAll();
		for (int i = 0; i < departmentList.size(); i++) {
			row = sheet.createRow((int) i + 1);
			// User user = (User) userList.get(i);
			Department department = (Department) departmentList.get(i);
			row.createCell(0).setCellValue(i + 1);
			row.createCell(1).setCellValue(department.getName());
			row.createCell(2).setCellValue(
					department.getParent() == null ? "无" : department
							.getParent().getName());//
			row.createCell(3).setCellValue(
					department.getDescription() == null ? "" : department
							.getDescription());//
			for (int j = 0; j <= 3; j++) {
				row.getCell(j).setCellStyle(style);
			}
		}

		try {
			FileOutputStream fout = new FileOutputStream(
					"C:/Users/Administrator/Desktop/公司部门信息汇总.xls");
			// setResult("1");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			// setResult("0");
			e.printStackTrace();
		}
		return "toExport";
	}

	// ----
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
