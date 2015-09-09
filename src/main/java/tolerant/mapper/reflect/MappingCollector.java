package tolerant.mapper.reflect;

import java.util.List;

import tolerant.mapper.PathMapping;

public interface MappingCollector {

	List<PathMapping> collectMappings(Class<?> type);

}
