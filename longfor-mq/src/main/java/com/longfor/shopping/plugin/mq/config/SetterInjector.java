package com.longfor.shopping.plugin.mq.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 *
 * 对象Setter注入器
 *
 * Created by Coral
 */
public class SetterInjector {
	private static final Logger LOGGER = LoggerFactory.getLogger(SetterInjector.class);

	/**
	 * 通过反射设置字段值
	 *
	 * @param obj
	 * @param field
	 * @param value
	 */
	public static void setField(Object obj, Field field, Object value) {
		try {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		} catch (IllegalAccessException e) {
			LOGGER.error("Invoke failed.", e);
			throw new RuntimeException(e);
		}
	}
}
