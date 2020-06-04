package org.huhu.thinking.in.spring.dependency.lookup;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全 依赖查找示例
 *
 * @author huhu
 */
public class TypeSafetyDependencyLookupDemo {

	public static void main(String[] args) {

		// 创建BeanFactory容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 将当前类作为配置类注册到容器中
		applicationContext.register(TypeSafetyDependencyLookupDemo.class);

		// 启动应用上下文
		applicationContext.refresh();

		// 演示BeanFactory#getBean方法的安全性
		displayBeanFactoryGetBean(applicationContext);

		// 演示ObjectFactory#getObject方法的安全性
		displayObjectFactoryGetObject(applicationContext);

		// 演示ObjectProvider#getIfAvailable方法的安全性
		displayObjectProviderGetIfAvailable(applicationContext);

		// 演示ListableBeanFactory#getBeansOfTye方法的安全性
		displayListableBeanFactoryGetBeansOfType(applicationContext);

		// 演示ObjectProvider#stream方法的安全性
		displayObjectProviderStreamOpt(applicationContext);

		// 关闭应用上下文
		applicationContext.close();

	}

	private static void displayObjectProviderStreamOpt(BeanFactory beanFactory) {
		printBeansException("objectProviderStream", () -> beanFactory.getBeanProvider(User.class).stream().forEach(System.out::println));
	}

	private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory listableBeanFactory) {
		printBeansException("ListableBeanFactory", () -> listableBeanFactory.getBeansOfType(User.class));
	}

	private static void displayObjectProviderGetIfAvailable(BeanFactory beanFactory) {
		printBeansException("ObjectProvider", () -> beanFactory.getBeanProvider(User.class).getIfAvailable());
	}

	private static void displayObjectFactoryGetObject(BeanFactory beanFactory) {
		printBeansException("ObjectFactory", () -> beanFactory.getBeanProvider(User.class).getObject());
	}

	private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
		printBeansException("BeanFactory", () -> beanFactory.getBean(User.class));
	}

	private static void printBeansException(String source, Runnable runnable) {
		try {
			System.err.println("=============================================");
			System.err.println("From: " + source);
			runnable.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
