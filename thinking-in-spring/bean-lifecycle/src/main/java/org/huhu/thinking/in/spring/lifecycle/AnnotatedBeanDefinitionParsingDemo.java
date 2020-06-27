package org.huhu.thinking.in.spring.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解 BeanDefinition 解析示例
 *
 * @author HuHu
 */
public class AnnotatedBeanDefinitionParsingDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 基于 Java 注解的 AnnotatedBeanDefinitionReader 的实现, 并没有实现 BeanDefinitionReader 接口
		AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

		int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();

		beanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);

		int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();

		System.out.println("已加载 BeanDefinition 数量: " + (beanDefinitionCountAfter - beanDefinitionCountBefore));

		// 普通 Class 作为 Component 注册到 Spring IOC 容器后, 通常 Bean 的名称为首字母小写
		// Bean 名称的生成来自于 BeanNameGenerator, 注解实现 AnnotationBeanNameGenerator
		AnnotatedBeanDefinitionParsingDemo bean = beanFactory.getBean("annotatedBeanDefinitionParsingDemo", AnnotatedBeanDefinitionParsingDemo.class);

		System.out.println(bean);
	}

}
