package tolerant.mapper.simple;

import java.util.Map;

import tolerant.mapper.Mapping;
import tolerant.mapper.map.Mapper;
import tolerant.mapper.reflect.Setter;
import tolerant.mapper.transform.Transformer;

/**
 * A simple transformer implementation, using a simple mapper and a simpler
 * setter - you can't get simpler than this.
 */
public class SimpleTransformer implements Transformer {

	private final Mapper mapper;
	private final Setter setter;

	public SimpleTransformer() {
		this.mapper = SimpleFactory.mapper();
		this.setter = SimpleFactory.setter();
	}

	@Override
	public <T> void transform(Mapping mapping, Map<Object, Object> source, T target) {

		setter.set(mapper.get(mapping.getPath(), source), target, mapping.getField());
	}

}
