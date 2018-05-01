package cn.ntt.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.ntt.oa.base.ModelDrivenBaseAction;
import cn.ntt.oa.domain.Forum;
import cn.ntt.oa.domain.Reply;
import cn.ntt.oa.domain.Topic;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ReplyAction extends ModelDrivenBaseAction<Reply> {
	private Long topicId;

	// 发回复UI
	public String addUI() throws Exception {
		// 准备数据
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}

	// 发回复
	public String add() throws Exception {
		// 封装
		// >> 表单字段，已经封装了title, content
		// model.setTitle(title);
		// model.setContent(content);
		model.setTopic(topicService.getById(topicId));
		// >> 当前信息
		model.setAuthor(getCurrentUser()); // 当前用户
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date()); // 当前时间

		// 保存
		replyService.save(model);
		return "toTopicShow";
	}

	/*
	 * 删除回复
	 */
	public String delete() throws Exception {
		// 相关数据维护
		Reply reply = replyService.getById(model.getId());
		Topic topic = reply.getTopic();
		Forum forum = topic.getForum();
		forum.setArticleCount(forum.getArticleCount() - 1); // 文章数量（主题数+回复数）
		topic.setReplyCount(topic.getReplyCount() - 1); // 回复数量
		topicService.update(topic);
		forumService.update(forum);
		// 删除
		replyService.delete(model.getId());
		return "toTopicShow";
	}

	// ----
	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
}
