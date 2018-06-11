package sudoku_game;

/**
 * Classes that implement this interface
 * describe the dimensions of a given
 * Sudoku puzzle's boxes
 * @author Alex Horejsi
 */
interface BoxInfo {
	/**
	 * Returns the number of rows a given
	 * Sudoku puzzle's boxes have
	 * @return The number of rows a given
	 * Sudoku puzzle's boxes have
	 */
	public int rowSize();
	
	/**
	 * Returns the number of columns a
	 * given Sudoku puzzle's boxes have
	 * @return The number of columns a
	 * given Sudoku puzzle's boxes have
	 */
	public int colSize();
}