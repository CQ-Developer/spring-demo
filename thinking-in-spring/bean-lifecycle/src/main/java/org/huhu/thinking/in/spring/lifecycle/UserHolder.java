package org.huhu.thinking.in.spring.lifecycle;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * user holder 类
 *
 * @author HuHu
 */
@Getter
@Setter
@EqualsAndHashCode
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware {

	private final User user;

	private Integer number;

	private String description;

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

	// 4
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
