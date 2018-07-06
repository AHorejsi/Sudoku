package sudoku_game;

import java.util.Random;

/**
 * Instances of this class
 * represent four-by-four
 * Sudoku boards
 * @author Alex Horejsi
 */
public class Board4x4 extends Board {
	/**
	 * Constructs a four-by-four Sudoku
	 * board using the default random number
	 * generator
	 */
	Board4x4() {
		super(LegalValues4x4.getInstance(),
			  NumericChecker.getInstance(),
			  BoxInfo4x4.getInstance(),
			  Generator4x4.getInstance().generate());
	}
	
	/**
	 * Constructs a four-by-four Sudoku
	 * board using the given random
	 * number generator
	 * @param rng The random number
	 * generator to be used to generate
	 * the Sudoku board
	 * @throws NullPointerException Thrown
	 * if the given random number
	 * generator is <tt>null</tt>
	 */
	Board4x4(Random rng) throws NullPointerException {
		super(LegalValues4x4.getInstance(),
			  NumericChecker.getInstance(),
			  BoxInfo4x4.getInstance(),
			  Generator4x4.getInstance().generate(rng));
	}
	
	/**
	 * Constructs a four-by-four Sudoku
	 * board using the given 2D array
	 * of cells
	 * @param table A 2D array of cells
	 * that will serve as this Sudoku board's
	 * cells
	 * @throws NullPointerException Thrown
	 * if the given 2D array of cells is
	 * <tt>null<tt>
	 */
	Board4x4(Cell[][] table) throws NullPointerException {
		super(LegalValues4x4.getInstance(),
			  NumericChecker.getInstance(),
			  BoxInfo4x4.getInstance(),
			  table);
	}
}