package org.huhu.thinking.in.spring.configuration.metadata;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于 Properties 资源的外部化配置
 *
 * @author huhu
 */
@PropertySource("classpath:META-INF/users-bean-definitions.properties")
public class PropertySourceDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

		// 基于 API 添加外部化配置
		Map<String, Object> source = new HashMap<>();
		source.put("user.name", "chenqiang");
		MapPropertySource mapPropertySource = new MapPropertySource("first-property-source", source);

		// 扩展 Environment 中的 PropertySources, 该操作必须在 refresh 方法之前完成
		MutablePropertySources mutablePropertySources = annotationConfigApplicationContext.getEnvironment().getPropertySources();
		mutablePropertySources.addFirst(mapPropertySource);

		annotationConfigApplicationContext.register(PropertySourceDemo.class);

		annotationConfigApplicationContext.refresh();

		mutablePropertySources.stream().forEach(System.out::println);

		Map<String, User> userBeanMap = annotationConfigApplicationContext.getBeansOfType(User.class);
		userBeanMap.forEach((k, v) -> System.out.println("当前Bean的名称: " + k + ", 值: ===>>> " + v));

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
