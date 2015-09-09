package tolerant.mapper;

import java.lang.reflect.Field;

public final class Mapping {

	private final Path path;
	private final Field field;

	public Mapping(Path path, Field field) {
		this.path = path;
		this.field = field;
	}

	public Path getPath() {
		return path;
	}

	public Field getField() {
		return field;
	}
}
