package org.huhu.thinking.in.spring.lifecycle;

import org.huhu.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
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

		String[] locations = {"classpath:/META-INF/bean-constructor-dependency-injection.xml",
				"classpath:/META-INF/dependency-lookup-context.xml"};
		int beanDefinitionsCount = beanDefinitionReader.loadBeanDefinitions(locations);

		System.out.println("已加载的 BeanDefinition 数量: " + beanDefinitionsCount);

		User user = beanFactory.getBean("user", User.class);
		System.out.println("user: " + user);

		User superUser = beanFactory.getBean("superUser", User.class);
		System.out.println("superUser: " + superUser);

		// 构造器注入按照类型注入 resolveDependency
		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
		System.out.println("userHolder: " + userHolder);
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

		@Override
		public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
				// "user" 对象不允许属性赋值 (配置元信息 -> 属性值)
				User user = (User) bean;
				user.setId(110L);
				user.setName("呼呼警官");
				return false;
			}
			return true;
		}

		/**
		 * user 是跳过 Bean 属性赋值 (填入)
		 * superUser 是完全跳过 Bean 实例化 (Bean 属性赋值(填入))
		 *
		 * userHolder
		 * 假设 <property name="number" value="1"/> 在 XML 文件中配置的话
		 * 那么在 PropertyValues 中就包含一个 PropertyValues(number=1)
		 *
		 * 原始配置 <property name="description" value="The user Holder"/>
		 */
		@Override
		public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
				final MutablePropertyValues propertyValues;

				if (pvs instanceof MutablePropertyValues) {
					propertyValues = (MutablePropertyValues) pvs;
				} else {
					propertyValues = new MutablePropertyValues();
				}

				propertyValues.addPropertyValue("number", "1");

				if (propertyValues.contains("description")) {
					propertyValues.removePropertyValue("description");
					propertyValues.addPropertyValue("description", "My user holder");
				}

				return propertyValues;
			}
			return null;
		}

	}

}
