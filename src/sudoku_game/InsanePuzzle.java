package sudoku_game;

import java.util.Random;

class InsanePuzzle extends Puzzle {
	public InsanePuzzle(Board board) {
		this(board, DefaultRNG.getDefaultGenerator());
	}
	
	public InsanePuzzle(Board board, Random rng) {
		super(board, SequentialDifficultyAdjustor.getInstance(), 21, 33, 0, rng);
	}
	
	@Override
	public String getDifficulty() {
		return "INSANE";
	}
}