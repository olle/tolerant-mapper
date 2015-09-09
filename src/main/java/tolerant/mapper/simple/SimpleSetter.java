package tolerant.mapper.simple;

import java.lang.reflect.Field;
import java.util.Optional;

import tolerant.mapper.reflect.Setter;

public class SimpleSetter implements Setter {

	@Override
	public <T> void set(Object value, T target, Field field) {		
		try {
			field.setAccessible(true);
		    if (Optional.class.isAssignableFrom(field.getType())) {
		        field.set(target, Optional.ofNullable(value));
		    } else {
		        field.set(target, value);
		    }
		} catch (IllegalArgumentException | IllegalAccessException e) {
		    throw new IllegalArgumentException("Could not set field on target object.", e);
		}
	}

}
