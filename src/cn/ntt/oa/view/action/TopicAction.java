package cn.ntt.oa.view.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.ntt.oa.base.ModelDrivenBaseAction;
import cn.ntt.oa.domain.Forum;
import cn.ntt.oa.domain.Reply;
import cn.ntt.oa.domain.Topic;
import cn.ntt.oa.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TopicAction extends ModelDrivenBaseAction<Topic> {
	private Long forumId;

	// 显示单个模块
	public String show() throws Exception {
		// 准备数据 topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		// 准备数据replyList
		// List<Reply> replyList=replyService.findByTopic(topic);
		// ActionContext.getContext().put("replyList", replyList);
		// 准备数据replyList分页
		// PageBean pageBean=replyService.getPageBeanByTopic( pageNum,
		// pageSize,topic);
		// ActionContext.getContext().getValueStack().push(pageBean);
		/*
		 * String hql = "FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC";
		 * List<Object> parameters = new ArrayList<Object>();
		 * parameters.add(topic); PageBean pageBean =
		 * replyService.getPageBean(pageNum, pageSize, hql, parameters);
		 * ActionContext.getContext().getValueStack().push(pageBean);
		 */
		new QueryHelper(Reply.class, "r").addCondition("r.topic=?", topic)
				.addOrderProperty("r.postTime", true)
				.preparePageBean(replyService, pageNum, pageSize);
		return "show";
	}

	// 发新帖UI
	public String addUI() throws Exception {
		// 准备数据
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}

	// 发新帖
	public String add() throws Exception {
		// 封装
		// >> 表单参数，已经封装了title, content
		// model.setTitle(title);
		// model.setContent(content);
		model.setForum(forumService.getById(forumId));
		// >> 当前直接获取的信息
		model.setAuthor(getCurrentUser()); // 当前登录用户
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr()); // 当前请求中的IP
		model.setPostTime(new Date()); // 当前时间

		// 保存
		topicService.save(model);
		return "toShow";
	}

	// 删除帖子
	public String delete() throws Exception {
		// 维护相关数据
		Topic topic = topicService.getById(model.getId());
		Forum forum = topic.getForum();
		forum.setTopicCount(forum.getTopicCount() - 1);// 主题数量
		forum.setArticleCount(forum.getArticleCount() - topic.getReplyCount()
				- 1);// 文章数量
		forumService.update(forum);
		// 保存
		topicService.delete(model.getId());
		return "toForumShow";
	}

	// 帖子置顶
	public String setTop() throws Exception {
		Topic topic = topicService.getById(model.getId());
		topic.setType(Topic.TYPE_TOP);
		topicService.update(topic);
		return "toShow";
	}

	// 帖子加精
	public String setBest() throws Exception {
		Topic topic = topicService.getById(model.getId());
		topic.setType(Topic.TYPE_BEST);
		topicService.update(topic);
		return "toShow";
	}

	// 帖子设为普通
	public String setNormal() throws Exception {
		Topic topic = topicService.getById(model.getId());
		topic.setType(Topic.TYPE_NORMAL);
		topicService.update(topic);
		return "toShow";
	}

	// 移动到其他版块UI
	public String moveUI() throws Exception {

		// 准备数据
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		// 准备数据
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);

		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		return "moveUI";
	}

	// 移动到其他版块
	public String move() throws Exception {
		Topic topic = topicService.getById(model.getId());
		// 修改原forum
		Forum forum = topic.getForum();
		forum.setTopicCount(forum.getTopicCount() - 1); // 主题数量
		forum.setArticleCount(forum.getArticleCount() - 1);// 文章数量（主题数+回复数）
		forumService.update(forum);

		topic.setForum(forumService.getById(forumId));
		topicService.move(topic);
		return "toShow";
	}

	// --
	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

}
