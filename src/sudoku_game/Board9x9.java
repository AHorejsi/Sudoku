package sudoku_game;

import java.util.Random;

public class Board9x9 extends Board {
	public Board9x9() {
		super(LegalValues9x9.getInstance(), Checker9x9.getInstance(), Generator9x9.getInstance().generate());
	}
	
	public Board9x9(Random rng) {
		super(LegalValues9x9.getInstance(), Checker9x9.getInstance(), Generator9x9.getInstance().generate(rng));
	}
	
	public Board9x9(Cell[][] table) {
		super(LegalValues9x9.getInstance(), Checker9x9.getInstance(), table);
	}
}