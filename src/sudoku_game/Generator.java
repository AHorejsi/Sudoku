package sudoku_game;

import java.util.Random;

/**
 * Classes that implement this interface
 * are used to generate completed Sudoku 
 * puzzles
 * @author Alex Horejsi
 */
@FunctionalInterface
interface Generator {
	/**
	 * Generates a completed 
	 * Sudoku puzzle
	 * @param rng The random
	 * number generator to use
	 * to generate to puzzle
	 * @return A completed Sudoku 
	 * puzzle
	 */
	Cell[][] generate(Random rng);
	
	/**
	 * Generates a completed
	 * Sudoku puzzle. Uses the
	 * default random number
	 * generator
	 * @return A completed Sudoku 
	 * puzzle
	 */
	default Cell[][] generate() {
		return this.generate(DefaultRNG.getDefaultGenerator());
	}
}