package sudoku_game;

import java.util.Arrays;

/**
 * Classes that implement this interface
 * are meant to define the <tt>char</tt>
 * values that can legally be placed into
 * a Sudoku puzzle with certain dimensions
 * @author Alex Horejsi
 */
abstract class LegalValues {
	private char[] values;
	
	protected LegalValues(char[] values) {
		this.values = values;
	}
	
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
	public boolean isLegal(char value) {
		return Arrays.binarySearch(this.values, value) >= 0;
	}
	
	/**
	 * Returns a <tt>char</tt> array
	 * containing all of the legal <tt>char</tt>
	 * values for this Sudoku puzzle. The <tt>char</tt>
	 * array that is returned should be a independent
	 * of any data fields in this class
	 * @return A <tt>char</tt> array containing
	 * all of the legal <tt>char</tt> values for
	 * a Sudoku puzzle
	 */
	public char[] getValues() {
		return Arrays.copyOf(this.values, this.values.length);
	}
}