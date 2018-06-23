package sudoku_game;

import java.util.Random;

/**
 * Instances of this class
 * represent Sudoku puzzles
 * of the insane difficulty
 * level
 * @author Alex Horejsi
 */
class InsanePuzzle extends Puzzle {
	/**
	 * Creates a insane Sudoku puzzle
	 * instance with the given
	 * completed Sudoku board
	 * and the default random
	 * number generator
	 * @param board A completed
	 * Sudoku puzzle
	 */
	public InsanePuzzle(Board board) {
		this(board, DefaultRNG.getDefaultGenerator());
	}
	
	/**
	 * Creates a insane Sudoku puzzle
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
	public InsanePuzzle(Board board, Random rng) {
		super(board, SequentialDifficultyAdjustor.getInstance(), 21, 33, 0, rng);
	}
	
	@Override
	public String getDifficulty() {
		return "INSANE";
	}
}