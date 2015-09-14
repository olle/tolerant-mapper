package tolerant.mapper.reflect;

/**
 * Can instantiate an object of the specified type.
 *
 * @param <T>
 *            of the instance object
 */
public interface Instantiator<T> {

	/**
	 * Creates a new instance.
	 * 
	 * @return the new instance object, never {@code null}
	 */
	T newInstance();
}
