package sudoku_game;

import java.util.Random;

/**
 * Instances of this class
 * represent twelve-by-twelve
 * Sudoku puzzles
 * @author Alex Horejsi
 */
public class Board12x12 extends Board {
	/**
	 * Constructs a twelve-by-twelve Sudoku
	 * board using the default random number
	 * generator
	 */
	Board12x12() {
		super(LegalValues12x12.getInstance(),
			  AlphanumericChecker.getInstance(),
			  BoxInfo12x12.getInstance(),
			  Generator12x12.getInstance().generate());
	}
	
	/**
	 * Constructs a twelve-by-twelve Sudoku
	 * board using the given random
	 * number generator
	 * @param rng The random number
	 * generator to be used to generate
	 * the Sudoku board
	 * @throws NullPointerException Thrown
	 * if the given random number
	 * generator is <tt>null</tt>
	 */
	Board12x12(Random rng) {
		super(LegalValues12x12.getInstance(),
			  AlphanumericChecker.getInstance(),
			  BoxInfo12x12.getInstance(),
			  Generator12x12.getInstance().generate(rng));
	}
	
	/**
	 * Constructs a twelve-by-twelve Sudoku
	 * board using the given 2D array
	 * of cells
	 * @param table A 2D array of cells
	 * that will serve as this Sudoku board's
	 * cells
	 * @throws NullPointerException Thrown
	 * if the given 2D array of cells is
	 * <tt>null<tt>
	 */
	Board12x12(Cell[][] table) {
		super(LegalValues12x12.getInstance(),
			  AlphanumericChecker.getInstance(),
			  BoxInfo12x12.getInstance(),
			  table);
	}
}