package com.studiomediatech.messaging;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;


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


    @Test
    public void ensureMapsMapPropertiesToAnnotatedFieldsAsNullableOptionals() throws Exception {

        Map<Object, Object> vcard = new HashMap<>();
        vcard.put("nickName", "roggy");

        Map<Object, Object> user = new HashMap<>();
        user.put("name", "rmoore");
        user.put("vcard", vcard);

        Map<Object, Object> map = new HashMap<>();
        map.put("user", user);

        Bar bar = MapperMapper.forClass(Bar.class).transform(map);

        Assert.assertEquals("Wrong username", "rmoore", bar.getUsername());
        Assert.assertEquals("Must not be present", false, bar.getNiceName().isPresent());
        Assert.assertEquals("Wrong nickname", "roggy", bar.getNickName().get());
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

    public static class Bar {

        @Path("user.name")
        private String username;
        @Path("user.vcard.fullName")
        private Optional<String> niceName;
        @Path("user.vcard.nickName")
        private Optional<String> nickName;

        public String getUsername() {

            return username;
        }


        public Optional<String> getNickName() {

            return nickName;
        }


        public Optional<String> getNiceName() {

            return niceName;
        }
    }
}
