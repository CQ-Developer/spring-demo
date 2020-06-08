package org.huhu.thinking.in.spring.ioc.dependency.injection;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 注解驱动的依赖注入处理过程
 *
 * @author huhu
 */
public class AnnotationDependencyInjectionResolutionDemo {

	@Lazy
	@Autowired
	private User lazyUser;

	/**
	 * 实时注入
	 * 通过类型 User.class 依赖查找(处理)
	 * 字段名称 user
	 * <p>
	 * required = true
	 * eager = true
	 * primary = true
	 *
	 * @see DependencyDescriptor
	 * @see DefaultListableBeanFactory#resolveDependency(DependencyDescriptor, String, Set, TypeConverter)
	 */
	@Autowired
	private User user;

	@Autowired
	private Map<String, User> mapUsers;

	@Autowired
	private Optional<User> optionalUser;

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册当前类为配置类
		applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

		// XML 配置解析器
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

		// XML 配置文件路径
		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

		// 加载 XML 文件
		beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

		// 启动应用上下文
		applicationContext.refresh();

		// 依赖查找当前类
		AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

		System.out.println("user: " + demo.getUser());

		System.out.println("mapUsers: " + demo.getMapUsers());

		System.out.println("optionalUser: " + demo.getOptionalUser().get());

		System.out.println("lazyUser: " + demo.getLazyUser());

		// 关闭应用上下文
		applicationContext.close();

	}

	public User getUser() {
		return user;
	}

	public User getLazyUser() {
		return lazyUser;
	}

	public Map<String, User> getMapUsers() {
		return mapUsers;
	}

	public Optional<User> getOptionalUser() {
		return optionalUser;
	}

}
