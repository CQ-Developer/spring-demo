package org.huhu.thinking.in.spring.lifecycle;

import org.huhu.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ObjectUtils;

/**
 * Bean 实例化生命周期示例
 *
 * @author HuHu
 */
public class BeanInstantiationLifecycleDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 添加 BeanPostProcessor 实例
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		Resource resource = new ClassPathResource("/META-INF/dependency-lookup-context.xml");
		int beanDefinitionsCount = beanDefinitionReader.loadBeanDefinitions(resource);

		System.out.println("已加载的 BeanDefinition 数量: " + beanDefinitionsCount);

		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

		User superUser = beanFactory.getBean("superUser", User.class);
		System.out.println(superUser);
	}

	private static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

		@Override
		public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

			// 把配置完成的 superUser Bean 覆盖
			if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
				SuperUser superUser = new SuperUser();
				superUser.setName("我在实例化前被修改过了");
				return superUser;
			}

			// 容器默认的实例化操作
			return null;
		}

	}

}
