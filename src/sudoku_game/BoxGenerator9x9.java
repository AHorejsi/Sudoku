package sudoku_game;

import java.util.Random;

class BoxGenerator9x9 extends BoxGenerator {
	BoxGenerator9x9(Cell[][] table, Random rng, int startRow, int startCol, int endRow, int endCol) {
		super(table, rng, startRow, startCol, endRow, endCol);
	}
	
	@Override
	char toChar(int digit) {
		return (char)(digit + '0');
	}
}