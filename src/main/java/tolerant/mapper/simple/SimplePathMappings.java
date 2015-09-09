package tolerant.mapper.simple;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import tolerant.mapper.Path;
import tolerant.mapper.PathMapping;
import tolerant.mapper.reflect.PathMappings;

/**
 * The most simple path mappings parser I can think of.
 * 
 * @author Olle Törnström - olle@studiomediatech.com
 */
public class SimplePathMappings implements PathMappings {

	@Override
	public List<PathMapping> forType(Class<?> clazz) {

		List<PathMapping> mappings = new ArrayList<>();

		parsePathMappingsForType(clazz, mappings);

		return Collections.unmodifiableList(mappings);
	}

	private void parsePathMappingsForType(Class<?> clazz, List<PathMapping> mappings) {

		if (clazz.getSuperclass().getSuperclass() != null) {
			parsePathMappingsForType(clazz.getSuperclass(), mappings);
		}

		parsePathMappingsForFields(clazz.getDeclaredFields(), mappings);
	}

	private void parsePathMappingsForFields(Field[] fields, List<PathMapping> mappings) {

		for (Field field : fields) {
			parsePathMappingsForField(field, mappings);
		}
	}

	private void parsePathMappingsForField(Field field, List<PathMapping> mappings) {

		Predicate<Annotation> isPathAnnotation = a -> Path.class.isAssignableFrom(a.getClass());

		Predicate<Field> hasAnyPathAnnotation = f -> Arrays.stream(f.getAnnotations()).filter(isPathAnnotation)
				.findAny().isPresent();

		if (hasAnyPathAnnotation.test(field)) {
			Annotation[] annotations = field.getAnnotations();
			Annotation pathAnnotation = Arrays.stream(annotations).filter(isPathAnnotation).findFirst().get();
			PathMapping pathMapping = new PathMapping(((Path) pathAnnotation).value(), field);
			mappings.add(pathMapping);
		}
	}
}
