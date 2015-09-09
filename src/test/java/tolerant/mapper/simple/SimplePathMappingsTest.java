package tolerant.mapper.simple;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import tolerant.mapper.Path;
import tolerant.mapper.PathMapping;

public class SimplePathMappingsTest {

	@Test
	public void ensureFindMappingBaseOnDeclaredAnnotatedFields() throws Exception {

		List<PathMapping> mappings = new SimplePathMappings().forType(Foo.class);
		
		Assert.assertEquals("Wrong amount", 1, mappings.size());
		Assert.assertEquals("Wrong mapping", "name<--foo.name", mappings.get(0).toString());
	}

	@Test
	public void ensureTraversesInheritedTypesForMoreAnnotatedPrivateFields() throws Exception {
		
		List<PathMapping> mappings = new SimplePathMappings().forType(Goo.class);

		Assert.assertEquals("Wrong amount", 3, mappings.size());
		
		Assert.assertEquals("Wrong mapping", "name<--foo.name", mappings.get(0).toString());
		Assert.assertEquals("Wrong mapping", "email<--bar.email", mappings.get(1).toString());
		Assert.assertEquals("Wrong mapping", "age<--goo.age", mappings.get(2).toString());

	}
	
	
	public static class Foo {
		@Path("foo.name")
		private String name;
	}

	public static class Bar extends Foo {
		@Path("bar.email")
		private String email;
	}

	public static class Goo extends Bar {
		@Path("goo.age")
		private int age;
	}
}
