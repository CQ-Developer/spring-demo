package org.huhu.thinking.in.spring.ioc.bean.scope.web;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.request.AbstractRequestAttributesScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Web MVC 配置类
 *
 * @author huhu
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {

	/**
	 * SeesionScope 由于有cookie的存在, 每次都会返回同一对象
	 * RequestScope 每次都会生成不同的对象
	 * {@link AbstractRequestAttributesScope#get(java.lang.String, org.springframework.beans.factory.ObjectFactory)}
	 *
	 * @return userBean
	 */
	// @RequestScope
	// @SessionScope
	@Bean
	@ApplicationScope
	public User user() {
		User user = new User();
		user.setId(1L);
		user.setName("呼呼");
		return user;
	}

}
