package org.huhu.thinking.in.spring.dependency.lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link BeanInstantiationException} 示例
 *
 * @author huhu
 */
public class BeanInstantiationExceptionDemo {

	public static void main(String[] args) {

		// 创建BeanFactory容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册BeanDefinition Bean Class是一个CharSequence接口
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
		applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

		// 启动应用上下文
		applicationContext.refresh();

		// 关闭应用上下文
		applicationContext.close();

	}

}
