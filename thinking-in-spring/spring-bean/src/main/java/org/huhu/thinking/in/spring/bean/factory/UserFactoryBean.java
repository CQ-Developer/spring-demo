package org.huhu.thinking.in.spring.bean.factory;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link User} 通过 {@link FactoryBean} 的实现
 *
 * @author huhu
 */
public class UserFactoryBean implements FactoryBean<User> {

	@Override
	public User getObject() throws Exception {
		return User.createUser();
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}

}
