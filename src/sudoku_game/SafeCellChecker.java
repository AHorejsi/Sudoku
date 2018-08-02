package sudoku_game;

/**
 * Classes that implement this
 * interface are meant to check
 * if a value is safe to be placed
 * into a particular cell
 * @author Alex Horejsi
 */
@FunctionalInterface
public interface SafeCellChecker {
	/**
	 * Checks if the given Sudoku board
	 * can have the given value placed
	 * into the cell at the given row
	 * and column indices
	 * @param table The Sudoku board to
	 * be tested
	 * @param value The value to be used
	 * to see if it can be safely placed
	 * at the given row and column indices
	 * @param row The row index of the cell
	 * to be tested
	 * @param col The column index of the
	 * cell to be tested
	 * @param boxRow The number of rows in
	 * a particular box of the given Sudoku
	 * board
	 * @param boxCol The number of columns
	 * in a particular box of the given
	 * Sudoku board
	 * @return <tt>true</tt> if the given cell
	 * can have the given value safely placed
	 * into the given cell, <tt>false</tt>
	 * otherwise
	 */
	public boolean safe(Cell[][] table, char value, int row, int col, int boxRow, int boxCol);
}