package tolerant.mapper.simple;

import tolerant.mapper.map.Mapper;
import tolerant.mapper.parse.Collector;
import tolerant.mapper.reflect.Instantiator;
import tolerant.mapper.reflect.Setter;
import tolerant.mapper.transform.Transformer;

/**
 * Provides static factory methods for all the components used to wire up the
 * default tolerant mapper instance.
 */
public final class SimpleFactory {

	private SimpleFactory() {
		// Hidden!
	}

	public static Collector collector() {
		return new SimpleCollector();
	}

	public static <T> Instantiator<T> instantiator(Class<T> type) {
		return new SimpleInstantiator<T>(type);
	}

	public static Transformer transformer() {
		return new SimpleTransformer();
	}

	public static Mapper mapper() {
		return new SimpleMapper();
	}

	public static Setter setter() {
		return new SimpleSetter();
	}

}
