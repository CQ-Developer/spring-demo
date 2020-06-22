package org.huhu.thinking.in.spring.ioc.bean.scope.web.controller;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页 Spring Web MVC Controller
 *
 * @author HuHu
 */
@Controller
public class IndexController {

	/**
	 * CGLIB代理对象 (不变的)
	 */
	@Autowired
	private User user;


	@GetMapping("/index.html")
	public String index(Model model) {
		// JSP EL 变量搜索路径 page -> request -> session -> application(ServletContext)
		// userObject -> 渲染上下文
		// user 对象存在 ServletContext 上下文名称: scopedTarget.user == 新生成的 Bean 的名称
		model.addAttribute("userObject", user);
		return "index";
	}

}
