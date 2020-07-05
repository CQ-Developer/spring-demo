package org.huhu.thinking.in.spring.lifecycle;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * user holder 类
 *
 * @author HuHu
 */
@Getter
@Setter
@EqualsAndHashCode
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware,
		InitializingBean, SmartInitializingSingleton {

	private final User user;

	private Integer number;

	private String description;

	/**
	 * 依赖注解驱动
	 * 当前场景: BeanFactory
	 */
	@PostConstruct
	public void initPostConstructor() {
		// postProcessBeforeInitialization V3 -> initPostConstructor V4
		this.description = "My user holder V4";
		System.out.println("initPostConstructor() = " + description);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// initPostConstructor V4 -> afterPropertiesSet V5
		this.description = "The user Holder V5";
		System.out.println("afterPropertiesSet() = " + description);
	}

	/**
	 * 自定义初始化方法
	 */
	public void init() {
		// afterPropertiesSet V5 -> init V6
		this.description = "The user Holder V6";
		System.out.println("init() = " + description);
	}

	@Override
	public void afterSingletonsInstantiated() {
		// postProcessAfterInitialization V7 -> V8
		this.description = "The User Holder V8";
		System.out.println("afterSingletonsInstantiated() = " + description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserHolder(User user) {
		this.user = user;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "UserHolder{" +
				"user=" + user +
				", number=" + number +
				", description='" + description + '\'' +
				", beanName='" + beanName + '\'' +
				'}';
	}

	private ClassLoader classLoader;

	private BeanFactory beanFactory;

	private String beanName;

	private Environment environment;

	// 1
	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}

	// 2
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	// 3
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	// 4...
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
