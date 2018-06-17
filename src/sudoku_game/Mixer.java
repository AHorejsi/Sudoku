package sudoku_game;

import java.util.Random;

/**
 * Mixes up a given Sudoku puzzle by
 * moving cells around in a way that
 * maintains the rules of Sudoku
 * @author Alex Horejsi
 */
@FunctionalInterface
public interface Mixer {
	/**
	 * Mixes up the given Sudoku board
	 * and uses the given random number
	 * generator to do so
	 * @param board The Sudoku board to
	 * be mixed up
	 * @param rng The random number
	 * generator to be used in the mixing
	 */
	public void mix(Board board, Random rng);
	
	/**
	 * Mixes up the given Sudoku board
	 * and uses the default random
	 * number generator
	 * @param board The Sudoku board to
	 * be mixed up
	 */
	public default void mix(Board board) {
		this.mix(board, DefaultRNG.getDefaultGenerator());
	}
}