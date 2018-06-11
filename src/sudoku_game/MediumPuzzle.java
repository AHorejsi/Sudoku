package sudoku_game;

import java.util.Random;

class MediumPuzzle extends Puzzle {
	public MediumPuzzle(Board board) {
		this(board, DefaultRNG.getDefaultGenerator());
	}
	
	public MediumPuzzle(Board board, Random rng) {
		super(board, S_PathDifficultyAdjustor.getInstance(), 40, 43, 33, rng);
	}
	
	@Override
	public String getDifficulty() {
		return "MEDIUM";
	}
}