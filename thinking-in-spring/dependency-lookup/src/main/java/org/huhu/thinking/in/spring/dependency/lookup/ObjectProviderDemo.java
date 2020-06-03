package org.huhu.thinking.in.spring.dependency.lookup;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过 {@link ObjectProvider} 进行依赖查找
 *
 * <p>虽然当前类被当作配置类注册到Spring容器中,
 * 但是 {@link org.springframework.context.annotation.Configuration} 注解也是非必须的,
 * 这点有别于SpringBoot</p>
 *
 * @author huhu
 */
public class ObjectProviderDemo {

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 将当前类作为配置类
		applicationContext.register(ObjectProviderDemo.class);

		// 启动应用上下文
		applicationContext.refresh();

		lookupByObjectProvider(applicationContext);

		// 当上下文中不存在, 给出一个默认值
		lookupIfAvailable(applicationContext);

		// 找出上下文中指定类型的说有Bean
		lookupByStreamOps(applicationContext);

		// 关闭应用上下文
		applicationContext.close();

	}

	private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
		ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
		objectProvider.stream().forEach(System.out::println);
	}

	private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
		ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
		User user = objectProvider.getIfAvailable(User::createUser);
		System.out.println(user);
	}

	/**
	 * 注意: 方法名就是 Bean 的名称
	 *
	 * @return 字符串常量
	 */
	@Bean
	@Primary
	public String helloWorld() {
		return "Hello World";
	}

	@Bean
	public String message() {
		return "message";
	}

	private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
		ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
		String value = beanProvider.getObject();
		System.out.println(value);
	}

}
