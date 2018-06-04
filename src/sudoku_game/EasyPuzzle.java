package sudoku_game;

import java.util.Random;

public class EasyPuzzle extends Puzzle {
	public EasyPuzzle(Board board, DifficultyAdjustor adjustor) {
		this(board, adjustor, DefaultRNG.getDefaultGenerator());
	}
	
	public EasyPuzzle(Board board, DifficultyAdjustor adjustor, Random rng) {
		super(board);
		adjustor.adjust(board, rng, 44, 57, 44);
	}
	
	@Override
	public String getDifficulty() {
		return "EASY";
	}
}