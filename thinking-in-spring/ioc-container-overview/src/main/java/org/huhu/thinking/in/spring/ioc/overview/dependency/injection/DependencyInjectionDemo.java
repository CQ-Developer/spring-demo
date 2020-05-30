package org.huhu.thinking.in.spring.ioc.overview.dependency.injection;

import org.huhu.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖查找
 *
 * 常见面试题:
 * {@link ObjectFactory}
 * {@link BeanFactory}
 * {@link org.springframework.beans.factory.FactoryBean}
 * 三者有什么区别
 *
 * 1.通过名称的方式查找
 * 2.通过类型的方式查找
 *
 * @author huhu
 */
public class DependencyInjectionDemo {

	public static void main(String[] args) {
		// 配置XML配置文件, 启动应用上下文
		// BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-injection-context.xml");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-injection-context.xml");

		// 依赖来源一: 自定义Bean
		UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);

		System.out.println(userRepository);

		// 依赖来源二: 依赖注入 (内建依赖)
		System.out.println(userRepository.getBeanFactory());

		whoIsIocContainer(userRepository, applicationContext);

		// 依赖注入
		// 如果BeanFactory是一个bean, 那么就可以通过beanFactory查找出来
		// System.out.println(beanFactory.getBean(BeanFactory.class));
		// 抛出异常NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.beans.factory.BeanFactory' available

		// true
		System.out.println(userRepository.getObjectFactory().getObject() == applicationContext);

		// 依赖来源三: 容器内建Bean
		Environment environment = applicationContext.getBean(Environment.class);
		System.out.println("获取Environment类型的Bean: " + environment);
	}

	private static void whoIsIocContainer(UserRepository userRepository, ApplicationContext applicationContext) {
		// 这个表达式为什么不成立
		System.out.println(userRepository.getBeanFactory() == applicationContext);

		// ConfigurableApplicationContext <- ApplicationContext <- BeanFactory
		// ConfigurableApplicationContext#getBeanFactory()

		// ApplicationContext is BeanFactory
	}

}
