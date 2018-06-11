package sudoku_game;

import java.util.Random;

@FunctionalInterface
public interface Mixer {
	public void mix(Board board, Random rng);
	
	public default void mix(Board board) {
		this.mix(board, DefaultRNG.getDefaultGenerator());
	}
}