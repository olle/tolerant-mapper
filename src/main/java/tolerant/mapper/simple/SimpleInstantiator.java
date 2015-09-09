package tolerant.mapper.simple;

import tolerant.mapper.reflect.Instantiator;

public class SimpleInstantiator<T> implements Instantiator<T> {

	private Class<?> type;

	public SimpleInstantiator(Class<?> type) {
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T newInstance() {
		try {
			return (T) type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalArgumentException("Could not create instance.", e);
		}
	}

}
