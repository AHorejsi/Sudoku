package sudoku_game;

import java.util.Random;

/**
 * Classes that implement this interface shall
 * remove values with a given set of constraints
 * in order to adjust a Sudoku puzzle for a given
 * difficulty. All implementations must generate
 * Sudoku puzzles with unique solutions
 * @author Alex Horejsi
 */
@FunctionalInterface
public interface DifficultyAdjustor {
	/**
	 * Adjusts a Sudoku puzzle for a given set
	 * of difficulty parameters
	 * @param board The Sudoku board to be
	 * modified for difficulty
	 * @param rng The random number generator
	 * to use
	 * @param lowerRangeOnGivens The minimum
	 * number of given values this Sudoku
	 * puzzle may have
	 * @param upperRangeOnGivens The maximum
	 * number of given values this Sudoku
	 * puzzle may have
	 * @param lowerBoundOnGivensPerUnit The
	 * minimum number of givens any row, column,
	 * or box may have
	 */
	public void adjust(Board board, Random rng, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit);
	
	/**
	 * Adjusts a Sudoku puzzle for a given set
	 * of difficulty parameters. Uses the default
	 * random number generator
	 * @param board The Sudoku board to be
	 * modified for difficulty
	 * @param lowerRangeOnGivens The minimum
	 * number of given values this Sudoku
	 * puzzle may have
	 * @param upperRangeOnGivens The maximum
	 * number of given values this Sudoku
	 * puzzle may have
	 * @param lowerBoundOnGivensPerUnit The
	 * minimum number of givens any row, column,
	 * or box may have
	 */
	public default void adjust(Board board, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit) {
		this.adjust(board, DefaultRNG.getDefaultGenerator(), lowerRangeOnGivens, upperRangeOnGivens, lowerBoundOnGivensPerUnit);
	}
	
	/**
	 * Sets all of empty cells to be editable and
	 * all non-empty cells to be read-only
	 * @param table The Sudoku board to have its
	 * cells set as editable or read-only
	 */
	public static void setEditableCells(Cell[][] table) {
		for (int i = 0 ; i < table.length ; i++) {
			for (int j = 0 ; j < table.length ; j++) {
				if (table[i][j].getValue() == '\u0000')
					table[i][j].setEditable(true);
			}
		}
	}
}