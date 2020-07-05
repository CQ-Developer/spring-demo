package org.huhu.thinking.in.spring.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Bean 生命周期示例
 *
 * @author HuHu
 */
public class BeanLifecycleDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

		defaultListableBeanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
		defaultListableBeanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
		defaultListableBeanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
		String[] locations = {"classpath:/META-INF/bean-constructor-dependency-injection.xml", "classpath:/META-INF/dependency-lookup-context.xml"};
		xmlBeanDefinitionReader.loadBeanDefinitions(locations);

		defaultListableBeanFactory.preInstantiateSingletons();

		UserHolder userHolder = defaultListableBeanFactory.getBean("userHolder", UserHolder.class);
		System.out.println(userHolder);

		// 执行 Bean 的销毁, Bean 销毁并不意味着 Bean 垃圾回收
		defaultListableBeanFactory.destroyBean("userHolder", userHolder);
		System.out.println("销毁后的Bean: " + userHolder);
	}

}
