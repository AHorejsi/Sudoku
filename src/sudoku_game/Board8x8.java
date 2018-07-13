package sudoku_game;

import java.util.Random;

/**
 * Instances of this class represent
 * eight-by-eight Sudoku boards
 * @author Alex Horejsi
 */
public class Board8x8 extends Board {
	/**
	 * Constructs an eight-by-eight Sudoku
	 * board using the default random number
	 * generator
	 */
	Board8x8() {
		super(LegalValues8x8.getInstance(),
			  NumericChecker.getInstance(),
			  BoxInfo8x8.getInstance(),
			  Generator8x8.getInstance().generate());
	}
	
	/**
	 * Constructs an eight-by-eight Sudoku
	 * board using the given random
	 * number generator
	 * @param rng The random number
	 * generator to be used to generate
	 * the Sudoku board
	 * @throws NullPointerException Thrown
	 * if the given random number
	 * generator is <tt>null</tt>
	 */
	Board8x8(Random rng) {
		super(LegalValues8x8.getInstance(),
			  NumericChecker.getInstance(),
			  BoxInfo8x8.getInstance(),
			  Generator8x8.getInstance().generate(rng));
	}
	
	/**
	 * Constructs an eight-by-eight Sudoku
	 * board using the given 2D array
	 * of cells
	 * @param table A 2D array of cells
	 * that will serve as this Sudoku board's
	 * cells
	 * @throws NullPointerException Thrown
	 * if the given 2D array of cells is
	 * <tt>null<tt>
	 */
	Board8x8(Cell[][] table) {
		super(LegalValues8x8.getInstance(),
			  NumericChecker.getInstance(),
			  BoxInfo8x8.getInstance(),
			  table);
	}
}