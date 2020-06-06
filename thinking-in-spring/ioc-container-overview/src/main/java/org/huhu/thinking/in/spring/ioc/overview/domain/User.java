package org.huhu.thinking.in.spring.ioc.overview.domain;

import lombok.Data;
import org.huhu.thinking.in.spring.ioc.overview.enums.City;
import org.springframework.core.io.Resource;

import java.util.List;

/**
 * 用户类
 *
 * @author huhu
 */
@Data
public class User {

	private Long id;

	private String name;

	private City city;

	private City[] workCities;

	private List<City> lifeCities;

	private Resource configFileLocation;

	public static User createUser() {
		User user = new User();
		user.setId(1L);
		user.setName("呼呼");
		return user;
	}

}
