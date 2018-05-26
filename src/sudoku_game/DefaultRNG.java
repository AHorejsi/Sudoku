package sudoku_game;

import java.util.Random;

/**
 * Default random number generator to be used
 * should a user opt to not provide one themselves
 * @author Alex Horejsi
 */
public class DefaultRNG {
	private static Random defaultGenerator = new Random();
	
	/**
	 * Returns the default random number generator
	 * @return The default random number generator
	 */
	public static Random getDefaultGenerator() {
		return DefaultRNG.defaultGenerator;
	}
}