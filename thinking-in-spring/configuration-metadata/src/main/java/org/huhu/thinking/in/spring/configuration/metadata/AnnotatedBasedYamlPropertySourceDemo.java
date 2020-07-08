package org.huhu.thinking.in.spring.configuration.metadata;

import com.alibaba.fastjson.JSON;
import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MutablePropertySources;

/**
 * 基于注解的 yaml 外部化配置示例
 *
 * @author huhu
 * @see YamlPropertySourceFactory
 */
@PropertySource(name = "yamlPropertySource", value = "classpath:/META-INF/user.yaml", factory = YamlPropertySourceFactory.class)
public class AnnotatedBasedYamlPropertySourceDemo {

	public static void main(String[] args) {
		// 创建 Spring 应用上下文
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

		// 将当前类作为 Configuration Class 注册到 IOC 容器中
		annotationConfigApplicationContext.register(AnnotatedBasedYamlPropertySourceDemo.class);

		// 启动 Spring 应用上下文
		annotationConfigApplicationContext.refresh();

		// 查看当前 IOC 容器的资源
		MutablePropertySources mutablePropertySources = annotationConfigApplicationContext.getEnvironment().getPropertySources();
		mutablePropertySources.stream().forEach(e -> {
			String name = e.getName();
			Object source = e.getSource();
			System.out.println("资源名: " + name + ", ===>>> " + JSON.toJSONString(source));
		});

		// 依赖查找
		User user = annotationConfigApplicationContext.getBean("user", User.class);
		System.out.println("通过依赖查找user对象: " + user);

		// 关闭 Spring 应用上下文
		annotationConfigApplicationContext.close();
	}

	@Bean
	public User user(@Value("${user.id}") long id, @Value("${user.name}") String name) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;
	}

}
