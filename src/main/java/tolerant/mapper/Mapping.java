package tolerant.mapper;

import java.lang.reflect.Field;

import tolerant.mapper.Path.Expression;

/**
 * Encapsulates a data-mapping, with a path-expression for the external data
 * source and an internal field for the internal target.
 */
public final class Mapping {

	private final Field field;
	private final Expression path;

	public Mapping(Expression path, Field field) {
		this.path = path;
		this.field = field;
	}

	/**
	 * Retrieves a path-expression, specifying the external data-mapping source.
	 * 
	 * @return a path expression, never {@code null}
	 */
	public Expression getPath() {
		return path;
	}

	/**
	 * Retrieves a reflection field, specifying the internal target.
	 * 
	 * @return a reflection field, never {@code null}
	 */
	public Field getField() {
		return field;
	}
}
