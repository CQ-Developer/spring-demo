package org.huhu.thinking.in.spring.ioc.dependency.injection;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于注解的 Method 依赖注入示例
 *
 * @author huhu
 */
public class AnnotationDependencyMethodInjectionDemo {

	private UserHolder userHolder1;

	private UserHolder userHolder2;

	@Autowired
	private void initUserHolder1(UserHolder userHolder) {
		this.userHolder1 = userHolder;
	}

	@Resource
	private void initUserHodler2(UserHolder userHolder) {
		this.userHolder2 = userHolder;
	}

	@Bean
	public UserHolder userHolder(User user) {
		return new UserHolder(user);
	}

	public static void main(String[] args) {

		// 创建BeanFactory容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册当前类为配置类
		applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

		beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

		// 启动Spring应用上下文
		applicationContext.refresh();

		// 依赖查找并创建Bean
		AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);

		System.out.println(demo.userHolder1);
		System.out.println(demo.userHolder2);

		System.out.println(demo.userHolder1 == demo.userHolder2);

		// 关闭Spring应用上下文
		applicationContext.close();

	}

}
