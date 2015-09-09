package tolerant.mapper.simple;

import tolerant.mapper.map.Mapper;
import tolerant.mapper.parse.Collector;
import tolerant.mapper.reflect.Instantiator;
import tolerant.mapper.reflect.Setter;
import tolerant.mapper.transform.Transformer;

public final class SimpleFactory {

	private SimpleFactory() {
		// Hidden!
	}

	public static Collector createCollector() {
		return new SimpleCollector();
	}

	public static <T> Instantiator<T> createInstantiator(Class<T> type) {
		return new SimpleInstantiator<T>(type);
	}

	public static Transformer createTransformer() {
		return new SimpleTransformer();
	}

	public static Mapper createMapper() {
		return new SimpleMapper();
	}

	public static Setter getSetter() {
		return new SimpleSetter();
	}

}
