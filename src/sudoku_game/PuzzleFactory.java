package sudoku_game;

import java.util.Collection;
import java.util.Random;

public interface PuzzleFactory {
	public Puzzle createPuzzle(String info, Random rng, Collection<Mixer> mixers);
	
	public default Puzzle createPuzzle(String info) {
		return this.createPuzzle(info, DefaultRNG.getDefaultGenerator(), null);
	}
	
	public default Puzzle createPuzzle(String info, Collection<Mixer> mixers) {
		return this.createPuzzle(info, DefaultRNG.getDefaultGenerator(), mixers);
	}
}