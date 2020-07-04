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

		defaultListableBeanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
		defaultListableBeanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
		String[] locations = {"classpath:/META-INF/bean-constructor-dependency-injection.xml", "classpath:/META-INF/dependency-lookup-context.xml"};
		xmlBeanDefinitionReader.loadBeanDefinitions(locations);

		UserHolder userHolder = defaultListableBeanFactory.getBean("userHolder", UserHolder.class);
		System.out.println(userHolder);
	}

}
