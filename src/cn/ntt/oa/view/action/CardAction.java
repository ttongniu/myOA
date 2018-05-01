package cn.ntt.oa.view.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.ntt.oa.base.ModelDrivenBaseAction;
import cn.ntt.oa.domain.Card;
import cn.ntt.oa.domain.Cardcase;
import cn.ntt.oa.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class CardAction extends ModelDrivenBaseAction<Card> {

	private Long cardcaseId;

	/** 列表 */
	public String list() throws Exception {

		Cardcase cardcase = cardcaseService.getById(model.getId());
		ActionContext.getContext().put("cardcase", cardcase);
		new QueryHelper(Card.class, "c").addCondition("c.cardcase=?", cardcase)
				.preparePageBean(cardService, pageNum, pageSize);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		cardService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		System.out.println("cardcaseId" + cardcaseId);
		Cardcase cardcase = cardcaseService.getById(cardcaseId);
		ActionContext.getContext().put("cardcase", cardcase);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		/*
		 * Card card=new Card(); System.out.println("********************");
		 * card.setAddress(model.getAddress());
		 * System.out.println("cardcaseId"+cardcaseId);
		 * card.setCardcase(cardcaseService.getById(cardcaseId));
		 * card.setCompany(model.getCompany()); card.setCreateDate();
		 * card.setDepartmentName(model.getDepartmentName());
		 * card.setDescription(model.getDescription());
		 * card.setEmail(model.getEmail());
		 * card.setEnglishName(model.getEnglishName());
		 * card.setName(model.getName()); card.setNickName(model.getNickName());
		 * card.setQQ(model.getQQ());
		 * card.setTelBusiness(model.getTelBusiness());
		 * card.setTelHome(model.getTelHome());
		 * card.setVocation(model.getVocation());
		 * card.setZipCode(model.getZipCode());
		 */
		model.setCardcase(cardcaseService.getById(cardcaseId));
		model.setCreateDate(new Date());
		cardService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		Card card = cardService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(card);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		Card card = cardService.getById(model.getId());

		card.setAddress(model.getAddress());
		card.setCardcase(cardcaseService.getById(cardcaseId));
		card.setCompany(model.getCompany());
		card.setCreateDate(new Date());
		card.setDepartmentName(model.getDepartmentName());
		card.setDescription(model.getDescription());
		card.setEmail(model.getEmail());
		card.setEnglishName(model.getEnglishName());
		card.setName(model.getName());
		card.setNickName(model.getNickName());
		card.setQQ(model.getQQ());
		card.setTelBusiness(model.getTelBusiness());
		card.setTelHome(model.getTelHome());
		card.setVocation(model.getVocation());
		card.setZipCode(model.getZipCode());

		cardService.update(card);
		return "toList";
	}

	// ---
	public Long getCardcaseId() {
		return cardcaseId;
	}

	public void setCardcaseId(Long cardcaseId) {
		this.cardcaseId = cardcaseId;
	}

}
