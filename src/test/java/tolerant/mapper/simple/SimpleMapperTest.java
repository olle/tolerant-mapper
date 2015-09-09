package tolerant.mapper.simple;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleMapperTest {

	private Map<Object, Object> map;

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
	}
	
	@Test
	public void ensurePathToFirstLevelResolvesMapProperty() throws Exception {
		Object value = new SimpleMapper().get("foo", map);
		Assert.assertEquals("Wrong value", "bar", value);
	}
	
	@Test
	public void ensurePathToSecondLevelResolvsMapProperty() throws Exception {
		Object value = new SimpleMapper().get("properties.age", map);
		Assert.assertEquals("Wrong age", 34, value);
	}
	
	@Test
	public void ensurePathToThirdLevelResolvsMapProperty() throws Exception {
		Object value = new SimpleMapper().get("properties.things.three", map);
		Assert.assertEquals("Wrong value", Boolean.FALSE, value);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ensureThrowsWhenTryingToAccessNonExistingMapPath() throws Exception {
		new SimpleMapper().get("properties.nope.nope", map);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ensureThrowsWhenTryingToAccessPropertyOnValue() throws Exception {
		new SimpleMapper().get("properties.age.nope", map);
	}

}
