package org.huhu.thinking.in.spring.bean.definition;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建示例
 *
 * @author huhu
 */
public class BeanDefinitionCreationDemo {

	public static void main(String[] args) {

		// 1.通过BeanDefinitionBuilder构建
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);

		// 通过属性设置
		beanDefinitionBuilder.addPropertyValue("id", 18).addPropertyValue("name", "呼呼");

		// 获取BeanDefinition实例
		AbstractBeanDefinition abstractBeanDefinition = beanDefinitionBuilder.getBeanDefinition();
		// BeanDefinition并非Bean的终态, 可以自定义修改


		// 2.通过AbstractBeanDefinition以及派生类构建
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();

		// 设置Bean的类型
		genericBeanDefinition.setBeanClass(User.class);

		// 通过MutablePropertyValues批量操作属性
		MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
		mutablePropertyValues.add("id", 18).add("name", "呼呼");

		// 通过set MutablePropertyValues批量属性操作
		genericBeanDefinition.setPropertyValues(mutablePropertyValues);

	}

}
