package sudoku_game;

/**
 * Classes that implement this interface
 * are intended to be used to check if
 * a Sudoku board has a unique solution
 * @author Alex Horejsi
 */
@FunctionalInterface
public interface Solver {
	/**
	 * Checks if the given board has
	 * a unique solution
	 * @param board The board to be checked
	 * @return <tt>true</tt> if the board has
	 * a unique solution, <tt>false</tt> otherwise
	 */
	boolean hasUniqueSolution(Board board);
}