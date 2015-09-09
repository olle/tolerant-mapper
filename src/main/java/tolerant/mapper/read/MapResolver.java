package tolerant.mapper.read;

import java.util.Map;

public interface MapResolver {

	Object valueForPath(String path, Map<Object, Object> source);

}
