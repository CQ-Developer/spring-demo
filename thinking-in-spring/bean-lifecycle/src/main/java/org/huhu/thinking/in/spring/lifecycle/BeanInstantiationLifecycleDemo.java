package org.huhu.thinking.in.spring.lifecycle;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化生命周期示例
 *
 * @author HuHu
 */
public class BeanInstantiationLifecycleDemo {

	public static void main(String[] args) {
		executeBeanFactory();
		System.out.println("---------------------------");
		executeApplicationContext();
	}

	private static void executeBeanFactory() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 方法一: 添加 BeanPostProcessor 实例 MyInstantiationAwareBeanPostProcessor
		// beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

		// 方法二: 将 MyInstantiationAwareBeanPostProcessor 作为 Bean 注册

		// 基于 XML 资源的 BeanDefinition 实现
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		String[] locations = {"classpath:/META-INF/bean-constructor-dependency-injection.xml",
				"classpath:/META-INF/dependency-lookup-context.xml"};
		int beanDefinitionsCount = beanDefinitionReader.loadBeanDefinitions(locations);
		System.out.println("已加载的 BeanDefinition 数量: " + beanDefinitionsCount);

		User user = beanFactory.getBean("user", User.class);
		System.out.println("user: " + user);

		User superUser = beanFactory.getBean("superUser", User.class);
		System.out.println("superUser: " + superUser);

		// 构造器注入按照类型注入 resolveDependency
		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
		System.out.println("userHolder: " + userHolder);
	}

	private static void executeApplicationContext() {
		String[] locations = {"classpath:/META-INF/bean-constructor-dependency-injection.xml",
				"classpath:/META-INF/dependency-lookup-context.xml"};

		// 创建并启动 Spring 应用上下文
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(locations);

		// 添加 BeanPostProcessor 实例
		// beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

		User user = applicationContext.getBean("user", User.class);
		System.out.println("user: " + user);

		User superUser = applicationContext.getBean("superUser", User.class);
		System.out.println("superUser: " + superUser);

		// 构造器注入按照类型注入 resolveDependency
		UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
		System.out.println("userHolder: " + userHolder);

		// 关闭 Spring 应用上下文
		applicationContext.close();
	}


}
