package com.studiomediatech.messaging;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapperMapperTest {

	private Map<Object, Object> map;
	private MapperMapper mappermapper;

	@Before
	public void setup() {
		map = new HashMap<>();
		// simple first level property
		map.put("foo", "bar");
		// simple second level property
		Map<Object, Object> properties = new HashMap<>();
		properties.put("name", "Sedwig");
		properties.put("age", 34);
		properties.put("active", false);
		HashMap<Object, Object> things = new HashMap<>();
		things.put("one", "A");
		things.put("two", new String[] {"foo", "bar", "baz"});
		things.put("three", false);
		properties.put("things", things);
		map.put("properties", properties);		
		mappermapper = new MapperMapper(map);
	}
	
	@Test
	public void ensurePathToFirstLevelResolvesMapProperty() throws Exception {
		Object value = mappermapper.valueForPath("foo");
		Assert.assertEquals("Wrong value", "bar", value);
		Assert.assertEquals("Wrong type", String.class, value.getClass());	
	}
	
	@Test
	public void ensurePathToSecondLevelResolvsMapProperty() throws Exception {
		Object value = mappermapper.valueForPath("properties.age");
		Assert.assertEquals("Wrong age", 34, value);
	}
	
	@Test
	public void ensurePathToThirdLevelResolvsMapProperty() throws Exception {
		Object value = mappermapper.valueForPath("properties.things.three");
		Assert.assertEquals("Wrong value", Boolean.FALSE, value);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ensureThrowsWhenTryingToAccessBadPath() throws Exception {
		mappermapper.valueForPath("properties.nooooo.crash");
	}
}
