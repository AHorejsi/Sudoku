package sudoku_game;

/**
 * Instances of this class
 * represent an implementation
 * that checks if a Sudoku puzzle
 * still has the specified
 * lower bound per unit during
 * difficulty adjustment
 * @author Alex Horejsi
 */
@FunctionalInterface
public interface LowerBoundChecker {
	/**
	 * Checks if the cell at the given
	 * row and column indices has the
	 * satisfies the specified lower
	 * bound in its corresponding
	 * row, column and box
	 * @param board The board to be
	 * checked
	 * @param row The row index of
	 * the cell to be checked
	 * @param col The column index
	 * of the cell to be checked
	 * @param lowerBound The lower
	 * bound to be satisfied
	 * @return <tt>true</tt> if the
	 * specified lower bound is met,
	 * <tt>false</tt> otherwise
	 */
	public boolean check(Board board, int row, int col, int lowerBound);
}