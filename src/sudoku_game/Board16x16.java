package sudoku_game;

import java.util.Random;

public class Board16x16 extends Board {
	public Board16x16() {
		super(LegalValues16x16.getInstance(), Checker16x16.getInstance(), BoxInfo16x16.getInstance(), 
				Generator16x16.getInstance().generate());
	}
	
	public Board16x16(Random rng) {
		super(LegalValues16x16.getInstance(), Checker16x16.getInstance(), BoxInfo16x16.getInstance(),
				Generator16x16.getInstance().generate(rng));
	}
	
	public Board16x16(Cell[][] table) {
		super(LegalValues16x16.getInstance(), Checker16x16.getInstance(), BoxInfo16x16.getInstance(), table);
	}
}