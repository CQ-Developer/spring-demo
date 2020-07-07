package org.huhu.thinking.in.spring.configuration.metadata;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Spring XML 元素扩展示例
 *
 * @author huhu
 */
public class ExtensibleXmlAuthoringDemo {

	public static void main(String[] args) {
		// 创建 IOC 底层容器
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

		// 创建 XML 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);

		// 加载 XML 资源
		xmlBeanDefinitionReader.loadBeanDefinitions("classpath:META-INF/users-context.xml");

		// 依赖查找
		User user = defaultListableBeanFactory.getBean(User.class);
		System.out.println(user);
	}

}
