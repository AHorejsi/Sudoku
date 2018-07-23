package sudoku_game;

import java.util.Random;

/**
 * This class runs all instances of
 * {@code Mixer} on the given Sudoku
 * puzzle
 * @author Alex Horejsi
 */
public class AllMixer implements Mixer {
	private static Mixer mixer = new AllMixer();
	
	private AllMixer() {}
	
	/**
	 * Returns the single instance
	 * of {@code AllMixer}
	 * @return The single instance
	 * of {@code AllMixer}
	 */
	public static Mixer getInstance() {
		return AllMixer.mixer;
	}
	
	@Override
	public void mix(Board board, Random rng) {
		RotateMixer.getInstance().mix(board, rng);
		FlipMixer.getInstance().mix(board, rng);
		FlipBoxMixer.getInstance().mix(board, rng);
		SwapMixer.getInstance().mix(board, rng);
	}
}