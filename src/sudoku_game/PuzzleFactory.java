package sudoku_game;

import java.util.Collection;
import java.util.Random;

/**
 * Constructs a Sudoku puzzle based
 * on the given parameters
 * @author Alex Horejsi
 */
public interface PuzzleFactory {
	/**
	 * Creates a Sudoku puzzle
	 * based on the given info,
	 * uses the given random number
	 * generator and mixes the
	 * puzzle up with the given
	 * instances of {@code Mixer}
	 * @param info Information
	 * describing the puzzle that
	 * is desired
	 * @param rng The random number
	 * generator to be used when
	 * generating the puzzle
	 * @param mixers The instances
	 * of {@code Mixer} to be used
	 * to mix up the puzzle. Can be
	 * <tt>null</tt> if no mixing
	 * is desired
	 * @return An instance of
	 * {@code Puzzle} that a user
	 * can play
	 */
	public Puzzle createPuzzle(String info, Random rng, Collection<Mixer> mixers);
	
	/**
	 * Creates a Sudoku puzzle based
	 * on the given info. The default
	 * random number generator is used
	 * and no mixers are used
	 * @param info Information
	 * describing the puzzle that is desired
	 * @return An instance of {@code Puzzle}
	 * that a user can play
	 */
	public default Puzzle createPuzzle(String info) {
		return this.createPuzzle(info, DefaultRNG.getDefaultGenerator(), null);
	}
	
	/**
	 * Creates a Sudoku puzzle
	 * based on the given info and
	 * mixes the puzzle up with the
	 * given instances of {@code Mixer}
	 * @param info Information
	 * describing the puzzle that is desired
	 * @param mixers The instances of
	 * {@code Mixer} to be used to mix
	 * up the puzzle. Can be <tt>null</tt>
	 * if no mixing is desired
	 * @return An instance of {@code Puzzle}
	 * that a user can play
	 */
	public default Puzzle createPuzzle(String info, Collection<Mixer> mixers) {
		return this.createPuzzle(info, DefaultRNG.getDefaultGenerator(), mixers);
	}
}