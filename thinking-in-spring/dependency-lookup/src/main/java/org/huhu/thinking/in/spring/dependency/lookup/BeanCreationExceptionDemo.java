package org.huhu.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link BeanCreationException} 示例
 *
 * @author huhu
 */
public class BeanCreationExceptionDemo {

	public static void main(String[] args) {

		// 创建BeanFactory容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
		applicationContext.registerBeanDefinition("error", beanDefinitionBuilder.getBeanDefinition());

		// 启动应用上下文
		applicationContext.refresh();

		// 关闭应用上下文
		applicationContext.close();

	}

	static class POJO implements InitializingBean {

		// CommonAnnotationBeanPostProcessor
		@PostConstruct
		public void init() throws Throwable {
			throw new Throwable("init(): For purposes ...");
		}

		@Override
		public void afterPropertiesSet() throws Exception {
			throw new Exception("afterPropertiesSet(): For purposes ...");
		}

	}

}
