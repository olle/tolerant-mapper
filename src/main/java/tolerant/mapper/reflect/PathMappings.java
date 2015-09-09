package tolerant.mapper.reflect;

import java.util.List;

import tolerant.mapper.PathMapping;

public interface PathMappings {

	List<PathMapping> forType(Class<?> clazz);
}
