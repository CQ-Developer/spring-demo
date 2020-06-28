package org.huhu.thinking.in.spring.lifecycle;

import org.huhu.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * 合并 BeanDefinition 示例
 *
 * 参考 {@link AbstractBeanFactory#getMergedBeanDefinition(java.lang.String)} 方法
 *
 * @author HuHu
 */
public class MergedBeanDefinitionDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 基于 XML 实现的 BeanDefinitionReader
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		Resource resource = new ClassPathResource("META-INF/dependency-lookup-context.xml");
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

		int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);
		System.out.println("已加载 BeanDefinition 数量: " + beanNumbers);

		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

		SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
		System.out.println(superUser);
	}

}
