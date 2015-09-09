package tolerant.mapper.simple;

import tolerant.mapper.reflect.Instantiates;

public class SimpleNewInstance<T> implements Instantiates<T> {

	private Class<T> clazz;

	public SimpleNewInstance(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public T newInstance() {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalArgumentException("Could not create target for transform.", e);
		}
	}
}
