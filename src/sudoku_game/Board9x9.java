package sudoku_game;

import java.util.Random;

/**
 * Instances of this class represent
 * nine-by-nine Sudoku boards
 * @author Alex Horejsi
 */
public class Board9x9 extends Board {
	private static Integer boxRowCols = 3;
	
	/**
	 * Constructs a nine-by-nine Sudoku
	 * board using the default random number
	 * generator
	 */
	Board9x9() {
		super(LegalValues9x9.getInstance(), 
			  NumericChecker.getInstance(), 
			  Board9x9.boxRowCols,
			  Board9x9.boxRowCols,
			  Generator9x9.getInstance().generate());
	}
	
	/**
	 * Constructs a nine-by-nine Sudoku
	 * board using the given random
	 * number generator
	 * @param rng The random number
	 * generator to be used to generate
	 * the Sudoku board
	 * @throws NullPointerException Thrown
	 * if the given random number
	 * generator is <tt>null</tt>
	 */
	Board9x9(Random rng) throws NullPointerException {
		super(LegalValues9x9.getInstance(), 
			  NumericChecker.getInstance(), 
			  Board9x9.boxRowCols,
			  Board9x9.boxRowCols,
			  Generator9x9.getInstance().generate(rng));
	}
	
	/**
	 * Constructs a nine-by-nine Sudoku
	 * board using the given 2D array
	 * of cells
	 * @param table A 2D array of cells
	 * that will serve as this Sudoku board's
	 * cells
	 * @throws NullPointerException Thrown
	 * if the given 2D array of cells is
	 * <tt>null<tt>
	 */
	Board9x9(Cell[][] table) throws NullPointerException {
		super(LegalValues9x9.getInstance(), 
			  NumericChecker.getInstance(), 
			  Board9x9.boxRowCols,
			  Board9x9.boxRowCols,
			  table);
	}
}