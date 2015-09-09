package tolerant.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import tolerant.mapper.reflect.PathMappings;

/**
 * This mapper knows how to traverse a given map and retrieve entries from it by
 * a dot-separated string path.
 */
public class MapMapper implements PathMappings {

	private final Map<Object, Object> map;

	/**
	 * Creates a new mapper for the given map.
	 * 
	 * @param map
	 *            to use as a base for the local unmodifiable view this mapper
	 *            will work on
	 */
	public MapMapper(Map<Object, Object> map) {
		this.map = Collections.unmodifiableMap(map);
	}

	/**
	 * Returns the object found for the given string path
	 * 
	 * @param path
	 *            string, to lookup an object for. A dot-separated string
	 * @return the object found or {@code null} if it couldn't be found or the
	 *         value actually was {@code null}
	 */
	public Object valueForPath(String path) {
		return valueForPathInMap(path, this.map);
	}

	private Object valueForPathInMap(String path, Map<Object, Object> map) {

		int i = path.indexOf('.');

		if (i == -1) {
			return map.get(path);
		}

		String head = path.substring(0, i);
		String tail = path.substring(i + 1);

		return valueForPathAndRestInMap(head, tail, map);
	}

	@SuppressWarnings("unchecked")
	private Object valueForPathAndRestInMap(String head, String tail, Map<Object, Object> context) {

		Object object = context.get(head);

		if (object == null || !Map.class.isAssignableFrom(object.getClass())) {
			throw new IllegalArgumentException(String.format("The path %s does not reference a map.", head));
		}

		return valueForPathInMap(tail, (Map<Object, Object>) object);
	}



}
