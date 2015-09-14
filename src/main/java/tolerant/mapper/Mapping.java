package tolerant.mapper;

import java.lang.reflect.Field;

import tolerant.mapper.Path.Expression;

public final class Mapping {

	private final Field field;
	private final Expression path;

	public Mapping(Expression path, Field field) {
		this.path = path;
		this.field = field;
	}

	public Expression getPath() {
		return path;
	}

	public Field getField() {
		return field;
	}
}
