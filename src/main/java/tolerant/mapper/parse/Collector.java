package tolerant.mapper.parse;

import java.util.List;

import tolerant.mapper.Mapping;

/**
 * Collects {@code Mapping}s from a given type.
 */
public interface Collector {

	/**
	 * Collects all the mappings found in the given type.
	 * 
	 * @param type
	 *            to look for path mappings in
	 * @param <T>
	 *            class type
	 * 
	 * @return a list of mapping, or an empty list if none were found, never
	 *         {@code null}
	 */
	<T> List<Mapping> collectMappings(Class<T> type);

}
