package tolerant.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class MapperTest {

	@Test
	public void ensureCreateMapperForTypeThroughStaticFactoryMethod() {
		Mapper<String> mapper = Mapper.forType(String.class);
		assertNotNull("Not an instance", mapper);
		assertTrue("Wrong type", Mapper.class.isAssignableFrom(mapper.getClass()));
	}

	@Test
    public void ensureMapsMapPropertyToAnnotatedFields() throws Exception {

        Map<Object, Object> properties = new HashMap<>();
        properties.put("active", true);

        Map<Object, Object> raul = new HashMap<>();
        raul.put("name", "Raul Esteban Marques");
        raul.put("age", 71);
        raul.put("properties", properties);

        Map<Object, Object> map = new HashMap<>();
        map.put("foo", raul);

        Foo foo = Mapper.forType(Foo.class).transform(map);

        Assert.assertEquals("Wrong name", "Raul Esteban Marques", foo.name);
        Assert.assertEquals("Wrong age", 71, foo.age);
        Assert.assertEquals("Not active", true, foo.active);
    }


    @Test
    public void ensureMapsMapPropertiesToAnnotatedFieldsAsNullableOptionals() throws Exception {

        Map<Object, Object> vcard = new HashMap<>();
        vcard.put("nickName", "roggy");

        Map<Object, Object> user = new HashMap<>();
        user.put("name", "rmoore");
        user.put("vcard", vcard);

        Map<Object, Object> map = new HashMap<>();
        map.put("user", user);

        Bar bar = Mapper.forType(Bar.class).transform(map);

        Assert.assertEquals("Wrong username", "rmoore", bar.username);
        Assert.assertEquals("Must not be present", false, bar.niceName.isPresent());
        Assert.assertEquals("Wrong nickname", "roggy", bar.nickName.get());
    }	
	
	public static class Foo {
		@Path("foo.name")
		private String name;
		@Path("foo.age")
		private int age;
		@Path("foo.properties.active")
		private Boolean active;
	}

	public static class Bar {
		@Path("user.name")
		private String username;
		@Path("user.vcard.fullName")
		private Optional<String> niceName;
		@Path("user.vcard.nickName")
		private Optional<String> nickName;
	}

}
