package org.huhu.thinking.in.spring.bean.definition;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 别名示例
 *
 * @author huhu
 */
public class BeanAliasDemo {

	public static void main(String[] args) {

		// 启动应用上下文
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");

		// 通过名称获取Bean
		User user = beanFactory.getBean("user", User.class);

		// 通过别名获取Bean
		User huhuUser = beanFactory.getBean("huhu-user", User.class);

		// 判断对象是否相等
		System.out.println("user和huhuUser是否相同: " + (user == huhuUser));

	}

}
