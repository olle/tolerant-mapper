package tolerant.mapper.simple;

import java.util.Map;

import tolerant.mapper.Path.Expression;
import tolerant.mapper.map.Mapper;

/**
 * The most simple mapper implementation possible. 
 */
public class SimpleMapper implements Mapper {

	@Override
	public Object get(Expression path, Map<Object, Object> map) {
		String value = path.value();
		return valueForPathInMap(value, map);
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
