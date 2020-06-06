package org.huhu.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link NoUniqueBeanDefinitionException} 示例代码
 *
 * @author huhu
 */
public class NoUniqueBeanDefinitionExceptionDemo {

	public static void main(String[] args) {

		// 创建BeanFactory容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 将当前类作为配置类注册到容器中
		applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);

		// 启动应用上下文
		applicationContext.refresh();

		try {
			// 由于Spring应用上下文存在两个String类型的Bean,通过单一查找会抛出异常
			applicationContext.getBean(String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 关闭应用上下文
		applicationContext.close();

	}

	@Bean
	public String bean1() {
		return "bean1";
	}

	@Bean
	public String bean2() {
		return "bean2";
	}

}
