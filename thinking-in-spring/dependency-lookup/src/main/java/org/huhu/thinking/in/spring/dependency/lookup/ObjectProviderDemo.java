package org.huhu.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

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

		// 关闭应用上下文
		applicationContext.close();

	}

	/**
	 * 注意: 方法名就是 Bean 的名称
	 *
	 * @return 字符串常量
	 */
	@Bean
	public String helloWorld() {
		return "Hello World";
	}

	private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
		ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
		String value = beanProvider.getObject();
		System.out.println(value);
	}

}
