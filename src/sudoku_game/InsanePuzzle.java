package sudoku_game;

import java.util.Random;

/**
 * Instances of this class
 * represent Sudoku puzzles
 * of the insane difficulty
 * level
 * @author Alex Horejsi
 */
public class InsanePuzzle extends Puzzle {
	private static final String DIFFICULTY = "Insane";
	
	/**
	 * Creates a insane Sudoku puzzle
	 * instance with the given
	 * completed Sudoku board
	 * and the default random
	 * number generator
	 * @param board A completed
	 * Sudoku puzzle
	 */
	InsanePuzzle(Board board) {
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
	 * in the removing of values
	 * from the given completed
	 * Sudoku puzzle
	 */
	InsanePuzzle(Board board, Random rng) {
		super(board, SequentialDifficultyAdjustor.getInstance(), 21, 33, 0, rng, InsanePuzzle.DIFFICULTY);
	}
}