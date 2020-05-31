package org.huhu.thinking.in.spring.ioc.overview.container;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * {@link BeanFactory} 作为IOC容器
 *
 * @author huhu
 */
public class BeanFactoryAsIocContainerDemo {

	public static void main(String[] args) {
		// 创建BeanFactory容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 读取XML配置文件
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		String location = "classpath:/META-INF/dependency-lookup-context.xml";
		int beanDefinitionsCount = xmlBeanDefinitionReader.loadBeanDefinitions(location);

		System.out.println("Bean定义加载数量: " + beanDefinitionsCount);

		// 依赖查找集合对象
		lookupCollectionByType(beanFactory);
	}

	private static void lookupCollectionByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
			System.out.println("通过类型实时查找对象的集合: " + users);
		}
	}

}
