package tolerant.mapper.transform;

import java.util.Map;

import tolerant.mapper.Mapping;

public interface Transformer {

	<T> void transform(Mapping mapping, Map<Object, Object> source, T target);

}
