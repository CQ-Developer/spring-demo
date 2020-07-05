package org.huhu.thinking.in.spring.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Bean 初始化声明周期示例
 *
 * @author HuHu
 */
public class BeanInitializationLifecycleDemo {

	public static void main(String[] args) {
		beanFactoryExecutor();
	}

	private static void beanFactoryExecutor() {
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

		defaultListableBeanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
		defaultListableBeanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
		String[] locations = {"classpath:/META-INF/bean-constructor-dependency-injection.xml", "classpath:/META-INF/dependency-lookup-context.xml"};
		xmlBeanDefinitionReader.loadBeanDefinitions(locations);

		// SmartInitializingSingleton 通常在 Spring ApplicationContext 场景中使用
		// preInstantiateSingletons 将已注册的 BeanDefinition 初始化为 Spring Bean
		defaultListableBeanFactory.preInstantiateSingletons();

		UserHolder userHolder = defaultListableBeanFactory.getBean("userHolder", UserHolder.class);
		System.out.println(userHolder);
	}

}
