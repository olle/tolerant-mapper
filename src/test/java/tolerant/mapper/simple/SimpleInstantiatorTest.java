package tolerant.mapper.simple;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleInstantiatorTest {

	@Test
	public void ensureSimpleInstantiatorCanCreateInstances() {
		String instance = new SimpleInstantiator<String>(String.class).newInstance();
		assertNotNull("Not an instance", instance);
	}

}
