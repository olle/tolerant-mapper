package com.studiomediatech.messaging;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PathAnnotationTest {

	@Test
	public void ensureFindMappingBaseOnDeclaredAnnotatedFields() throws Exception {

		List<Mapping> mappings = MapMapper.getMappings(Foo.class);

		Assert.assertEquals("Wrong amount", 1, mappings.size());
		Assert.assertEquals("Wrong value", "foo.name", mappings.get(0).getPath());
		Assert.assertNotNull("No field", mappings.get(0).getField());
		Assert.assertEquals("Wrong field name", "name", mappings.get(0).getField().getName());
	}

	@Test
	public void ensureTraversesInheritedTypesForMoreAnnotatedPrivateFields() throws Exception {
		List<Mapping> mappings = MapMapper.getMappings(Bar.class);

		Assert.assertEquals("Wrong amount", 2, mappings.size());
		Assert.assertEquals("Wrong value", "foo.name", mappings.get(0).getPath());
		Assert.assertEquals("Wrong field name", "name", mappings.get(0).getField().getName());
		Assert.assertEquals("Wrong value", "bar.age", mappings.get(1).getPath());
		Assert.assertEquals("Wrong field name", "age", mappings.get(1).getField().getName());

	}

	public static class Foo {
		@Path("foo.name")
		private String name;
	}

	public static class Bar extends Foo {
		@Path("bar.age")
		private String age;
	}
}
