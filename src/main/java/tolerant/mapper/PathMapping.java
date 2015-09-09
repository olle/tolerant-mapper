package tolerant.mapper;

import java.lang.reflect.Field;
import java.util.Map;

import tolerant.mapper.read.MapResolver;
import tolerant.mapper.reflect.FieldSetter;

/**
 * Encapsulates a {@link Path} annotation value and its target field.
 * 
 * @author Olle Törnström - olle@studiomediatech.com
 */
public final class PathMapping {

	private final String path;
	private final Field field;

	public PathMapping(String path, Field field) {
		this.path = path;
		this.field = field;
	}

	@Override
	public String toString() {
		return String.format("%s<--%s", field.getName(), path);
	}

	public <T> void transform(Map<Object, Object> source, T target, MapResolver resolver, FieldSetter setter) {
		Object value = resolver.valueForPath(path, source);
		setter.setValue(field, target, value);
	}

}
