package sudoku_game;

import adjustor.Cell;

public class Checker16x16 implements Checker {
	private static Checker16x16 val = new Checker16x16();
	
	private Checker16x16() {}
	
	public static Checker16x16 getInstance() {
		return Checker16x16.val;
	}
	
	@Override
	public boolean isSolved(Board board) {
		for (int row = 0 ; row < 16 ; row++) {
			if (!this.validRow(board.table[row]))
				return false;
		}
		
		for (int col = 0 ; col < 16 ; col++) {
			if (!this.validCol(board.table, col))
				return false;
		}
		
		for (int row = 0 ; row < 16 ; row += 4) {
			for (int col = 0 ; col < 16 ; col += 4) {
				if (!this.validBox(board.table, row, col))
					return false;
			}
		}
		
		return true;
	}
	
	private boolean validRow(Cell[] row) {
		int bits = 0;
		
		for (int col = 0 ; col < 16 ; col++)
			bits |= 1 << (row[col].getValue() - '0' - 1);
		
		return bits == 8323583;
	}
	
	private boolean validCol(Cell[][] table, int col) {
		int bits = 0;
		
		for (int row = 0 ; row < 16 ; row++)
			bits |= 1 << (table[row][col].getValue() - '0' - 1);
		
		return bits == 8323583;
	}
	
	private boolean validBox(Cell[][] table, int row, int col) {
		int bits = 0;
		int endRow = row + 4;
		int endCol = col + 4;
		
		for (int i = row ; i < endRow ; i++) {
			for (int j = col ; j < endCol ; j++)
				bits |= 1 << (table[row][col].getValue() - '0' - 1);
		}
		
		return bits == 8323583;
	}
}