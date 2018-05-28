package generation;

import java.util.Random;
import sudoku_game.DefaultRNG;

/**
 * Classes that implement this interface
 * are used to adjust generated Sudoku
 * puzzles 
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
	public Cell[][] adjust(char[][] table, Random rng);
	
	/**
	 * Adjusts the given Sudoku puzzle
	 * for a specific difficulty. Uses
	 * the default random number generator
	 * @param table The Sudoku puzzle to be adjusted for difficulty
	 */
	public default Cell[][] adjust(char[][] table) {
		return this.adjust(table, DefaultRNG.getDefaultGenerator());
	}
}