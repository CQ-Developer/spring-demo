package org.huhu.thinking.in.spring.ioc.bean.scope.web;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Web MVC 配置类
 *
 * @author huhu
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {

	@Bean
	@RequestScope
	public User user() {
		User user = new User();
		user.setId(1L);
		user.setName("呼呼");
		return user;
	}

}
