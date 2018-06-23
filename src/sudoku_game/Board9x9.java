package sudoku_game;

import java.util.Random;

/**
 * Instances of this class represent
 * nine-by-nine Sudoku boards
 * @author Alex Horejsi
 */
class Board9x9 extends Board {
	/**
	 * Constructs a nine-by-nine Sudoku
	 * board using the default random number
	 * generator
	 */
	public Board9x9() {
		super(LegalValues9x9.getInstance(), Checker9x9.getInstance(), BoxInfo9x9.getInstance(), Generator9x9.getInstance().generate());
	}
	
	/**
	 * Constructs a nine-by-nine Sudoku
	 * board using the given random
	 * number generator
	 * @param rng The random number
	 * generator to be used to generate
	 * the Sudoku board
	 */
	public Board9x9(Random rng) {
		super(LegalValues9x9.getInstance(), Checker9x9.getInstance(), BoxInfo9x9.getInstance(), Generator9x9.getInstance().generate(rng));
	}
	
	/**
	 * Constructs a nine-by-nine Sudoku
	 * board using the given 2D array
	 * of cells
	 * @param table A 2D array of cells
	 * that will serve as this Sudoku board's
	 * cells
	 */
	public Board9x9(Cell[][] table) {
		super(LegalValues9x9.getInstance(), Checker9x9.getInstance(), BoxInfo9x9.getInstance(), table);
	}
}