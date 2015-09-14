package tolerant.mapper;

import java.util.List;
import java.util.Map;

import tolerant.mapper.parse.Collector;
import tolerant.mapper.reflect.Instantiator;
import tolerant.mapper.simple.SimpleFactory;
import tolerant.mapper.transform.Transformer;

/**
 * Provides a simple and intuitive way to transform data into an annotated
 * object of the given {@code type}. This enables loose coupling of source and
 * target data formats.
 * 
 * <p>
 * This mapper and the {@link Path} annotation supports better internal
 * implementation stability with the ability to adapt to external data format
 * changes.
 * </p>
 * 
 * <p>
 * See <a href="http://martinfowler.com/bliki/TolerantReader.html">http://
 * martinfowler.com/bliki/TolerantReader.html</a> for more information on the
 * issue.
 * </p>
 * 
 * @param <T>
 *            target object type
 * 
 * @author Olle Törnström - olle@studiomediatech.com
 */
public final class Mapper<T> {

	private final Class<T> type;

	private final Instantiator<T> instantiator;
	private final Collector collector;
	private final Transformer transformer;

	private Mapper(Class<T> type) {
		this.type = type;

		this.instantiator = SimpleFactory.createInstantiator(type);
		this.collector = SimpleFactory.createCollector();
		this.transformer = SimpleFactory.createTransformer();
	}

	/**
	 * Returns a new tolerant mapper instance for the given class {@code type}.
	 * 
	 * @param type
	 *            specifying the target object type to transform the mapping
	 *            into
	 * 
	 * @param <T>
	 *            type of the mapper target object
	 * @return a new instance, never {@code null}
	 */
	public static <T> Mapper<T> forType(Class<T> type) {
		return new Mapper<>(type);
	}

	/**
	 * Transforms from the given map data-structure into a target instance
	 * object for this mapper.
	 * 
	 * @param source
	 *            to map from
	 * @return a new target object instance, never {@code null}
	 */
	public T transform(Map<Object, Object> source) {

		T object = instantiator.newInstance();

		List<Mapping> mappings = collector.collectMappings(type);

		for (Mapping mapping : mappings) {
			transformer.transform(mapping, source, object);
		}

		return object;
	}
}
