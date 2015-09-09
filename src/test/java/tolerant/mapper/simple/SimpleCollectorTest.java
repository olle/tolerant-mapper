package tolerant.mapper.simple;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import tolerant.mapper.Mapping;
import tolerant.mapper.Path;

public class SimpleCollectorTest {

	@Test
	public void ensureCollectsPathAnnotationAndField() {

		List<Mapping> mappings = new SimpleCollector().collectMappings(Foo.class);

		assertEquals("Wrong amount of mappings", 1, mappings.size());
		assertMappingPathAndFieldName("foo.name", "name", mappings.get(0));
	}

	@Test
	public void esnsureCollectsFromInheritedClassesToo() {

		List<Mapping> mappings = new SimpleCollector().collectMappings(Goo.class);

		assertEquals("Wrong amount of mappings", 3, mappings.size());
		assertMappingPathAndFieldName("foo.name", "name", mappings.get(0));
		assertMappingPathAndFieldName("bar.email", "email", mappings.get(1));
		assertMappingPathAndFieldName("goo.age", "age", mappings.get(2));
	}

	private void assertMappingPathAndFieldName(String expectedPath, String expectedFieldName, Mapping mapping) {
		assertEquals("Wrong path", expectedPath, mapping.getPath().value());
		assertEquals("Wrong field", expectedFieldName, mapping.getField().getName());
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
