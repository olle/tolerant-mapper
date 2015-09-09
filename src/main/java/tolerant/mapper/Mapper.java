package tolerant.mapper;

import java.util.List;
import java.util.Map;

import tolerant.mapper.read.MapResolver;
import tolerant.mapper.reflect.FieldSetter;
import tolerant.mapper.reflect.MappingCollector;
import tolerant.mapper.reflect.ObjectInstantiator;
import tolerant.mapper.transform.MapTransformer;

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
 * @author Olle Törnström - olle@studiomediatech.com
 */
public final class Mapper<T> implements MapTransformer<T> {

	private final Class<T> type;

	private final MappingCollector collector;
	private final ObjectInstantiator<T> instantiator;
	private final FieldSetter setter;
	private final MapResolver resolver;

	private Mapper(Class<T> type) {
		this(type, null, null, null, null);
	}

	protected Mapper(Class<T> type, MappingCollector collector, ObjectInstantiator<T> instantiator, FieldSetter setter,
			MapResolver resolver) {
		this.type = type;
		this.collector = collector;
		this.instantiator = instantiator;
		this.setter = setter;
		this.resolver = resolver;
	}

	/**
	 * Returns a new tolerant mapper instance for the given class {@code type}.
	 * 
	 * @param type
	 *            specifying the target object type to transform the mapping
	 *            into
	 * 
	 * @return a new instance, never {@code null}
	 */
	public static <T> Mapper<T> forType(Class<T> type) {
		return new Mapper<>(type);
	}

	@Override
	public T transform(Map<Object, Object> source) {

		T object = instantiator.newInstance();

		List<PathMapping> mappings = collector.collectMappings(type);

		for (PathMapping mapping : mappings) {
			mapping.transform(source, object, resolver, setter);
		}

		return object;
	}
}
