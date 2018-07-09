package sudoku_game;

import java.util.Random;

/**
 * Default random number generator holder to be used
 * should a user opt to not provide one themselves
 * @author Alex Horejsi
 */
class DefaultRNG {
	private static Random defaultGenerator = new ImmutableRandom();
	
	private DefaultRNG() {}
	
	/**
	 * Returns the default random number generator
	 * @return The default random number generator
	 */
	public static Random getDefaultGenerator() {
		return DefaultRNG.defaultGenerator;
	}
	
	private static class ImmutableRandom extends Random {
		private static final long serialVersionUID = -6105475001788975491L;
		private boolean set = false;
		
		private ImmutableRandom() {
			super();
		}
		
		@Override
		public void setSeed(long seed) throws UnsupportedOperationException {
			if (this.set)
				throw new UnsupportedOperationException();
			super.setSeed(seed);
			this.set = true;
		}
	}
}