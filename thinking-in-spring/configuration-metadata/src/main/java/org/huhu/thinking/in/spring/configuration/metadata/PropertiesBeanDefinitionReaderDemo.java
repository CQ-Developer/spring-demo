package org.huhu.thinking.in.spring.configuration.metadata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/**
 * {@link PropertiesBeanDefinitionReader} 示例
 *
 * @author huhu
 */
public class PropertiesBeanDefinitionReaderDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

		PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(defaultListableBeanFactory);

		// Properties 资源加载默认是通过 ISO-8859-1, 实际存储是 UTF-8
		EncodedResource encodedResource = new EncodedResource(new ClassPathResource("META-INF/users-bean-definitions.properties"), "UTF-8");

		/*
		 * 另一种实现方式
		 * Resource resource = new DefaultResourceLoader().getResource("");
		 * EncodedResource encodedResource = new EncodedResource(resource);
		 */

		int beanDefinitionsCount = propertiesBeanDefinitionReader.loadBeanDefinitions(encodedResource);
		System.out.println("已加载 BeanDefinition 数量: " + beanDefinitionsCount);

		User user = defaultListableBeanFactory.getBean("user", User.class);
		System.out.println(JSON.toJSONString(user, SerializerFeature.PrettyFormat));
	}

}
