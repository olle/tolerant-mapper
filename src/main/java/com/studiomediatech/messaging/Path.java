package com.studiomediatech.messaging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Dot-separated string, the value mapping for the annotated field.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Path {
	/**
	 * The dot-separated path for this annotated field.
	 * @return path string
	 */
	String value();	
}
