package sudoku_game;

import java.util.Random;

public class Board9x9 extends Board {
	public Board9x9(Cell[][] table) {
		super(LegalValues9x9.getInstance(), Checker9x9.getInstance(), table);
	}
	
	public Board9x9(Cell[][] table, Random rng) {
		super(LegalValues9x9.getInstance(), Checker9x9.getInstance(), table, rng);
	}
}