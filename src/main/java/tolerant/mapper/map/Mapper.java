package tolerant.mapper.map;

import java.util.Map;

import tolerant.mapper.Path.Expression;

/**
 * Knows how to traverse a specified map with a given string path-expression and
 * find a value.
 */
public interface Mapper {

	/**
	 * Retrieves the value, mapped by the given path-expression, by traversing
	 * the specified map.
	 * 
	 * @param path
	 *            expression, specifying the the lookup traversal, never
	 *            {@code null}
	 * @param map
	 *            to try to map the path to, in order to return a value, never
	 *            {@code null}
	 * @return the object value found, or {@code null} if it doesn't exist
	 */
	Object get(Expression path, Map<Object, Object> map);
}
