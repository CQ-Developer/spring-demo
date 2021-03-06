package org.huhu.thinking.in.spring.ioc.overview.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.huhu.thinking.in.spring.ioc.overview.annotation.Super;

/**
 * 超级用户
 *
 * @author huhu
 */
@Super
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class SuperUser extends User {

	private String address;

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
	}

}


