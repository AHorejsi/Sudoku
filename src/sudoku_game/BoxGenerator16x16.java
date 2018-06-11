package sudoku_game;

import java.util.Random;

class BoxGenerator16x16 extends BoxGenerator {
	public BoxGenerator16x16(Cell[][] table, Random rng, int startRow, int startCol, int endRow, int endCol) {
		super(table, rng, startRow, startCol, endRow, endCol);
	}
	
	@Override
	char toChar(int digit) {
		return (char)(digit < 10 ? digit + '0' : digit - 10 + 'A');
	}
}