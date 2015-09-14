package tolerant.mapper.transform;

import java.util.Map;

import tolerant.mapper.Mapping;

/**
 * Can perform a mapped data-transformation, from a external data-structure
 * (map) to a specified target instance of a given type.
 */
public interface Transformer {

	/**
	 * Transforms the given mapping and source data-map, setting the result on
	 * the provided target object.
	 * 
	 * @param mapping
	 *            to transform, specifying the data mapping and the target field
	 * @param source
	 *            to lookup the transformation value from
	 * @param target
	 *            instance to apply the value on
	 * @param <T>
	 *            target type
	 */
	<T> void transform(Mapping mapping, Map<Object, Object> source, T target);

}
