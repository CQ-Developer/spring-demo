package org.huhu.thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 基于 xml 资源的 yaml 外部化配置示例
 *
 * @author huhu
 */
public class XmlBasedYamlPropertySourceDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
		xmlBeanDefinitionReader.loadBeanDefinitions("classpath:META-INF/yml-property-source-context.xml");

		Map<String, Object> ymlMap = defaultListableBeanFactory.getBean("ymlMap", Map.class);
		System.out.println(ymlMap);
	}

}
