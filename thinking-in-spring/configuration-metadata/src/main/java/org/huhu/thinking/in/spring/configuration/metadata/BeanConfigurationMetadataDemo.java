package org.huhu.thinking.in.spring.configuration.metadata;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * Bean 配置元信息示例
 *
 * @author HuHu
 */
public class BeanConfigurationMetadataDemo {

	public static void main(String[] args) {
		// BeanDefinition 定义
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);

		// 为 Bean 的属性赋值
		beanDefinitionBuilder.addPropertyValue("name", "呼呼");

		// 获取 BeanDefinition
		AbstractBeanDefinition abstractBeanDefinition = beanDefinitionBuilder.getBeanDefinition();

		// 附加属性, 不影响 Bean 的生成 (实例化,属性赋值,初始化) -> (populate,Initialize)
		abstractBeanDefinition.setAttribute("name", "HuHu");

		// 当前 BeanDefinition 来自于何方 (辅助作用)
		abstractBeanDefinition.setSource(BeanConfigurationMetadataDemo.class);

		// BeanFactory 容器定义, 注册 BeanDefinition
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

		defaultListableBeanFactory.addBeanPostProcessor(new BeanPostProcessor() {

			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
				if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
					BeanDefinition beanDefinition = defaultListableBeanFactory.getBeanDefinition(beanName);
					if (BeanConfigurationMetadataDemo.class.equals(beanDefinition.getSource())) {
						// 属性存储上下文
						String name = (String) beanDefinition.getAttribute("name");
						User user = (User) bean;
						user.setName(name);
					}
				}
				return bean;
			}

		});

		// 注册 User 的 BeanDefinition
		defaultListableBeanFactory.registerBeanDefinition("user", abstractBeanDefinition);

		// 依赖查找
		User user = defaultListableBeanFactory.getBean("user", User.class);
		System.out.println(user);
	}

}
