package org.huhu.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 {@link Aware} 接口回调的依赖注入示例
 *
 * @author huhu
 */
public class AwareInterfaceDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware {

	private static BeanFactory beanFactory;

	private static ApplicationContext applicationContext;

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册配置类 Configuration Class -> Spring Bean
		context.register(AwareInterfaceDependencyInjectionDemo.class);

		// 启动应用上下文
		context.refresh();

		System.out.println(context.getBeanFactory() == beanFactory);
		System.out.println(context == applicationContext);

		// 关闭应用上下文
		context.close();
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
	}

}
