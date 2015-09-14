package tolerant.mapper.reflect;

import java.lang.reflect.Field;

/**
 * Knows how to set a value on a given instance, specified by a reflection
 * field.
 */
public interface Setter {

	/**
	 * Sets the given value on the target object field, through reflection.
	 * 
	 * @param value
	 *            to set
	 * @param target
	 *            object
	 * @param field
	 *            on the target object, to receive the value
	 * @param <T>
	 *            type of the target object
	 */
	<T> void set(Object value, T target, Field field);
}
