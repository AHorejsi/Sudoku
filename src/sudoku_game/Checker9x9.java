package sudoku_game;

/**
 * Checks if a Sudoku with nine-by-nine
 * dimensions is solved
 * @author Alex Horejsi
 */
class Checker9x9 implements Checker {
	private static Checker val = new Checker9x9();
	
	private Checker9x9() {}
	
	/**
	 * Returns the single instance of
	 * {@code Checker9x9}
	 * @return The single instance of
	 * {@code Checker9x9}
	 */
	public static Checker getInstance() {
		return Checker9x9.val;
	}
	
	@Override
	public boolean isSolved(Board board) {
		for (int row = 0 ; row < 9 ; row++) {
			if (!this.validRow(row, board))
				return false;
		}
		
		for (int col = 0 ; col < 9 ; col++) {
			if (!this.validCol(col, board))
				return false;
		}
		
		for (int row = 0 ; row < 9 ; row += 3) {
			for (int col = 0 ; col < 9 ; col += 3) {
				if (!this.validBox(row, col, board))
					return false;
			}
		}
		
		return true;
	}
	
	private boolean validRow(int row, Board board) {
		short bits = 0;
		char value;
		
		for (int col = 0 ; col < 9 ; col++) {
			value = board.getValueAt(row, col);
			if (value == '\u0000')
				return false;
			bits |= 1 << (value - '0' - 1);
		}
		
		return bits == 511;
	}
	
	private boolean validCol(int col, Board board) {
		short bits = 0;
		char value;
		
		for (int row = 0 ; row < 9 ; row++) {
			value = board.getValueAt(row, col);
			if (value == '\u0000')
				return false;
			bits |= 1 << (value - '0' - 1);
		}
		
		return bits == 511;
	}
	
	private boolean validBox(int row, int col, Board board) {
		short bits = 0;
		int endRow = row + 3;
		int endCol = col + 3;
		char value;
		
		for (int i = row ; i < endRow ; i++) {
			for (int j = 0 ; j < endCol ; j++) {
				value = board.getValueAt(i, j);
				if (value == '\u0000')
					return false;
				bits |= 1 << (value - '0' - 1);
			}
		}
		
		return bits == 511;
	}
}