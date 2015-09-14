package tolerant.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Dot-separated string, the value mapping for the annotated field.
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
