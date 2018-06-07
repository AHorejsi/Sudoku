package sudoku_game;

import java.util.Random;

public class BasicPuzzle extends Puzzle {
	public BasicPuzzle(Board board) {
		this(board, DefaultRNG.getDefaultGenerator());
	}
	
	public BasicPuzzle(Board board, Random rng) {
		super(board, RandomizedDifficultyAdjustor.getInstance(), 58, 68, 55, rng);
	}
	
	@Override
	public String getDifficulty() {
		return "BASIC";
	}
}