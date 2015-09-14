package tolerant.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import tolerant.mapper.Path.Expression;

public class PathExpressionTest {

	@Test
	public void ensureCreatesExpressionFromAnnotatedField() throws NoSuchFieldException, SecurityException {
		Expression path = Path.Expression.valueOf(Foo.class.getDeclaredField("anything").getAnnotation(Path.class));
		assertNotNull("Missing expression", path);
		assertEquals("Wrong value", "anything.anywhere.anyone", path.value());
	}

	private static final class Foo {
		@Path("anything.anywhere.anyone")
		private String anything;
	}

}
