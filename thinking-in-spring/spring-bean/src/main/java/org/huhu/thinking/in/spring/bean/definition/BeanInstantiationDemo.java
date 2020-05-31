package org.huhu.thinking.in.spring.bean.definition;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化示例
 *
 * @author huhu
 */
public class BeanInstantiationDemo {

	public static void main(String[] args) {

		// 配置XML配置文件, 启动Spring应用上个下文
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");

		User userByStaticMethod = beanFactory.getBean("user-by-static-method", User.class);
		User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
		User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);

		System.out.println(userByStaticMethod);
		System.out.println(userByInstanceMethod);
		System.out.println(userByFactoryBean);

		System.out.println(userByStaticMethod == userByInstanceMethod);
		System.out.println(userByStaticMethod == userByFactoryBean);
	}

}
