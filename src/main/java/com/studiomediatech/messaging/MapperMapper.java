package com.studiomediatech.messaging;

import java.util.Map;

public class MapperMapper {

	private Map<Object, Object> map;

	public MapperMapper(Map<Object, Object> map) {
		this.map = map;
	}

	public Object valueForPath(String path) {

		return valueForPathInMap(path, map);
	}

	private Object valueForPathInMap(String path, Map<Object, Object> context) {

		if (!path.contains(".")) {
			return context.get(path);
		}

		int i = path.indexOf('.');
		String head = path.substring(0, i);
		String tail = path.substring(i + 1);

		return valueForPathAndRestInMap(head, tail, context);
	}

	private Object valueForPathAndRestInMap(String head, String tail, Map<Object, Object> context) {

		Object object = context.get(head);
		
		if (object == null || !Map.class.isAssignableFrom(object.getClass())) {
			throw new IllegalArgumentException(String.format("The path %s does not reference an object.", head));
		}
		
		return valueForPathInMap(tail, (Map<Object, Object>) object);
	}

}
