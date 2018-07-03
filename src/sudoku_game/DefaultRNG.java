package sudoku_game;

import java.util.Random;

/**
 * Default random number generator holder to be used
 * should a user opt to not provide one themselves
 * @author Alex Horejsi
 */
public class DefaultRNG {
	private static Random defaultGenerator = new ImmutableRandom();
	
	/**
	 * Returns the default random number generator
	 * @return The default random number generator
	 */
	public static Random getDefaultGenerator() {
		return DefaultRNG.defaultGenerator;
	}
	
	private static class ImmutableRandom extends Random {
		private static final long serialVersionUID = -5569275294824071575L;

		@Override
		public void setSeed(long seed) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Default random number generator cannot have its seed set");
		}
	}
}