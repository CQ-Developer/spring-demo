package org.huhu.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于API实现Setter方法的依赖注入
 *
 * @author huhu
 */
public class ApiDependencyConstructorInjectionDemo {

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 创建并获取 UserHolder 的 BeanDefinition
		BeanDefinition beanDefinition = createUserHolderBeanDefinition();

		// 将 UserHolder 的 BeanDefinition 注册到当前容器中
		applicationContext.registerBeanDefinition("userHolder", beanDefinition);

		// 创建 XML 配置文件读取器
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

		// 加载 XML 配置文件, 解析并生成 BeanDefinition
		beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

		// 启动应用上下文
		applicationContext.refresh();

		// 查找 UserHolder Bean
		UserHolder userHolder = applicationContext.getBean(UserHolder.class);

		System.out.println(userHolder);

		// 关闭应用上下文
		applicationContext.close();
	}

	/**
	 * 为{@link UserHolder}生成{@link BeanDefinition}
	 *
	 * @return {@link BeanDefinition}
	 */
	private static BeanDefinition createUserHolderBeanDefinition() {
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
		beanDefinitionBuilder.addConstructorArgReference("superUser");
		return beanDefinitionBuilder.getBeanDefinition();
	}

}
