package sudoku_game;

/**
 * Instances of this class
 * are thrown when attempting
 * to edit the value of a cell
 * that has been set to be
 * non-editable
 * @author Alex Horejsi
 */
public class NoneditableCellException extends IllegalStateException {
	private static final long serialVersionUID = 3750707403323100909L;
	
	/**
	 * Creates a {@code NoneditableCellException}
	 * with no detail message and no identified
	 * cause
	 */
	public NoneditableCellException() {
		super();
	}
	
	/**
	 * Creates a {@code NoneditableCellException}
	 * with a detail message, but no identified
	 * cause
	 * @param message The detail message for this
	 * exception
	 */
	public NoneditableCellException(String message) {
		super(message);
	}
	
	/**
	 * Creates a {@code NoneditableCellException}
	 * with an identified cause, but no detail
	 * message
	 * @param cause The identified cause of this
	 * exception
	 */
	public NoneditableCellException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Creates a {@code NoneditableCellException}
	 * with a detail message and an identified
	 * cause
	 * @param message The detail message for this
	 * exception
	 * @param cause The identified cause of this
	 * exception
	 */
	public NoneditableCellException(String message, Throwable cause) {
		super(message, cause);
	}
}