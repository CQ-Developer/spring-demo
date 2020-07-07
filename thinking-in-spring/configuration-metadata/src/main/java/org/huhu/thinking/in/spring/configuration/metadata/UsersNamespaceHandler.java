package org.huhu.thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * users.xsd
 * {@link NamespaceHandlerSupport}
 *
 * @author huhu
 */
public class UsersNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		// 将 user 元素注册到对应的 BeanDefinitionParser 实现
		registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
	}

}
