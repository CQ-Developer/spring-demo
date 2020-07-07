package org.huhu.thinking.in.spring.configuration.metadata;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * 基于 JAVA 注解的 Spring IOC 容器元信息配置示例
 *
 * @author huhu
 */
@ImportResource("classpath:META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:META-INF/users-bean-definitions.properties")
public class AnnotatedSpringIocContainerMetadataDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

		// 将当前类 AnnotatedSpringIocContainerMetadataDemo 作为 ConfigurationClass 注册
		annotationConfigApplicationContext.register(AnnotatedSpringIocContainerMetadataDemo.class);

		// 启动 Spring 应用上下文
		annotationConfigApplicationContext.refresh();

		System.out.println("已加载的beanDefinition数量: " + annotationConfigApplicationContext.getBeanDefinitionCount());

		Map<String, User> usersBeanMap = annotationConfigApplicationContext.getBeansOfType(User.class);
		usersBeanMap.forEach((k, v) -> System.out.println("===>>> 当前beanName: " + k + ", 当前beanInstance: " + v));

		// 关闭 Spring 应用上下文
		annotationConfigApplicationContext.close();
	}

	@Bean
	public User configuredUser(@Value("${user.name}") String name, @Value("${user.id}") long id) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;
	}

}
