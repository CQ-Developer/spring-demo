package org.huhu.thinking.in.spring.bean.definition;

import org.huhu.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.huhu.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean 初始化实例
 *
 * @author huhu
 */
@Configuration
public class BeanInitializationDemo {

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class 配置类
		applicationContext.register(BeanInitializationDemo.class);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 依赖查找
		UserFactory userFactory = applicationContext.getBean(UserFactory.class);

		// 关闭 Spring 应用上下文
		applicationContext.close();

	}

	@Bean(initMethod = "initUserFactory")
	public UserFactory userFactory() {
		return new DefaultUserFactory();
	}

}
