package sudoku_game;

import java.util.Random;

public class AllMixer implements Mixer {
	private static Mixer mixer = new AllMixer();
	
	private AllMixer() {}
	
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