package sudoku_game;

import java.util.Random;

/**
 * Instances of this class
 * represent Sudoku puzzles
 * of the easy difficulty
 * level
 * @author Alex Horejsi
 */
public class EasyPuzzle extends Puzzle {
	private static final String DIFFICULTY = "Easy";
	
	/**
	 * Creates an easy Sudoku puzzle
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
	 * in the removing of values
	 * from the given completed
	 * Sudoku puzzle
	 */
	EasyPuzzle(Board board, Random rng) {
		super(board, RandomizedDifficultyAdjustor.getInstance(), 44, 57, 44, rng, EasyPuzzle.DIFFICULTY);
	}
}