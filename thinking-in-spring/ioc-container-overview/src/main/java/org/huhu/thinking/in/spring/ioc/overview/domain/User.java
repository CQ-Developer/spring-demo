package org.huhu.thinking.in.spring.ioc.overview.domain;

import lombok.Data;

/**
 * 用户类
 *
 * @author huhu
 */
@Data
public class User {

	private Long id;

	private String name;

	public static User createUser() {
		User user = new User();
		user.setId(1L);
		user.setName("呼呼");
		return user;
	}

}
