package sudoku_game;

import java.util.Random;

/**
 * Instances of this class
 * represent Sudoku puzzles
 * of the hard difficulty
 * level
 * @author Alex Horejsi
 */
public class HardPuzzle extends Puzzle {
	private static final String DIFFICULTY = "Hard";
	
	/**
	 * Creates a hard Sudoku puzzle
	 * instance with the given
	 * completed Sudoku board
	 * and the default random
	 * number generator
	 * @param board A completed
	 * Sudoku puzzle
	 */
	HardPuzzle(Board board) {
		this(board, DefaultRNG.getDefaultGenerator());
	}
	
	/**
	 * Creates a hard Sudoku puzzle
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
	HardPuzzle(Board board, Random rng) {
		super(board, S_PathDifficultyAdjustor.getInstance(), 35, 38, 22, HardPuzzle.DIFFICULTY);
	}
}