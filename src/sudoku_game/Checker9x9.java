package sudoku_game;

import adjustor.Cell;

public class Checker9x9 implements Checker {
	private static Checker9x9 val = new Checker9x9();
	
	private Checker9x9() {}
	
	public static Checker9x9 getInstance() {
		return Checker9x9.val;
	}
	
	@Override
	public boolean isSolved(Board board) {
		for (int row = 0 ; row < 9 ; row++) {
			if (!this.validRow(board.table[row]))
				return false;
		}
		
		for (int col = 0 ; col < 9 ; col++) {
			if (!this.validCol(board.table, col))
				return false;
		}
		
		for (int row = 0 ; row < 9 ; row += 3) {
			for (int col = 0 ; col < 9 ; col += 3) {
				if (!this.validBox(board.table, row, col))
					return false;
			}
		}
		
		return false;
	}
	
	private boolean validRow(Cell[] row) {
		short bits = 0;
		
		for (int col = 0 ; col < 9 ; col++)
			bits |= 1 << (row[col].getValue() - '0' - 1);
		
		return bits == 511;
	}
	
	private boolean validCol(Cell[][] table, int col) {
		short bits = 0;
		
		for (int row = 0 ; row < 9 ; row++)
			bits |= 1 << (table[row][col].getValue() - '0' - 1);
		
		return bits == 511;
	}
	
	private boolean validBox(Cell[][] table, int row, int col) {
		short bits = 0;
		int endRow = row + 3;
		int endCol = col + 3;
		
		for (int i = row ; i < endRow ; i++) {
			for (int j = 0 ; j < endCol ; j++)
				bits |= 1 << (table[i][j].getValue() - '0' - 1);
		}
		
		return bits == 511;
	}
}