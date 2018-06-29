package sudoku_game;

/**
 * Checks if a given Sudoku puzzle is solved
 * @author Alex Horejsi
 */
@FunctionalInterface
interface Checker {
	/**
	 * Checks if the given Sudoku board
	 * is solved
	 * @param board The Sudoku board to
	 * be checked for whether or not it
	 * is solved
	 * @return <tt>true</tt> if the given
	 * Sudoku board is solved, <tt>false</tt>
	 * otherwise
	 */
	boolean isSolved(Board board);
}