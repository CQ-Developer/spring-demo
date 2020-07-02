package org.huhu.thinking.in.spring.lifecycle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.huhu.thinking.in.spring.ioc.overview.domain.User;

/**
 * user holder 类
 *
 * @author HuHu
 */
@Getter
@Setter
@EqualsAndHashCode
public class UserHolder {

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
		return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
	}

}
