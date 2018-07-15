package sudoku_game;

import java.util.Random;

/**
 * Default random number generator holder to be used
 * should a user opt to not provide one themselves.
 * This random number generator cannot have its seed
 * custom set
 * @author Alex Horejsi
 */
public class DefaultRNG extends Random {
	private static final long serialVersionUID = 1775403578435790735L;
	private static Random defaultGenerator = new DefaultRNG();
	private boolean seedIsSet = false;
	
	private DefaultRNG() {}
	
	/**
	 * Returns the default random number generator
	 * @return The default random number generator
	 */
	public static Random getDefaultGenerator() {
		return DefaultRNG.defaultGenerator;
	}
	
	@Override
	public void setSeed(long seed) {
		if (this.seedIsSet)
			throw new UnsupportedOperationException("Default RNG cannot have its seed set");
		this.seedIsSet = true;
	}
}