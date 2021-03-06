package org.huhu.thinking.in.spring.ioc.dependency.injection;

import org.huhu.thinking.in.spring.ioc.dependency.injection.annotation.InjectedUser;
import org.huhu.thinking.in.spring.ioc.dependency.injection.annotation.MyAutwowired;
import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 注解驱动的依赖注入处理过程
 *
 * @author huhu
 */
@Configuration
public class AnnotationDependencyInjectionResolutionDemo {

	@Lazy
	@Autowired
	private User lazyUser;

	/**
	 * 1.实时注入
	 * 2.通过类型 User.class 依赖查找(处理)
	 * 3.字段名称 user
	 *
	 * required = true
	 * eager = true
	 * primary = true
	 *
	 * @see DependencyDescriptor
	 * @see DefaultListableBeanFactory#resolveDependency(DependencyDescriptor, String, Set, TypeConverter)
	 */
	@Autowired
	private User user;

	/**
	 * 集合类型依赖注入
	 * user
	 * superUser
	 */
	@Autowired
	private Map<String, User> mapUsers;

	@MyAutwowired
	private Optional<User> optionalUser;

	@Inject
	private User injectUser;

	@InjectedUser
	private User myInjectUser;

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

		System.out.println("user: " + demo.user);

		System.out.println("injectUser: " + demo.injectUser);

		System.out.println("mapUsers: " + demo.mapUsers);

		System.out.println("optionalUser: " + demo.optionalUser);

		System.out.println("lazyUser: " + demo.lazyUser);

		System.out.println("myInjectUser: " + demo.myInjectUser);

		// 关闭应用上下文
		applicationContext.close();

	}

	@Bean
	@Order(Ordered.LOWEST_PRECEDENCE - 3)
	public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
		AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();

		beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);

		return beanPostProcessor;
	}

	// @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
	// public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
	// 	AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
	//
	// 	// @Autowired @InjectUser @Inject
	// 	Set<Class<? extends Annotation>> autowiredAnnotationTypes =
	// 			new LinkedHashSet<>(asList(Autowired.class, Inject.class, InjectedUser.class));
	//
	// 	beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
	//
	// 	return beanPostProcessor;
	// }

}
