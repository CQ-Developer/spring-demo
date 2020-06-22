package org.huhu.thinking.in.spring.ioc.bean.scope;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import static org.huhu.thinking.in.spring.ioc.bean.scope.ThreadLocalScope.SCOPE_NAME;

/**
 * 自定义 Scope 示例
 *
 * @author huhu
 * @see ThreadLocalScope
 */
public class ThreadLocalScopeDemo {

	@Bean
	@Scope(SCOPE_NAME)
	public User user() {
		return createUser();
	}

	private User createUser() {
		User user = new User();
		user.setId(System.nanoTime());
		return user;
	}

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class
		applicationContext.register(ThreadLocalScopeDemo.class);

		applicationContext.addBeanFactoryPostProcessor(beanFactory -> beanFactory.registerScope(SCOPE_NAME, new ThreadLocalScope()));

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		scopedBeansByLookup(applicationContext);

		// 关闭 Spring 应用上下文
		applicationContext.close();

	}

	private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
		for (int i = 0; i < 3; i++) {
			try {
				Thread thread = new Thread(() -> {
					User user = applicationContext.getBean("user", User.class);
					System.out.println("user = " + user);
				});

				// 启动线程
				thread.start();

				// 强制线程执行完成
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
