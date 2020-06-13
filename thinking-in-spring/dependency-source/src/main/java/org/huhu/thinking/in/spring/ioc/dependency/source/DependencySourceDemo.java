package org.huhu.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源示例
 *
 * {@link Autowired} 注入在 postProcessProperties 方法执行
 * 早于 setter 注入, 也早于 @PostConstruct
 *
 * @author huhu
 * @see org.springframework.context.support.AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory)
 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#findAutowireCandidates(String, Class, DependencyDescriptor)
 */
public class DependencySourceDemo {

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@PostConstruct
	public void initByInject() {
		System.out.println("beanFactory == applicationContext: " + (beanFactory == applicationContext));
		System.out.println("beanFactory == applicationContext.getAutowireCapableBeanFactory: " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
		System.out.println("resourceLoader == applicationContext: " + (resourceLoader == applicationContext));
		System.out.println("applicationEventPublisher == applicationContext: " + (applicationEventPublisher == applicationContext));
	}

	@PostConstruct
	public void initByLookup() {
		getBean(BeanFactory.class);
		getBean(ResourceLoader.class);
		getBean(ApplicationContext.class);
		getBean(ApplicationEventPublisher.class);
	}

	private <T> T getBean(Class<T> beanType) {
		try {
			return beanFactory.getBean(beanType);
		} catch (NoSuchBeanDefinitionException e) {
			System.err.println("当前类 " + beanType.getName() + " 无法在 BeanFactory 中找到 !");
		}
		return null;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.register(DependencySourceDemo.class);

		applicationContext.refresh();

		DependencySourceDemo demo = applicationContext.getBean(DependencySourceDemo.class);

		applicationContext.close();
	}

}
