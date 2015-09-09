package tolerant.mapper.parse;

import java.util.List;

import tolerant.mapper.Mapping;

public interface Collector {

	<T> List<Mapping> collectMappings(Class<T> type);

}
