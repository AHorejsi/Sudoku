package sudoku_game;

/**
 * Instances of this class represent a single
 * cell within a Sudoku puzzle
 * @author Alex Horejsi
 */
public interface Cell {
	/**
	 * Obtains the value in this cell
	 * @return The value in this cell or
	 * '\u0000' if there is no value
	 */
	char getValue();
	
	/**
	 * Changes the value in this cell
	 * @param value The new value for this cell
	 * @throws NoneditableCellException Thrown if this
	 * cell is not allowed to be edited
	 */
	void setValue(char value) throws NoneditableCellException;
	
	/**
	 * Empties this cell so that it contains
	 * no value at all
	 * @throws NoneditableCellException Thrown if
	 * this cell is not allowed to be edited
	 */
	void setEmptyValue() throws NoneditableCellException;
	
	/**
	 * Checks if this cell is editable
	 * @return <tt>true</tt> if this cell
	 * is editable, <tt>false</tt> if not
	 */
	boolean isEditable();
	
	/**
	 * Empties this cell so that is contains
	 * no value at all. This method should only
	 * be called when constructing a new Sudoku
	 * puzzle, not when a user is editing a
	 * puzzle
	 */
	void setEmptyForSetUp();
	
	/**
	 * Changes the value in this cell. This method
	 * should only be called when constructing a
	 * new Sudoku puzzle, not when a user is editing
	 * a puzzle
	 * @param value The new value for this cell
	 */
	void setValueForSetUp(char value);
	
	/**
	 * Sets whether or not this cell will be editable
	 * when a user is editing a puzzle. This method
	 * should only be called when constructing a new
	 * Sudoku puzzle, not when a user is editing a
	 * puzzle
	 * @param editable Value indicating this cell's
	 * editability
	 */
	void setEditable(boolean editable);
}