package tolerant.mapper;

import static org.junit.Assert.*;

import org.junit.Test;

public class PathMappingTest {

	@SuppressWarnings("unused")
	private String myField;

	@Test
	public void ensureHasNiceToString() throws NoSuchFieldException, SecurityException {
		assertEquals("myField<--foo.bar.baz",
				new PathMapping("foo.bar.baz", getClass().getDeclaredField("myField")).toString());
	}
}
