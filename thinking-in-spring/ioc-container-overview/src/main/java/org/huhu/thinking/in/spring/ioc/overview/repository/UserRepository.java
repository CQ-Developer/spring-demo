package org.huhu.thinking.in.spring.ioc.overview.repository;

import lombok.Data;
import org.huhu.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * 用户的信息仓库
 *
 * @author huhu
 */
@Data
public class UserRepository {

	private Collection<User> users;

	private BeanFactory beanFactory;

	private ObjectFactory<ApplicationContext> objectFactory;

}
