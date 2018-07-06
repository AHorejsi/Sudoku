package sudoku_game;

import java.util.Objects;

/**
 * Instances of this class 
 * represent a Sudoku board
 * @author Alex Horejsi
 */
public abstract class Board {
	Cell[][] table;
	LegalValues legalValues;
	Checker checker;
	BoxInfo boxInfo;
	
	/**
	 * Creates a Sudoku board
	 * @param legalValues Object that provides
	 * information on the legal values for
	 * this Sudoku board
	 * @param checker Object that checks if
	 * this Sudoku board is solved
	 * @param boxInfo Object that provides basic
	 * information about the boxes of this Sudoku
	 * board
	 * @param table The cells that shall make up
	 * this Sudoku board
	 * @throws NullPointerException Thrown if any
	 * of the parameters are <tt>null</tt>
	 */
	protected Board(LegalValues legalValues, Checker checker, BoxInfo boxInfo, Cell[][] table) throws NullPointerException {
		this.legalValues = Objects.requireNonNull(legalValues);
		this.checker = Objects.requireNonNull(checker);
		this.boxInfo = Objects.requireNonNull(boxInfo);
		this.table = Objects.requireNonNull(table);
	}
	
	/**
	 * Obtains the value stored at the given
	 * row and column position
	 * @param row The row index of the value
	 * to be returned
	 * @param col The column index of the
	 * value to be returned
	 * @return The value at the given row and
	 * column indices
	 * @throws IndexOutOfBoundsException Thrown
	 * if either the given row index or the given
	 * column index are outside the bounds of this
	 * Sudoku board
	 */
	public char getValueAt(int row, int col) throws IndexOutOfBoundsException {
		this.outOfBounds(row, col);
		return this.table[row][col].getValue();
	}
	
	/**
	 * Checks if the {@code Cell} at the given
	 * row and column indices is editable
	 * @param row The row index of the
	 * {@code Cell} to be checked
	 * @param col The column index of the
	 * {@code Cell} to be checked
	 * @return <tt>true</tt> if the {@code Cell}
	 * at the given row and column indices is
	 * editable, <tt>false</tt> otherwise
	 * @throws IndexOutOfBoundsException Thrown
	 * if either the given row index or the given
	 * column index are outside the bounds of this
	 * Sudoku board
	 */
	public boolean editableCellAt(int row, int col) throws IndexOutOfBoundsException {
		this.outOfBounds(row, col);
		return this.table[row][col].isEditable();
	}
	
	/**
	 * Sets the value for the {@code Cell}
	 * at the given row and column indices
	 * @param value The value to be placed
	 * into the given {@code Cell}
	 * @param row The row index of the
	 * {@code Cell} to have its value
	 * changed
	 * @param col The column index of
	 * the {@code Cell} to have its value
	 * changed
	 * @throws IndexOutOfBoundsException Thrown
	 * if either the given row index or the given
	 * column index are outside the bounds of this
	 * Sudoku board
	 * @throws IllegalArgumentException Thrown if
	 * the given value is not legal for this Sudoku
	 * board
	 * @throws NoneditableCellException Thrown if
	 * the {@code Cell} to be edited has been set
	 * as non-editable
	 */
	public void setValueAt(char value, int row, int col) throws IndexOutOfBoundsException, 
			IllegalArgumentException, NoneditableCellException {
		if (!this.isLegalValue(value))
			throw new IllegalArgumentException("Illegal value: " + value);
		this.noneditable(row, col);
		this.outOfBounds(row, col);
		this.table[row][col].setValue(value);
	}
	
	/**
	 * Removes the value contained in the
	 * {@code Cell} at the given row and
	 * column indices
	 * @param row The row index of the
	 * {@code Cell} to have its value
	 * removed
	 * @param col The column index of
	 * the value to be removed
	 * @throws IndexOutOfBoundsException Thrown
	 * if either the given row index or the given
	 * column index are outside the bounds of this
	 * Sudoku board
	 * @throws NoneditableCellException Thrown if
	 * the {@code Cell} to be edited has been set
	 * as non-editable
	 */
	public void deleteValueAt(int row, int col) throws IndexOutOfBoundsException, NoneditableCellException {
		this.noneditable(row, col);
		this.outOfBounds(row, col);
		this.table[row][col].setEmptyValue();
	}
	
	/**
	 * Returns the dimensions 
	 * of this Sudoku board
	 * @return The dimensions 
	 * of this Sudoku board
	 */
	public int getDimensions() {
		return this.table.length;
	}
	
	/**
	 * Checks if the given value is
	 * legal for this Sudoku board
	 * @param value The value to be
	 * checked for legality
	 * @return <tt>true</tt> if the
	 * given value is legal for this
	 * Sudoku board, <tt>false</tt>
	 * if not
	 */
	public boolean isLegalValue(char value) {
		return this.legalValues.isLegal(value);
	}
	
	/**
	 * Checks if this Sudoku board
	 * is solved
	 * @return <tt>true</tt> if this
	 * Sudoku board is solved,
	 * <tt>false</tt> if not
	 */
	public boolean isSolved() {
		return this.checker.isSolved(this);
	}
	
	private void outOfBounds(int row, int col) {
		if (row < 0 || row >= this.table.length || col < 0 || col >= this.table.length)
			throw new IndexOutOfBoundsException();
	}
	
	private void noneditable(int row, int col) {
		if (!this.table[row][col].isEditable())
			throw new NoneditableCellException("Non-editable cell: row = " + row + ", column = " + col);
	}
	
	/**
	 * Returns the number of rows
	 * in this Sudoku board's boxes
	 * @return The number of rows
	 * in this Sudoku board's boxes
	 */
	public int rowSizeInBox() {
		return this.boxInfo.rowSize();
	}
	
	/**
	 * Returns the number of columns
	 * in thsi Sudoku board's boxes
	 * @return The number of columns
	 * in this Sudoku board's boxes
	 */
	public int colSizeInBox() {
		return this.boxInfo.colSize();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0 ; i < table.length ; i++) {
			for (int j = 0 ; j < table.length ; j++) {
				sb.append(this.table[i][j].getValue());
				sb.append(' ');
			}
			
			sb.append('\n');
		}
		
		return sb.toString();
	}
}