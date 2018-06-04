package sudoku_game;

import java.util.Random;

/**
 * Classes that implement this interface
 * are used to adjust generated Sudoku
 * puzzles for a given difficulty
 * setting. The exact difficulty is
 * determined by the parameters
 * @author Alex Horejsi
 */
@FunctionalInterface
public interface DifficultyAdjustor {
	/**
	 * Adjusts the given Sudoku puzzle
	 * for a specific difficulty
	 * @param board The Sudoku puzzle
	 * to be adjusted for difficulty
	 * @param rng The random number
	 * generator to determine the
	 * exact adjustments
	 * @param lowerRangeOnGivens A
	 * percentage indicating the minimum
	 * possible number of givens for the
	 * puzzle to start out with
	 * @param upperRangeOnGivens A
	 * percentage indicating the maximum
	 * possible number of givens for the
	 * puzzle to start out with
	 * @param lowerBoundOnGivensPerUnit
	 * A percentage indicating the minimum
	 * possible number of givens per
	 * row/column/box
	 */
	public void adjust(Board board, Random rng, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit);
}