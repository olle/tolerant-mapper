package tolerant.mapper.reflect;

import java.lang.reflect.Field;

public interface Setter {

	<T> void set(Object value, T target, Field field);
}
