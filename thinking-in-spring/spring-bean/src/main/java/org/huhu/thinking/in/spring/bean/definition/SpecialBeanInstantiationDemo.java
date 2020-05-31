package org.huhu.thinking.in.spring.bean.definition;

import org.huhu.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.huhu.thinking.in.spring.bean.factory.UserFactory;
import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

import static java.util.ServiceLoader.load;

/**
 * 特殊 Bean 实例化示例
 *
 * @author huhu
 */
public class SpecialBeanInstantiationDemo {

	public static void main(String[] args) {

		// 配置XML配置文件, 启动Spring应用上个下文
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");

		// 通过 ApplicationContext 获取 AutowireCapableBeanFactory
		AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

		ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);

		displayServiceLoader(serviceLoader);

		demoServiceLoader();

		DefaultUserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
		System.out.println(userFactory.createUser());
	}

	private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
		for (UserFactory userFactory : serviceLoader) {
			User user = userFactory.createUser();
			System.out.println(user);
		}
	}

	private static void demoServiceLoader() {
		ServiceLoader<UserFactory> serviceLoader = load(UserFactory.class, Thread.currentThread().getContextClassLoader());
		displayServiceLoader(serviceLoader);
	}

}
