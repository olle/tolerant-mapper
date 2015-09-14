package tolerant.mapper.simple;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import tolerant.mapper.Mapping;
import tolerant.mapper.Path;
import tolerant.mapper.Path.Expression;
import tolerant.mapper.parse.Collector;

/**
 * This simple collector implementation collects mappings from the specified
 * type and any declared, extended parent types.
 */
public class SimpleCollector implements Collector {

	@Override
	public <T> List<Mapping> collectMappings(Class<T> type) {

		List<Mapping> mappings = new ArrayList<>();
		collectPathMappingsForType(type, mappings);
		return Collections.unmodifiableList(mappings);
	}

	private void collectPathMappingsForType(Class<?> clazz, List<Mapping> mappings) {

		if (clazz.getSuperclass().getSuperclass() != null) {
			collectPathMappingsForType(clazz.getSuperclass(), mappings);
		}

		collectMappingsForFields(clazz.getDeclaredFields(), mappings);
	}

	private void collectMappingsForFields(Field[] fields, List<Mapping> mappings) {

		for (Field field : fields) {
			collectMappingForField(field, mappings);
		}
	}

	private void collectMappingForField(Field field, List<Mapping> mappings) {

		Predicate<Annotation> isPathAnnotation = a -> Path.class.isAssignableFrom(a.getClass());

		Predicate<Field> hasAnyPathAnnotation = f -> Arrays.stream(f.getAnnotations()).filter(isPathAnnotation)
				.findAny().isPresent();

		if (hasAnyPathAnnotation.test(field)) {
			Annotation[] annotations = field.getAnnotations();
			Annotation annotation = Arrays.stream(annotations).filter(isPathAnnotation).findFirst().get();
			Expression path = Expression.valueOf((Path) annotation);
			Mapping mapping = new Mapping(path, field);
			mappings.add(mapping);
		}
	}

}
