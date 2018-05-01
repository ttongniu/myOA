package cn.ntt.oa.view.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.ntt.oa.base.ModelDrivenBaseAction;
import cn.ntt.oa.domain.Cardcase;
import cn.ntt.oa.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class CardcaseAction extends ModelDrivenBaseAction<Cardcase> {
	//private String name;

	/** 列表 */
	public String list() throws Exception {
		new QueryHelper(Cardcase.class, "c")//
				.addCondition("c.createBy=?", getCurrentUser())//
				.preparePageBean(cardcaseService, pageNum, pageSize);
		return "list";
	}

	/** Private列表 */
	public String listPrivate() throws Exception {
		String sname = "'%" + model.getName() + "%'";
		new QueryHelper(Cardcase.class, "c")//
		        .addCondition(model.getName() != null, "c.name like " + sname,null)//
				.addCondition("c.createBy=?", getCurrentUser())//
				.addCondition("c.type=?", Cardcase.TYPE_PRIVATE)//
				.preparePageBean(cardcaseService, pageNum, pageSize);
		return "listPrivate";
	}

	/** Share列表 */
	public String listShare() throws Exception {
		String sname = "'%" + model.getName() + "%'";
		new QueryHelper(Cardcase.class, "c")//
		.addCondition(model.getName() != null, "c.name like " + sname,null)//
		.addCondition("c.type=?",Cardcase.TYPE_SHARE)//
		.preparePageBean(cardcaseService, pageNum,pageSize);
		return "listShare";
	}

	/** 删除 */
	public String delete() throws Exception {
		cardcaseService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		Cardcase cardcase = new Cardcase();
		cardcase.setName(model.getName());
		cardcase.setDescription(model.getDescription());
		cardcase.setType(Cardcase.TYPE_PRIVATE);
		cardcase.setCreateTime(new Date());
		cardcase.setCreateBy(getCurrentUser());
		cardcaseService.save(cardcase);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备数据
		Cardcase cardcase = cardcaseService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(cardcase);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		Cardcase cardcase = cardcaseService.getById(model.getId());
		cardcase.setName(model.getName());
		cardcase.setDescription(model.getDescription());
		cardcaseService.update(cardcase);
		return "toList";
	}

	/** 私有 */
	public String setPrivate() throws Exception {
		Cardcase cardcase = cardcaseService.getById(model.getId());
		cardcase.setType(Cardcase.TYPE_PRIVATE);
		cardcaseService.update(cardcase);
		return "toList";
	}

	/** 共享 */
	public String setShare() throws Exception {
		Cardcase cardcase = cardcaseService.getById(model.getId());
		cardcase.setType(Cardcase.TYPE_SHARE);
		cardcaseService.update(cardcase);
		return "toList";
	}

	/*public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}*/

}
