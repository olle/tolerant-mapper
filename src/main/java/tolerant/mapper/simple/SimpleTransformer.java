package tolerant.mapper.simple;

import java.util.Map;

import tolerant.mapper.Mapping;
import tolerant.mapper.map.Mapper;
import tolerant.mapper.reflect.Setter;
import tolerant.mapper.transform.Transformer;

public class SimpleTransformer implements Transformer {

	private final Mapper mapper;
	private final Setter setter;

	public SimpleTransformer() {
		this.mapper = SimpleFactory.createMapper();
		this.setter = SimpleFactory.getSetter();
	}

	@Override
	public <T> void transform(Mapping mapping, Map<Object, Object> source, T target) {

		setter.set(mapper.get(mapping.getPath().value(), source), target, mapping.getField());
	}

}
