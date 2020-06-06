package org.huhu.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于XML资源依赖Setter方法注入
 *
 * @author huhu
 */
public class XmlDependencySetterInjectionDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);

		String xmlResourcePath = "classpath:/META-INF/dependency-setter-injection.xml";

		// 加载XML资源,解析并生成BeanDefinition
		definitionReader.loadBeanDefinitions(xmlResourcePath);

		UserHolder userHolder = beanFactory.getBean(UserHolder.class);

		System.out.println(userHolder);
	}

}
