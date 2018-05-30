package sudoku_game;

import java.util.Random;

/**
 * Classes that implement this interface
 * are used to adjust generated Sudoku
 * puzzles for a given difficulty
 * setting
 * @author Alex Horejsi
 */
@FunctionalInterface
public interface DifficultyAdjustor {
	/**
	 * Adjusts the given Sudoku puzzle
	 * for a specific difficulty
	 * @param table The Sudoku puzzle
	 * to be adjusted for difficulty
	 * @param rng The random number
	 * generator to determine the
	 * exact adjustments
	 */
	public void adjust(Board board, Random rng);
	
	/**
	 * Adjusts the given Sudoku puzzle
	 * for a specific difficulty. Uses
	 * the default random number generator
	 * @param table The Sudoku puzzle to be adjusted for difficulty
	 */
	public default void adjust(Board board) {
		this.adjust(board, DefaultRNG.getDefaultGenerator());
	}
}