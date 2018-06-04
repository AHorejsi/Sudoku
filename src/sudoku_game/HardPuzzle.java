package sudoku_game;

import java.util.Random;

public class HardPuzzle extends Puzzle {
	public HardPuzzle(Board board, DifficultyAdjustor adjustor) {
		this(board, adjustor, DefaultRNG.getDefaultGenerator());
	}
	
	public HardPuzzle(Board board, DifficultyAdjustor adjustor, Random rng) {
		super(board);
		adjustor.adjust(board, rng, 35, 38, 22);
	}
	
	@Override
	public String getDifficulty() {
		return "HARD";
	}
}