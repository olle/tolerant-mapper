package tolerant.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation describing a data-mapping from an external tree-like structure, to
 * the annotated field.
 * 
 * <p>
 * Annotated fields can be of the {@code Optional} type.
 * </p>
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Path {

	/**
	 * The dot-separated path for this annotated field.
	 * 
	 * @return path string
	 */
	String value();

	/**
	 * Encapsulates a path-expression, that can be used to do map lookup or
	 * traversal.
	 * 
	 * <p>
	 * An expression is typically a tree-like reference, and in our simple case,
	 * a dot-separated string, where each string is a key in a map. A dot means
	 * to traverse into either another map or to lookup a value, based on that
	 * string key. You are certainly getting this.
	 * </p>
	 */
	public static final class Expression {

		private final String value;

		private Expression(String value) {
			this.value = value;
		}

		public String value() {
			return this.value;
		}

		public static Expression valueOf(String value) {
			return new Expression(value);
		}

		public static Expression valueOf(Path annotation) {
			return valueOf(annotation.value());
		}
	}
}
