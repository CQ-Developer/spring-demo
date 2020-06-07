package org.huhu.thinking.in.spring.ioc.dependency.injection;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

/**
 * {@link ObjectProvider} 实现延迟注入
 *
 * @author huhu
 */
public class LazyAnnotationDependencyInjectionDemo {

	/** 实时注入 */
	@Autowired
	private User user;

	/** 延迟注入 */
	@Autowired
	private ObjectProvider<User> userObjectProvider;

	@Autowired
	private ObjectFactory<Collection<User>> userObjectFactory;

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册当前类为配置类
		applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

		// XML 配置解析器
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

		// XML 配置文件路径
		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

		// 加载 XML 文件
		beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

		// 启动应用上下文
		applicationContext.refresh();

		// 依赖查找当前类
		LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

		System.out.println("user: " + demo.getUser());

		ObjectProvider<User> userObjectProvider = demo.getUserObjectProvider();
		System.out.println("userObjectProvider: " + userObjectProvider.getIfAvailable());
		userObjectProvider.forEach(System.out::println);

		ObjectFactory<Collection<User>> userObjectFactory = demo.getUserObjectFactory();
		System.out.println("userObjectFactory: " + userObjectFactory.getObject());
		userObjectFactory.getObject().forEach(System.out::println);

		// 关闭应用上下文
		applicationContext.close();

	}

	public User getUser() {
		return user;
	}

	public ObjectProvider<User> getUserObjectProvider() {
		return userObjectProvider;
	}

	public ObjectFactory<Collection<User>> getUserObjectFactory() {
		return userObjectFactory;
	}

}
