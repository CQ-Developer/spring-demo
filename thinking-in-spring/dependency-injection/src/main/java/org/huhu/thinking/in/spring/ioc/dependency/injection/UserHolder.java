package org.huhu.thinking.in.spring.ioc.dependency.injection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.huhu.thinking.in.spring.ioc.overview.domain.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHolder {

	private User user;

}
