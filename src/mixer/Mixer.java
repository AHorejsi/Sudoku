package mixer;

import java.util.Random;

/**
 * Classes that implement this interface are
 * intended to mix up a generated Sudoku
 * puzzle in order to increase variation
 * among Sudoku puzzles
 * @author Alex Horejsi
 */
@FunctionalInterface
public interface Mixer {
	/**
	 * Mixes up the given Sudoku puzzle
	 * @param table The <tt>char</tt> array
	 * that represents a Sudoku puzzle. Must
	 * be a square matrix
	 * @param rng The random number generator
	 * used to mix up the given Sudoku puzzle
	 */
	public void mix(char[][] table, Random rng);
}