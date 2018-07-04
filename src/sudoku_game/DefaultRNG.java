package sudoku_game;

import java.util.Random;

/**
 * Default random number generator holder to be used
 * should a user opt to not provide one themselves. The
 * instance of {@code Random} provided by this class
 * cannot have its seed set
 * @author Alex Horejsi
 */
public class DefaultRNG {
	private static Random defaultGenerator = new Random();
	
	private DefaultRNG() {}
	
	/**
	 * Returns the default random number generator
	 * @return The default random number generator
	 */
	public static Random getDefaultGenerator() {
		return DefaultRNG.defaultGenerator;
	}
}