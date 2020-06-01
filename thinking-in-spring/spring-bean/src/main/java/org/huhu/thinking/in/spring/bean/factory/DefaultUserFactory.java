package org.huhu.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认 {@link UserFactory} 实现
 *
 * @author huhu
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

	// 基于 @PostConstruct 注解的初始化方法
	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct: UserFactory 初始化中...");
	}

	// 自定义方法的初始化方法
	@Override
	public void initUserFactory() {
		System.out.println("自定义初始化方法 initUserFactory(): UserFactory 初始化中...");
	}

	// 实现 InitializingBean 接口的初始化方法
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean afterPropertiesSet(): UserFactory 初始化中...");
	}

	// 基于 @PreDestroy 注解的销毁方法
	@PreDestroy
	public void preDestroy() {
		System.out.println("@PreDestroy: UserFactory 销毁中...");
	}

	// 自定义方法的销毁方法
	@Override
	public void doDestroy() {
		System.out.println("自定义初始化方法 doDestroy(): UserFactory 销毁中...");
	}

	// 实现 DisposableBean 接口的销毁方法
	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean#destroy(): UserFactory 销毁中...");
	}

	@Override
	public void finalize() throws Throwable {
		System.out.println("DefaultUserFactory 正在被垃圾回收...");
	}

}
