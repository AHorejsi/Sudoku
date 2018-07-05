package sudoku_game;

import java.util.Random;

/**
 * Instances of this class
 * represent sixteen-by-sixteen
 * Sudoku puzzles
 * @author Alex Horejsi
 */
class Board16x16 extends Board {
	/**
	 * Constructs a sixteen-by-sixteen Sudoku
	 * board using the default random number
	 * generator
	 */
	Board16x16() {
		super(LegalValues16x16.getInstance(),
			  AlphanumericChecker.getInstance(),
			  BoxInfo16x16.getInstance(),
			  Generator16x16.getInstance().generate());
	}
	
	/**
	 * Constructs a sixteen-by-sixteen Sudoku
	 * board using the given random
	 * number generator
	 * @param rng The random number
	 * generator to be used to generate
	 * the Sudoku board
	 * @throws NullPointerException Thrown
	 * if the given random number
	 * generator is <tt>null</tt>
	 */
	Board16x16(Random rng) {
		super(LegalValues16x16.getInstance(),
			  AlphanumericChecker.getInstance(),
			  BoxInfo16x16.getInstance(),
			  Generator16x16.getInstance().generate(rng));
	}
	
	/**
	 * Constructs a sixteen-by-sixteen Sudoku
	 * board using the given 2D array
	 * of cells
	 * @param table A 2D array of cells
	 * that will serve as this Sudoku board's
	 * cells
	 * @throws NullPointerException Thrown
	 * if the given 2D array of cells is
	 * <tt>null<tt>
	 */
	Board16x16(Cell[][] table) {
		super(LegalValues16x16.getInstance(),
			  AlphanumericChecker.getInstance(),
			  BoxInfo16x16.getInstance(),
			  table);
	}
}