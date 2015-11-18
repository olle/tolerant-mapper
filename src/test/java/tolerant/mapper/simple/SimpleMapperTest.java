package tolerant.mapper.simple;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import tolerant.mapper.Path;

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
		things.put("two", new String[] { "foo", "bar", "baz" });
		things.put("three", false);
		properties.put("things", things);
	}

	@Test
	public void ensurePathToFirstLevelResolvesMapProperty() throws Exception {
		Object value = new SimpleMapper().get(Path.Expression.valueOf("foo"), map);
		assertEquals("Wrong value", "bar", value);
	}

	@Test
	public void ensurePathToSecondLevelResolvsMapProperty() throws Exception {
		Object value = new SimpleMapper().get(Path.Expression.valueOf("properties.age"), map);
		assertEquals("Wrong age", 34, value);
	}

	@Test
	public void ensurePathToThirdLevelResolvsMapProperty() throws Exception {
		Object value = new SimpleMapper().get(Path.Expression.valueOf("properties.things.three"), map);
		assertEquals("Wrong value", Boolean.FALSE, value);
	}

	@Test(expected = IllegalArgumentException.class)
	public void ensureThrowsWhenTryingToAccessNonExistingMapPath() throws Exception {
		new SimpleMapper().get(Path.Expression.valueOf("properties.nope.nope"), map);
	}

	@Test(expected = IllegalArgumentException.class)
	public void ensureThrowsWhenTryingToAccessPropertyOnValue() throws Exception {
		new SimpleMapper().get(Path.Expression.valueOf("properties.age.nope"), map);
	}

	@Test
	public void ensurePathToFourthLevelArrayByIndexResolvesValueOne() throws Exception {
		Object value = new SimpleMapper().get(Path.Expression.valueOf("properties.things.two[0]"), map);
		assertEquals("wrong value", "foo", value);
	}

	@Test
	public void ensurePathToFourthLevelArrayByIndexResolvesValueTwo() throws Exception {
		Object value = new SimpleMapper().get(Path.Expression.valueOf("properties.things.two[1]"), map);
		assertEquals("wrong value", "bar", value);
	}

	@Test
	public void ensurePathToFourthLevelArrayByIndexResolvesValueThree() throws Exception {
		Object value = new SimpleMapper().get(Path.Expression.valueOf("properties.things.two[2]"), map);
		assertEquals("wrong value", "baz", value);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ensureThrowsWhenTryingToAccessValueAsArrayThatIsNot() throws Exception {
		new SimpleMapper().get(Path.Expression.valueOf("properties.things.one[0]"), map);
	}

}
