package sudoku_game;

import java.util.Random;

/**
 * Instances of this class
 * represent Sudoku puzzles
 * of the medium difficulty
 * level
 * @author Alex Horejsi
 */
class MediumPuzzle extends Puzzle {
	/**
	 * Creates a medium Sudoku puzzle
	 * instance with the given
	 * completed Sudoku board
	 * and the default random
	 * number generator
	 * @param board A completed
	 * Sudoku puzzle
	 */
	public MediumPuzzle(Board board) {
		this(board, DefaultRNG.getDefaultGenerator());
	}
	
	/**
	 * Creates a medium Sudoku puzzle
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
	public MediumPuzzle(Board board, Random rng) {
		super(board, S_PathDifficultyAdjustor.getInstance(), 40, 43, 33, rng);
	}
	
	@Override
	public String getDifficulty() {
		return "MEDIUM";
	}
}