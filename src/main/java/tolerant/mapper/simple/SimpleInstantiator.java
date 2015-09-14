package tolerant.mapper.simple;

import tolerant.mapper.reflect.Instantiator;

/**
 * Simple implementation that uses regular, of-the-shelf, java reflection
 * instantiation - which means there might be trouble, with odd JVMs.
 *
 * @param <T>
 *            instance type
 */
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
