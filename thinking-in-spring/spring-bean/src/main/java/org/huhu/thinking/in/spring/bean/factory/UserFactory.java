package org.huhu.thinking.in.spring.bean.factory;

import org.huhu.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link org.huhu.thinking.in.spring.ioc.overview.domain.User} 工厂类
 *
 * @author huhu
 */
public interface UserFactory {

	default User createUser() {
		return User.createUser();
	}

	void initUserFactory();

	void doDestroy();

}
