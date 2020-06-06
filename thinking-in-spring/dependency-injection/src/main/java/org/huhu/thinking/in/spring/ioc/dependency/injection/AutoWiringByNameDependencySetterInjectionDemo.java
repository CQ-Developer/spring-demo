package org.huhu.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * "byName" Autowiring 依赖 Setter 方法注入示例
 *
 * @author huhu
 */
public class AutoWiringByNameDependencySetterInjectionDemo {

	public static void main(String[] args) {

		// 创建 BeanFactory 对象
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 加载 XML 配置文件
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/autowiring-dependency-setter-injection.xml");

		// 依赖查找
		UserHolder userHolder = beanFactory.getBean(UserHolder.class);

		System.out.println(userHolder);
	}

}
