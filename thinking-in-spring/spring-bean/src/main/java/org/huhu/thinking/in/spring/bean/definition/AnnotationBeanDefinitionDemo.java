package org.huhu.thinking.in.spring.bean.definition;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 注解 BeanDefinition 示例
 *
 * @author huhu
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

	public static void main(String[] args) {

		// 创建BeanFactory容器
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class (配置类)
		annotationConfigApplicationContext.register(AnnotationBeanDefinitionDemo.class);

		// 1.通过@Bean的方式进行定义

		// 2.通过@Component的方式进行实现

		// 3.通过@Import进行导入

		// 4.通过BeanDefinition注册API实现

		// 4.1.命名Bean的注册方式
		registerUserBeanDefinition(annotationConfigApplicationContext, "boy-huhu");

		// 4.2.非命名Bean的注册方式
		registerUserBeanDefinition(annotationConfigApplicationContext);

		// 启动上下文
		annotationConfigApplicationContext.refresh();

		// 按照类型依赖查找
		System.out.println("Config类型的所有Bean: " + annotationConfigApplicationContext.getBeansOfType(Config.class));
		System.out.println("User类型的所有Bean: " + annotationConfigApplicationContext.getBeansOfType(User.class));


		// 显示的关闭Spring应用上下文
		annotationConfigApplicationContext.close();

	}

	/**
	 * 命名Bean的注册方式
	 *
	 * @param registry 注册
	 * @param beanName 名称
	 */
	public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
		BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
		beanDefinitionBuilder.addPropertyValue("id", 1L).addPropertyValue("name", "呼呼");

		// 判断 beanName 参数存在时
		if (StringUtils.hasText(beanName))
			// 注册 Bean Definition
			registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
		else
			BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
	}

	public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
		registerUserBeanDefinition(registry, null);
	}

	@Component
	static class Config {

		@Bean(name = {"user", "huhu-user"})
		public User user() {
			User user = new User();
			user.setId(1L);
			user.setName("呼呼");
			return user;
		}

	}


}
