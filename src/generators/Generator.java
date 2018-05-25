package generators;

import java.util.Random;

/**
 * Classes that implement this interface
 * are used to generate Sudoku puzzles
 * @author Alex Horejsi
 */
@FunctionalInterface
public interface Generator {
	/**
	 * Generates a Sudoku puzzle
	 * @param rng The random
	 * number generator to use
	 * to generate to puzzle
	 * @return A Sudoku puzzle
	 * represented by a 2D <tt>char</tt>
	 * array
	 */
	public char[][] generate(Random rng);
}