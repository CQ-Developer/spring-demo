package org.huhu.thinking.in.spring.ioc.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 级别的 Scope
 *
 * @author huhu
 */
public class ThreadLocalScope implements Scope {

	public static final String SCOPE_NAME = "thread-local";

	/**
	 * 重写 initialValue 初始化方法, 防止空指针
	 */
	private final ThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal<Map<String, Object>>("thread-local-scope") {

		@Override
		protected Map<String, Object> initialValue() {
			return new HashMap<>();
		}

	};

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Map<String, Object> context = getContext();

		Object object = context.get(name);
		if (object == null) {
			object = objectFactory.getObject();
			context.put(name, object);
		}

		return object;
	}

	@Override
	public Object remove(String name) {
		Map<String, Object> context = getContext();
		return context.remove(name);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// TODO
	}

	@Override
	public Object resolveContextualObject(String key) {
		Map<String, Object> context = getContext();
		return context.get(key);
	}

	@Override
	public String getConversationId() {
		Thread thread = Thread.currentThread();
		return String.valueOf(thread.getId());
	}

	@NonNull
	private Map<String, Object> getContext() {
		return threadLocal.get();
	}

}
