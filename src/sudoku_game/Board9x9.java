package sudoku_game;

import java.util.Random;
import generators.Generator9x9;

class Board9x9 extends Board {
	Board9x9() {
		this(DefaultRNG.getDefaultGenerator());
	}
	
	Board9x9(Random rng) {
		super(9);
		
		char[][] table = Generator9x9.getInstance().generate(rng);
		for (int i = 0 ; i < 9 ; i++) {
			for (int j = 0 ; j < 9 ; j++)
				super.table[i][j] = new Cell9x9(table[i][j]);
		}
	}
}