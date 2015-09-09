package tolerant.mapper.map;

import java.util.Map;

public interface Mapper {

	Object get(String path, Map<Object, Object> map);
}
