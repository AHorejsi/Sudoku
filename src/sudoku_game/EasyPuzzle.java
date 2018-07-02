package sudoku_game;

import java.util.Random;

/**
 * Instances of this class
 * represent Sudoku puzzles
 * of the easy difficulty
 * level
 * @author Alex Horejsi
 */
class EasyPuzzle extends Puzzle {
	/**
	 * Creates a easy Sudoku puzzle
	 * instance with the given
	 * completed Sudoku board
	 * and the default random
	 * number generator
	 * @param board A completed
	 * Sudoku puzzle
	 */
	EasyPuzzle(Board board) {
		this(board, DefaultRNG.getDefaultGenerator());
	}
	
	/**
	 * Creates a easy Sudoku puzzle
	 * instance with the given
	 * completed Sudoku board
	 * and the given random
	 * number generator
	 * @param board A completed
	 * Sudoku puzzle
	 * @param rng The random
	 * number generator to be used
	 * in the removing a values
	 * from the given completed
	 * Sudoku puzzle
	 */
	EasyPuzzle(Board board, Random rng) {
		super(board, RandomizedDifficultyAdjustor.getInstance(), 44, 57, 44, rng);
	}
	
	@Override
	public String getDifficulty() {
		return "EASY";
	}
}