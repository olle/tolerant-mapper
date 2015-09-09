package tolerant.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import tolerant.mapper.read.MapResolver;
import tolerant.mapper.reflect.FieldSetter;
import tolerant.mapper.reflect.MappingCollector;
import tolerant.mapper.reflect.ObjectInstantiator;

@RunWith(MockitoJUnitRunner.class)
public class MapperTest {

	@Mock
	MappingCollector collector;
	@Mock
	ObjectInstantiator<Object> instantiator;
	@Mock
	FieldSetter setter;
	@Mock
	MapResolver resolver;

	Mapper<Object> mapper;

	@Before
	public void setup() {
		this.mapper = new Mapper<>(Object.class, collector, instantiator, setter, resolver);
	}

	@Test
	public void ensureCreateMapperForTypeThroughStaticFactoryMethod() {
		Mapper<String> mapper = Mapper.forType(String.class);
		assertNotNull("Not an instance", mapper);
		assertTrue("Wrong type", Mapper.class.isAssignableFrom(mapper.getClass()));
	}

	@Test
	public void ensureTransformUsesDelegates() {

		when(collector.collectMappings(any())).thenReturn(Arrays.asList(new PathMapping("", null)));

		mapper.transform(new HashMap<>());

		verify(instantiator).newInstance();
		verify(collector).collectMappings(any());
		verify(resolver).valueForPath(anyString(), any());
		verify(setter).setValue(any(), any(), any());
	}

}
