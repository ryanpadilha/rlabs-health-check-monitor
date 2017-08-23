package br.com.rlabs.exceptions;

/**
 * Unique Constraint Exception.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class UniqueConstraintException extends Exception {

	private static final long serialVersionUID = -235350999732162895L;

	public UniqueConstraintException() {
		super();
	}

	public UniqueConstraintException(String message) {
		super(message);
	}

	public UniqueConstraintException(String message, Throwable cause) {
		super(message, cause);
	}

	public UniqueConstraintException(Throwable cause) {
		super(cause);
	}
}
