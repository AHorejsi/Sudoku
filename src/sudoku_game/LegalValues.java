package sudoku_game;

/**
 * Classes that implement this interface
 * are meant to define the <tt>char</tt>
 * values that can legally be placed into
 * a Sudoku puzzle with certain dimensions
 * @author Alex Horejsi
 */
interface LegalValues {
	/**
	 * Checks if the given <tt>char</tt>
	 * value is legal for this Sudoku
	 * puzzle
	 * @param value The value to be checked
	 * for legality
	 * @return <tt>true</tt> if the given
	 * <tt>char</tt> value is legal for a
	 * Sudoku puzzle
	 */
	public boolean isLegal(char value);
	
	/**
	 * Returns a <tt>char</tt> array
	 * containing all of the legal <tt>char</tt>
	 * values for this Sudoku puzzle
	 * @return A <tt>char</tt> array containing
	 * all of the legal <tt>char</tt> values for
	 * a Sudoku puzzle
	 */
	public char[] getLegalValues();
}