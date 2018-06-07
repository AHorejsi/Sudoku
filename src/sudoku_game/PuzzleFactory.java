package sudoku_game;

import java.util.Random;

public interface PuzzleFactory {
	public Puzzle createPuzzle(String info, Random rng);
	
	public default Puzzle createPuzzle(String info) {
		return this.createPuzzle(info, DefaultRNG.getDefaultGenerator());
	}
}