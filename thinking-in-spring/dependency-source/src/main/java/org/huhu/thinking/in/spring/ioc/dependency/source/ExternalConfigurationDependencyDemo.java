package org.huhu.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置作为依赖来源
 *
 * @author huhu
 */
@Configuration
@PropertySource(value = "classpath:/META-INF/default.properties", encoding = "UTF-8")
public class ExternalConfigurationDependencyDemo {

	@Value("${user.id:-1}")
	private Long id;

	@Value("${usr.name}")
	private String name;

	@Value("${user.resource:test.properties}")
	private Resource resource;

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册配置类
		applicationContext.register(ExternalConfigurationDependencyDemo.class);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		ExternalConfigurationDependencyDemo demo = applicationContext.getBean(ExternalConfigurationDependencyDemo.class);

		System.out.println("id = " + demo.id);

		System.out.println("resource = " + demo.resource);

		System.out.println("name = " + demo.name);

		// 关闭 Spring 应用上下文
		applicationContext.close();

	}

}
