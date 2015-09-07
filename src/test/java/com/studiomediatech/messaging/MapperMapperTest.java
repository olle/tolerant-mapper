package com.studiomediatech.messaging;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class MapperMapperTest {

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

        Foo foo = MapperMapper.forClass(Foo.class).transform(map);

        Assert.assertEquals("Wrong name", "Raul Esteban Marques", foo.getName());
        Assert.assertEquals("Wrong age", 71, foo.getAge());
        Assert.assertEquals("Not active", true, foo.getActive());
    }

    public static class Foo {

        @Path("foo.name")
        private String name;
        @Path("foo.age")
        private int age;
        @Path("foo.properties.active")
        private Boolean active;

        public String getName() {

            return name;
        }


        public int getAge() {

            return age;
        }


        public Boolean getActive() {

            return active;
        }
    }
}
