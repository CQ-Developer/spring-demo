package org.huhu.thinking.in.spring.ioc.overview.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.huhu.thinking.in.spring.ioc.overview.annotation.Super;

/**
 * 超级用户
 *
 * @author huhu
 */
@Super
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SuperUser extends User {

	private String address;

}


