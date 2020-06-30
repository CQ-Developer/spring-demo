package org.huhu.thinking.in.spring.ioc.overview.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.huhu.thinking.in.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * 用户类
 *
 * @author huhu
 */
@Getter
@Setter
@EqualsAndHashCode
public class User implements BeanNameAware {

	private Long id;

	private String name;

	private City city;

	private City[] workCities;

	private List<City> lifeCities;

	private Resource configFileLocation;

	/** 当前 Bean 的抿名称 */
	private String beanName;

	public static User createUser() {
		User user = new User();
		user.setId(1L);
		user.setName("呼呼");
		return user;
	}

	@PostConstruct
	public void init() {
		System.out.println("user bean " + beanName + " 初始化...");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("user bean " + beanName + " 销毁...");
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
	}

}
