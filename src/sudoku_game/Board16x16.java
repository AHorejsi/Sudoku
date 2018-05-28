package sudoku_game;

public class Board16x16 extends Board {
	public Board16x16() {
		super(LegalValues16x16.getInstance(), Checker16x16.getInstance());
	}
}