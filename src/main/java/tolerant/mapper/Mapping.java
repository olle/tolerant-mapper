package tolerant.mapper;

import java.lang.reflect.Field;

public class Mapping {

	private final Path path;
	private final Field field;

	public Mapping(Path path, Field field) {
		this.path = path;
		this.field = field;
	}

	public String getPath() {
		return this.path.value();
	}

	public Field getField() {
		return this.field;
	}

}
