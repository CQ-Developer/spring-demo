package org.huhu.thinking.in.spring.ioc.overview.container;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * 注解能力 {@link org.springframework.context.ApplicationContext} 作为IOC容器
 *
 * @author huhu
 */
public class AnnotationApplicationContextAsIocContainerDemo {

	public static void main(String[] args) {
		// 创建BeanFactory容器
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

		// 将当前类AnnotationApplicationContextAsIocContainerDemo作为配置类(Configuration Class)
		annotationConfigApplicationContext.register(AnnotationApplicationContextAsIocContainerDemo.class);

		// 启动应用上下文
		annotationConfigApplicationContext.refresh();

		// 依赖查找集合对象
		lookupCollectionByType(annotationConfigApplicationContext);

		// 关闭应用上下文
		annotationConfigApplicationContext.close();
	}

	/**
	 * 通过Java注解方式定义了一个Bean
	 */
	@Bean
	public User user() {
		User user = new User();
		user.setId(1L);
		user.setName("呼呼");
		return user;
	}

	private static void lookupCollectionByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
			System.out.println("通过类型实时查找对象的集合: " + users);
		}
	}

}
