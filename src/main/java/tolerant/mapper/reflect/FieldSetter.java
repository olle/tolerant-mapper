package tolerant.mapper.reflect;

import java.lang.reflect.Field;

public interface FieldSetter {

	<T> void setValue(Field field, T target, Object value);

}
