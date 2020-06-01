package org.huhu.thinking.in.spring.bean.definition;

import org.huhu.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.huhu.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单例 Bean 注册示例
 *
 * @author huhu
 */
public class SingletonBeanRegistrationDemo {

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 创建一个外部对象
		UserFactory userFactory = new DefaultUserFactory();

		SingletonBeanRegistry beanFactory = applicationContext.getBeanFactory();

		// 注册 UserFactory 对象
		beanFactory.registerSingleton("userFactory", userFactory);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 依赖查找
		UserFactory userFactoryByLookup = applicationContext.getBean("userFactory", UserFactory.class);

		// 判断是否是原来的对象
		System.out.println("userFactory == userFactoryByLookup : " + (userFactory == userFactoryByLookup));

		// 关闭 Spring 应用上下文
		applicationContext.close();

	}

}
