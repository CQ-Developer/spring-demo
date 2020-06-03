package org.huhu.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找示例
 * {@link BeanFactoryUtils#beansOfTypeIncludingAncestors(org.springframework.beans.factory.ListableBeanFactory, java.lang.Class)}
 *
 * @author huhu
 */
public class HierarchicalDependencyLookupDemo {

	public static void main(String[] args) {

		// 创建BeanFactory容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 将当前类作为配置类注册
		applicationContext.register(HierarchicalDependencyLookupDemo.class);

		// 1.获取 HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
		ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
		System.out.println("当前BeanFactory的Parent BeanFactory: " + beanFactory.getParentBeanFactory());

		// 设置Parent BeanFactory
		ConfigurableListableBeanFactory parentBeanFactory = createParentBeanFactory();
		beanFactory.setParentBeanFactory(parentBeanFactory);
		System.out.println("当前BeanFactory的Parent BeanFactory: " + beanFactory.getParentBeanFactory());

		displayContainsLocalBean(beanFactory, "user");
		displayContainsLocalBean(parentBeanFactory, "user");

		displayContainsBean(beanFactory, "user");
		displayContainsBean(parentBeanFactory, "user");

		// 启动应用上下文
		applicationContext.refresh();

		// 关闭应用上下文
		applicationContext.close();

	}

	// 双亲委派机制
	private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
		boolean b = containsBean(beanFactory, beanName);
		System.out.printf("当前BeanFactory[%s], 是否包含Bean[name:%s]: %s\n", beanFactory, beanName, b);
	}

	// 递归调用
	private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
		BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
		if (parentBeanFactory instanceof HierarchicalBeanFactory) {
			if (containsBean((HierarchicalBeanFactory) parentBeanFactory, beanName)) return true;
		}
		return beanFactory.containsLocalBean(beanName);
	}

	private static void displayContainsLocalBean(ConfigurableListableBeanFactory beanFactory, String beanName) {
		boolean b = beanFactory.containsLocalBean(beanName);
		System.out.printf("当前BeanFactory[%s], 是否包含Bean[name:%s]: %s\n", beanFactory, beanName, b);
	}

	private static ConfigurableListableBeanFactory createParentBeanFactory() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
		String location = "classpath:/META-INF/dependency-lookup-context.xml";
		definitionReader.loadBeanDefinitions(location);
		return beanFactory;
	}

}
