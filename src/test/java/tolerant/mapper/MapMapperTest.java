package tolerant.mapper;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tolerant.mapper.MapMapper;

public class MapMapperTest {

	private Map<Object, Object> map;
	private MapMapper mappermapper;

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
		map.put("properties", properties);		
		
		// simple third level properties
		HashMap<Object, Object> things = new HashMap<>();
		things.put("one", "A");
		things.put("two", new String[] {"foo", "bar", "baz"});
		things.put("three", false);
		properties.put("things", things);

		mappermapper = new MapMapper(map);
	}
	
	@Test
	public void ensurePathToFirstLevelResolvesMapProperty() throws Exception {
		Object value = mappermapper.valueForPath("foo");
		Assert.assertEquals("Wrong value", "bar", value);
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
	public void ensureThrowsWhenTryingToAccessNonExistingMapPath() throws Exception {
		mappermapper.valueForPath("properties.nope.nope");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ensureThrowsWhenTryingToAccessPropertyOnValue() throws Exception {
		mappermapper.valueForPath("properties.age.nope");
	}
}
