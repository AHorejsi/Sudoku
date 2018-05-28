package sudoku_game;

public class Board9x9 extends Board {
	public Board9x9() {
		super(LegalValues9x9.getInstance(), Checker9x9.getInstance());
	}
}