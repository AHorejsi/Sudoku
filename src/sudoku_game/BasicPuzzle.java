package sudoku_game;

import java.util.Random;

/**
 * Instances of this class
 * represent Sudoku puzzles
 * of the basic difficulty
 * level
 * @author Alex Horejsi
 */
public class BasicPuzzle extends Puzzle {
	private static String difficulty = "Basic";
	
	/**
	 * Creates a basic Sudoku puzzle
	 * instance with the given
	 * completed Sudoku board
	 * and the default random
	 * number generator
	 * @param board A completed
	 * Sudoku puzzle
	 */
	BasicPuzzle(Board board) {
		this(board, DefaultRNG.getDefaultGenerator());
	}
	
	/**
	 * Creates a basic Sudoku puzzle
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
	BasicPuzzle(Board board, Random rng) {
		super(board, RandomizedDifficultyAdjustor.getInstance(), 58, 68, 55, rng, BasicPuzzle.difficulty);
	}
}