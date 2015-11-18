package tolerant.mapper.simple;

import java.util.Map;

import tolerant.mapper.Path.Expression;
import tolerant.mapper.map.Mapper;

/**
 * The most simple mapper implementation I could think of, traverses the map by
 * recursion and head/tail splitting the dot-string expression.
 */
public class SimpleMapper implements Mapper {

	@Override
	public Object get(Expression path, Map<Object, Object> map) {
		String value = path.value();
		return valueForPathInMap(value, map);
	}

	private Object valueForPathInMap(String path, Map<Object, Object> map) {

		int splitIndex = path.indexOf('.');

		if (splitIndex == -1) {
			return valueForLastPathInMap(path, map);
		}

		String head = path.substring(0, splitIndex);
		String tail = path.substring(splitIndex + 1);

		return valueForPathAndRestInMap(head, tail, map);
	}

	private Object valueForLastPathInMap(String path, Map<Object, Object> context) {

		int i = path.indexOf('[');

		if (i == -1) {
			return context.get(path);
		}

		String key = path.substring(0, i);
		String index = path.substring(i + 1).replaceAll("]", "");

		Object object = context.get(key);

		if (object == null || !Object[].class.isAssignableFrom(object.getClass())) {
			throw new IllegalArgumentException(String.format("The path %s does not resolve to an array.", path));
		}

		return ((Object[]) object)[Integer.parseInt(index)];
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
