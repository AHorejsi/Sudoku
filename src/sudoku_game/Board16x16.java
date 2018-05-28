package sudoku_game;

import java.util.Random;

public class Board16x16 extends Board {
	public Board16x16(Cell[][] table) {
		super(LegalValues16x16.getInstance(), Checker16x16.getInstance(), table);
	}
	
	public Board16x16(Cell[][] table, Random rng) {
		super(LegalValues16x16.getInstance(), Checker16x16.getInstance(), table, rng);
	}
}