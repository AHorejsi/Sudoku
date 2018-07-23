package sudoku_game;

import java.util.Random;

/**
 * Instances of this class
 * represent Sudoku puzzles
 * of the medium difficulty
 * level
 * @author Alex Horejsi
 */
public class MediumPuzzle extends Puzzle {
	private static String difficulty = "Medium";
	
	/**
	 * Creates a medium Sudoku puzzle
	 * instance with the given
	 * completed Sudoku board
	 * and the default random
	 * number generator
	 * @param board A completed
	 * Sudoku puzzle
	 */
	MediumPuzzle(Board board) {
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
	 * in the removing of values
	 * from the given completed
	 * Sudoku puzzle
	 */
	MediumPuzzle(Board board, Random rng) {
		super(board, JumpCellDifficultyAdjustor.getInstance(), 40, 43, 33, rng, MediumPuzzle.difficulty);
	}
}