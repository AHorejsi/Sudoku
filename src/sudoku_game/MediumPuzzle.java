package sudoku_game;

import java.util.Random;

public class MediumPuzzle extends Puzzle {
	public MediumPuzzle(Board board, DifficultyAdjustor adjustor) {
		this(board, adjustor, DefaultRNG.getDefaultGenerator());
	}
	
	public MediumPuzzle(Board board, DifficultyAdjustor adjustor, Random rng) {
		super(board);
		adjustor.adjust(board, rng, 40, 43, 33);
	}
	
	@Override
	public String getDifficulty() {
		return "MEDIUM";
	}
}