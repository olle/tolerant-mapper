package com.studiomediatech.messaging;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public class MapperMapper<T> {

    private final Class<T> clazz;
    private final List<Mapping> mappings;

    private MapperMapper(Class<T> clazz) {

        this.clazz = clazz;
        mappings = MapMapper.getMappings(clazz);
    }

    public T transform(Map<Object, Object> map) {

        final T object;

        try {
            object = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("Could not create target for transform.", e);
        }

        MapMapper mapper = new MapMapper(map);

        for (Mapping m : mappings) {
            String path = m.getPath();
            Field f = m.getField();

            Object value = mapper.valueForPath(path);
            f.setAccessible(true);

            try {
                if (Optional.class.isAssignableFrom(f.getType())) {
                    f.set(object, Optional.ofNullable(value));
                } else {
                    f.set(object, value);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new IllegalArgumentException("Could not set property on target object.", e);
            }
        }

        return object;
    }


    public static <T> MapperMapper<T> forClass(Class<T> clazz) {

        return new MapperMapper<T>(clazz);
    }
}
