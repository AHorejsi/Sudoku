package sudoku_game;

import java.util.Random;

public class EasyPuzzle extends Puzzle {
	public EasyPuzzle(Board board) {
		this(board, DefaultRNG.getDefaultGenerator());
	}
	
	public EasyPuzzle(Board board, Random rng) {
		super(board);
		EasyAdjustor.getInstance().adjust(board, rng);
	}
	
	@Override
	public String getDifficulty() {
		return "EASY";
	}
}