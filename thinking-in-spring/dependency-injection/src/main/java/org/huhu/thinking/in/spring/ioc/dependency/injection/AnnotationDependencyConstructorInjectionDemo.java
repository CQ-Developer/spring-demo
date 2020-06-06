package org.huhu.thinking.in.spring.ioc.dependency.injection;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于注解的 Constructor 依赖注入示例
 */
public class AnnotationDependencyConstructorInjectionDemo {

	public static void main(String[] args) {

		// 创建BeanFactory容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册当前类为配置类
		applicationContext.register(AnnotationDependencyConstructorInjectionDemo.class);

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

		beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

		// 启动Spring应用上下文
		applicationContext.refresh();

		// 依赖查找并创建Bean
		UserHolder userHolder = applicationContext.getBean(UserHolder.class);
		System.out.println(userHolder);

		// 关闭Spring应用上下文
		applicationContext.close();

	}

	@Bean
	public UserHolder userHolder(User user) {
		return new UserHolder(user);
	}

}
