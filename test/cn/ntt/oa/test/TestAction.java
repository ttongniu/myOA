package cn.ntt.oa.test;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.ntt.oa.util.RandomNumUtil;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class TestAction extends ActionSupport {

	@Resource
	private TestService testService;

	@Override
	public String execute() throws Exception {
		System.out.println("---> TestAction.execute()");
		testService.saveTwoUsers();
		return "success";
	}
	
	public static void main(String[] args) {
		RandomNumUtil rdnu=RandomNumUtil.Instance(); 
		rdnu.getImage();//取得带有随机字符串的图片
		System.out.println(rdnu.getImage());
		System.out.println(rdnu.getString());
	}
	
}
