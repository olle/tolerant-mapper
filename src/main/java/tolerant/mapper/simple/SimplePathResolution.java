package tolerant.mapper.simple;

import java.util.Map;

import tolerant.mapper.traverse.PathResolution;

public class SimplePathResolution implements PathResolution<Map<Object, Object>> {
	
	private final Map<Object, Object> map;

	public SimplePathResolution(Map<Object, Object> map) {
		this.map = map;
	}

	@Override
	public Object valueForPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

}
