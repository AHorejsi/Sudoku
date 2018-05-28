package sudoku_game;

import java.util.Random;

public class MediumPuzzle extends Puzzle {
	public MediumPuzzle(Board board) {
		this(board, DefaultRNG.getDefaultGenerator());
	}
	
	public MediumPuzzle(Board board, Random rng) {
		super(board);
		MediumAdjustor.getInstance().adjust(board, rng);
	}
	
	@Override
	public String getDifficulty() {
		return "MEDIUM";
	}
}