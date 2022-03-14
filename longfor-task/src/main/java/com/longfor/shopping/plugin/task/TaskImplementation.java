package com.longfor.shopping.plugin.task;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注一个Task实现
 * TaskImplementation
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskImplementation {
	String id() default "";
	String event();
	String storage() default "MEMORY";
}
