package org.huhu.thinking.in.spring.ioc.bean.scope;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * Bean 的作用域示例
 *
 * @author HuHu
 */
public class BeanScopeDemo implements DisposableBean {

	@Autowired
	@Qualifier("singletonUser")
	private User singletonUser0;

	@Autowired
	@Qualifier("singletonUser")
	private User singletonUser1;

	@Autowired
	@Qualifier("prototypeUser")
	private User prototypeUser0;

	@Autowired
	@Qualifier("prototypeUser")
	private User prototypeUser1;

	@Autowired
	@Qualifier("prototypeUser")
	private User prototypeUser2;

	@Autowired
	private Map<String, User> userMap;

	/**
	 * Resolvabel Dependency
	 */
	@Autowired
	private ConfigurableListableBeanFactory beanFactory;

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
			beanFactory.addBeanPostProcessor(new BeanPostProcessor() {

				@Override
				public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
					System.out.println(bean.getClass().getSimpleName() + " : " + beanName + " 在初始化后回调 !");
					return bean;
				}

			});
		});

		// 注册配置类
		applicationContext.register(BeanScopeDemo.class);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		/*
		 * 结论一:
		 * Singleton Bean 无论依赖查找还是依赖注入, 均为同一个对象
		 * Prototype Bean 无论依赖查找还是依赖注入, 均为新生成对象
		 *
		 * 结论二:
		 * 如果依赖注入集合对象, Singleton  和 Prototype Bean 均会存在一个
		 * Prototype Bean 有别于其它地方的依赖注入的 Prototype Bean
		 *
		 * 结论三:
		 * 无论是 Singleton Bean 还是 Prototype Bean 均会执行初始化回调方法
		 * 仅 Singleton Bean 会执行销毁方法回调
		 */
		scopeBeanByInjection(applicationContext);
		scopeBeanByLookup(applicationContext);

		// 关闭 Spring 应用上下文
		applicationContext.close();

	}

	@Override
	public void destroy() throws Exception {
		System.out.println("当前 BeanScopeDemo Bean 正在销毁中...");

		this.prototypeUser0.destroy();
		this.prototypeUser1.destroy();
		this.prototypeUser2.destroy();

		for (Map.Entry<String, User> entry : userMap.entrySet()) {
			User user = entry.getValue();
			// 获取 BeanDefinition
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(user.getBeanName());
			// 如果当前 Bean 是 Prototype Scope
			if (beanDefinition.isPrototype()) {
				user.destroy();
			}
		}

		System.out.println("当前 BeanScopeDemo Bean 销毁完成...");
	}

	private static void scopeBeanByInjection(AnnotationConfigApplicationContext applicationContext) {
		BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);

		System.out.println("demo.singletonUser0 = " + demo.singletonUser0);
		System.out.println("demo.singletonUser1 = " + demo.singletonUser1);

		System.out.println("demo.prototypeUser0 = " + demo.prototypeUser0);
		System.out.println("demo.prototypeUser1 = " + demo.prototypeUser1);
		System.out.println("demo.prototypeUser2 = " + demo.prototypeUser2);

		System.out.println("demo.userMap = " + demo.userMap);
	}

	private static void scopeBeanByLookup(AnnotationConfigApplicationContext applicationContext) {
		for (int i = 0; i < 3; i++) {
			// singletonUser 是共享 Bean 对象
			User singletonUser = applicationContext.getBean("singletonUser", User.class);
			System.out.println("singletonUser = " + singletonUser);
			// prototypeUser 是每次依赖查找均声称了新的 Bean 对象
			User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
			System.out.println("prototypeUser = " + prototypeUser);
		}
	}

	@Bean
	public static User singletonUser() {
		return createUser();
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public static User prototypeUser() {
		return createUser();
	}

	private static User createUser() {
		User user = new User();
		user.setId(System.nanoTime());
		return user;
	}

}
