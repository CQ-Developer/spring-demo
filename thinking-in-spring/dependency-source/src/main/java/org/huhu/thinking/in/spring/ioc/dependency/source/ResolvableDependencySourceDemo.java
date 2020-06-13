package org.huhu.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency 作为依赖来源
 *
 * 只能用于依赖注入, 不能用于依赖查找, 并没有 Bean 的类型来供我们操作
 * 只能用于类型方面的依赖注入, 不能用于名称方面的依赖注入
 *
 * @author huhu
 */
public class ResolvableDependencySourceDemo {

	@Autowired
	private String value;

	@PostConstruct
	public void init() {
		System.out.println(value);
	}

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class 配置类 -> Spring Bean
		applicationContext.register(ResolvableDependencySourceDemo.class);

		// 回调
		applicationContext.addBeanFactoryPostProcessor(beanFactory -> beanFactory.registerResolvableDependency(String.class, "Hello.World"));

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 关闭 Spring 应用上下文
		applicationContext.close();

	}

}
