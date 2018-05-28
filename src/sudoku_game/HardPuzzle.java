package sudoku_game;

import java.util.Random;

public class HardPuzzle extends Puzzle {
	public HardPuzzle(Board board) {
		this(board, DefaultRNG.getDefaultGenerator());
	}
	
	public HardPuzzle(Board board, Random rng) {
		super(board);
		HardAdjustor.getInstance().adjust(board, rng);
	}
	
	@Override
	public String getDifficulty() {
		return "HARD";
	}
}