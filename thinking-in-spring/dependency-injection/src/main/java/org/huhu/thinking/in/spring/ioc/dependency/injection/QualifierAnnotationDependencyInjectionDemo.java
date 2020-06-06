package org.huhu.thinking.in.spring.ioc.dependency.injection;

import org.huhu.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * {@link Qualifier} 注解依赖注入
 *
 * @author huhu
 */
public class QualifierAnnotationDependencyInjectionDemo {

	/**
	 * 默认情况下注入 SuperUser -> Primary
	 */
	@Autowired
	private User user;

	/**
	 * 通过
	 * {@link Qualifier}
	 * 指定 Bean 的名称或 ID 进行注入
	 */
	@Autowired
	@Qualifier("user")
	private User namedUser;

	/*
	 * 应用上下文整体存在4个 User 类型的 Bean
	 * user
	 * superUser
	 * user1
	 * user2
	 */

	/**
	 * user
	 * superUser
	 */
	@Autowired
	private Collection<User> allUsers;

	/**
	 * user1
	 * user2
	 * user3
	 * user4
	 */
	@Autowired
	@Qualifier
	private Collection<User> qualifierUsers;

	/**
	 * user3
	 * user4
	 */
	@Autowired
	@UserGroup
	private Collection<User> groupedUsers;

	/**
	 * 通过 {@link Qualifier} 注解
	 * 进行逻辑分组
	 */
	@Bean
	@Qualifier
	public User user1() {
		return createUser(7L);
	}

	@Bean
	@Qualifier
	public User user2() {
		return createUser(8L);
	}

	@Bean
	@UserGroup
	public User user3() {
		return createUser(9L);
	}

	@Bean
	@UserGroup
	public User user4() {
		return createUser(10L);
	}

	private User createUser(long id) {
		User user = new User();
		user.setId(id);
		return user;
	}

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册当前类为配置类
		applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

		// XML 配置解析器
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

		// XML 配置文件路径
		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

		// 加载 XML 文件
		beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

		// 启动应用上下文
		applicationContext.refresh();

		// 依赖查找当前类
		QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

		System.out.println(demo.getUser());
		System.out.println(demo.getNamedUser());
		System.out.println(demo.getAllUsers());
		System.out.println(demo.getQualifierUsers());
		System.out.println(demo.getGroupedUsers());

		// 关闭应用上下文
		applicationContext.close();

	}

	public User getUser() {
		return user;
	}

	public User getNamedUser() {
		return namedUser;
	}

	public Collection<User> getAllUsers() {
		return allUsers;
	}

	public Collection<User> getQualifierUsers() {
		return qualifierUsers;
	}

	public Collection<User> getGroupedUsers() {
		return groupedUsers;
	}
}
