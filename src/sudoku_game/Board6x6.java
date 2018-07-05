package sudoku_game;

import java.util.Random;

/**
 * Instances of this class
 * represent six-by-six
 * Sudoku puzzles
 * @author Alex Horejsi
 */
public class Board6x6 extends Board {
	/**
	 * Constructs a six-by-six Sudoku
	 * board using the default random number
	 * generator
	 */
	Board6x6() {
		super(LegalValues6x6.getInstance(), 
			  NumericChecker.getInstance(), 
			  BoxInfo6x6.getInstance(), 
			  Generator6x6.getInstance().generate());
	}
	
	/**
	 * Constructs a six-by-six Sudoku
	 * board using the given random
	 * number generator
	 * @param rng The random number
	 * generator to be used to generate
	 * the Sudoku board
	 * @throws NullPointerException Thrown
	 * if the given random number
	 * generator is <tt>null</tt>
	 */
	Board6x6(Random rng) {
		super(LegalValues6x6.getInstance(), 
			  NumericChecker.getInstance(), 
			  BoxInfo6x6.getInstance(), 
			  Generator6x6.getInstance().generate(rng));
	}
	
	/**
	 * Constructs a six-by-six Sudoku
	 * board using the given 2D array
	 * of cells
	 * @param table A 2D array of cells
	 * that will serve as this Sudoku board's
	 * cells
	 * @throws NullPointerException Thrown
	 * if the given 2D array of cells is
	 * <tt>null<tt>
	 */
	Board6x6(Cell[][] table) {
		super(LegalValues6x6.getInstance(), 
			  NumericChecker.getInstance(), 
			  BoxInfo6x6.getInstance(), 
			  table);
	}
}