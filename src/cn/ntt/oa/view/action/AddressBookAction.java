package cn.ntt.oa.view.action;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.ntt.oa.base.ModelDrivenBaseAction;
import cn.ntt.oa.domain.AddressBook;
import cn.ntt.oa.domain.Department;
import cn.ntt.oa.util.DepartmentUtils;
import cn.ntt.oa.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class AddressBookAction extends ModelDrivenBaseAction<AddressBook> {
	private Long departmentId;
	private String name;
	private String phoneNumber;
	private String result;

	/** 列表 */
	public String list() throws Exception {
		new QueryHelper(AddressBook.class, "a").preparePageBean(
				addressBookService, pageNum, pageSize);
		return "list";
	}

	/** 列表 */
	public String show() throws Exception {
		/*
		 * new QueryHelper(AddressBook.class, "a").
		 * preparePageBean(addressBookService, pageNum, pageSize);
		 */
		// 准备数据
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		Department department = departmentService.getById(departmentId);
		String sname = "'%" + model.getName() + "%'";
		String sphoneNumber = "'%" + model.getPhoneNumber() + "%'";
		new QueryHelper(AddressBook.class, "a")
				.addCondition(model.getName() != null, "a.name like " + sname,
						null)
				.addCondition(department != null, "a.department=?", department)
				.addCondition(model.getPhoneNumber() != null,
						"a.phoneNumber like " + sphoneNumber, null)
				.preparePageBean(addressBookService, pageNum, pageSize);
		return "show";
	}

	/** 删除 */
	public String delete() throws Exception {
		addressBookService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备数据
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 部门
		Department department = departmentService.getById(departmentId);
		AddressBook addressBook = new AddressBook();
		// 填属性
		addressBook.setAddress(model.getAddress());
		addressBook.setDepartment(department);
		addressBook.setDescription(model.getDescription());
		addressBook.setEmail(model.getEmail());
		addressBook.setGender(model.getGender());
		addressBook.setName(model.getName());
		addressBook.setPhoneNumber(model.getPhoneNumber());
		addressBook.setQQ(model.getQQ());
		// 保存
		addressBookService.save(addressBook);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备数据
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		AddressBook addressBook = addressBookService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(addressBook);
		if (addressBook.getDepartment() != null) {
			departmentId = addressBook.getDepartment().getId();
		}
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 获取对象
		AddressBook addressBook = addressBookService.getById(model.getId());
		// 填写属性
		addressBook.setAddress(model.getAddress());
		addressBook.setDepartment(departmentService.getById(departmentId));
		addressBook.setDescription(model.getDescription());
		addressBook.setEmail(model.getEmail());
		addressBook.setGender(model.getGender());
		addressBook.setName(model.getName());
		addressBook.setPhoneNumber(model.getPhoneNumber());
		addressBook.setQQ(model.getQQ());
		// 更新
		addressBookService.update(addressBook);
		return "toList";
	}

	/**
	 * 导出通讯录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exportBook() throws Exception {

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("公司通讯录信息汇总");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		for (int i = 0; i < 10; i++) {
			sheet.setColumnWidth(i, 4000);
		}
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);

		cell = row.createCell((short) 1);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);

		cell = row.createCell((short) 2);
		cell.setCellValue("性别");
		cell.setCellStyle(style);

		cell = row.createCell((short) 3);
		cell.setCellValue("电话号码");
		cell.setCellStyle(style);

		cell = row.createCell((short) 4);
		cell.setCellValue("电子邮件");
		cell.setCellStyle(style);

		cell = row.createCell((short) 5);
		cell.setCellValue("部门名称");
		cell.setCellStyle(style);

		cell = row.createCell((short) 6);
		cell.setCellValue("家庭地址");
		cell.setCellStyle(style);

		cell = row.createCell((short) 7);
		cell.setCellValue("QQ");
		cell.setCellStyle(style);

		cell = row.createCell((short) 8);
		cell.setCellValue("备注说明");
		cell.setCellStyle(style);

		List<AddressBook> addressBookList = addressBookService.findAll();
		for (int i = 0; i < addressBookList.size(); i++) {
			row = sheet.createRow((int) i + 1);
			AddressBook addressBook = (AddressBook) addressBookList.get(i);
			row.createCell(0).setCellValue(i + 1);
			row.createCell(1).setCellValue(addressBook.getName());
			row.createCell(2).setCellValue(addressBook.getGender());
			row.createCell(3).setCellValue(addressBook.getPhoneNumber());
			row.createCell(4).setCellValue(addressBook.getEmail());
			row.createCell(5).setCellValue(
					addressBook.getDepartment().getName());
			row.createCell(6).setCellValue(addressBook.getAddress());
			row.createCell(7).setCellValue(addressBook.getQQ());
			row.createCell(8).setCellValue(addressBook.getDescription());
			for (int j = 0; j <= 8; j++) {
				row.getCell(j).setCellStyle(style);
			}
		}

		try {

			FileOutputStream fout = new FileOutputStream(
					"C:/Users/Administrator/Desktop/公司通讯录信息汇总.xls");

			// setResult("1");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			// setResult("0");
			e.printStackTrace();
		}

		return "toExport";
	}

	// ---
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
