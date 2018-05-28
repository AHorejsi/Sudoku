package sudoku_game;

import java.util.Random;
import sudoku_game.DefaultRNG;

/**
 * Classes that implement this interface are
 * intended to mix up a generated Sudoku
 * puzzle in order to increase variation
 * @author Alex Horejsi
 */
@FunctionalInterface
public interface Mixer {
	/**
	 * Mixes up the given Sudoku puzzle
	 * @param table The Sudoku puzzle to
	 * be mixed up
	 * @param rng The random number generator
	 * used to mix up the given Sudoku puzzle
	 */
	public void mix(Cell[][] table, Random rng);
	
	/**
	 * Mixes up the given Sudoku puzzle.
	 * Uses the default random number
	 * generator
	 * @param table The Sudoku puzzle to
	 * be mixed up
	 */
	public default void mix(Cell[][] table) {
		this.mix(table, DefaultRNG.getDefaultGenerator());
	}
}