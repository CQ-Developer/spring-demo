package org.huhu.thinking.in.spring.lifecycle;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean 元信息配置示例
 *
 * @author HuHu
 */
public class BeanMetadataConfigurationDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 实例化基于 Properties 资源 BeanDefinitionReader
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

		// 基于 ClassPath 加载 properties 资源
		Resource classPathResource = new ClassPathResource("META-INF/user.properties");

		// 指定字符集编码 UTF-8
		EncodedResource encodedResource = new EncodedResource(classPathResource, "UTF-8");

		// 加载 properties 资源
		int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);

		System.out.println("已加载的 beanDefinition 数量: " + beanNumbers);

		// 通过 Bean ID 和类型进行依赖查找
		User user = beanFactory.getBean("user", User.class);

		System.out.println(user);
	}

}
