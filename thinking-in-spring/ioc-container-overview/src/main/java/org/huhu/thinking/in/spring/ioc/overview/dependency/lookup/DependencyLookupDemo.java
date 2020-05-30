package org.huhu.thinking.in.spring.ioc.overview.dependency.lookup;

import org.huhu.thinking.in.spring.ioc.overview.annotation.Super;
import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找
 *
 * 常见面试题:
 * {@link org.springframework.beans.factory.ObjectFactory}
 * {@link org.springframework.beans.factory.BeanFactory}
 * {@link org.springframework.beans.factory.FactoryBean}
 * 三者有什么区别
 *
 * 1.通过名称的方式查找
 * 2.通过类型的方式查找
 *
 * @author huhu
 */
public class DependencyLookupDemo {

	public static void main(String[] args) {
		// 配置XML配置文件, 启动应用上下文
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");

		// 通过名称实时查找
		lookupInRealTime(beanFactory);

		// 通过名称延迟查找
		lookupInLazy(beanFactory);

		// 通过类型实时查找
		lookuByType(beanFactory);

		// 通过类型实时查找对象的集合
		lookupCollectionByType(beanFactory);

		// 通过注解实时查找
		lookupByAnnotation(beanFactory);
	}

	private static void lookupByAnnotation(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, Object> users = listableBeanFactory.getBeansWithAnnotation(Super.class);
			System.out.println("通过注解实时查找对象的集合: " + users);
		}
	}

	private static void lookupCollectionByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
			System.out.println("通过类型实时查找对象的集合: " + users);
		}
	}

	private static void lookuByType(BeanFactory beanFactory) {
		User user = beanFactory.getBean(User.class);
		System.out.println("通过类型实时查找: " + user);
	}

	/**
	 * ObjectFactory并没有生成新的bean
	 * 而使用BeanFactory就不一样了
	 * 则是它们两者的重大区别一个
	 */
	private static void lookupInLazy(BeanFactory beanFactory) {
		ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
		User user = objectFactory.getObject();
		System.out.println("通过名称延迟查找: " + user);
	}

	private static void lookupInRealTime(BeanFactory beanFactory) {
		User user = (User) beanFactory.getBean("user");
		System.out.println("通过名称实时查找: " + user);
	}

}
