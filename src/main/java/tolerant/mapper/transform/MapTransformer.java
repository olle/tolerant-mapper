package tolerant.mapper.transform;

import java.util.Map;

public interface MapTransformer<T> {

	T transform(Map<Object, Object> source);
}
