package sudoku_game;

public class Board9x9 extends Board {
	Board9x9(char[][] table) {
		super.table = new Cell[9][9];
		
		for (int row = 0 ; row < 9 ; row++) {
			for (int col = 0 ; col < 9 ; col++) {
				if (table[row][col] == '\u0000')
					super.table[row][col] = new Cell(true);
				else
					super.table[row][col] = new Cell(table[row][col], false);
			}
		}
	}
	
	@Override
	public boolean isLegalValue(char value) {
		return value >= '1' && value <= '9';
	}
	
	@Override
	public boolean isSolved() {
		for (int row = 0 ; row < 9 ; row++) {
			if (!this.validRow(row))
				return false;
		}
		
		for (int col = 0 ; col < 9 ; col++) {
			if (!this.validCol(col))
				return false;
		}
		
		for (int row = 0 ; row < 9 ; row += 3) {
			for (int col = 0 ; col < 9 ; col += 3) {
				if (!this.validBox(row, col))
					return false;
			}
		}
		
		return true;
	}
	
	private boolean validRow(int row) {
		short bits = 0;
		
		for (int col = 0 ; col < 9 ; col++)
			bits |= (1 << super.table[row][col].value - '0' - 1);
		
		return bits == 511;
	}
	
	private boolean validCol(int col) {
		short bits = 0;
		
		for (int row = 0 ; row < 9 ; row++)
			bits |= (1 << super.table[row][col].value - '0' - 1);
		
		return bits == 511;
	}
	
	private boolean validBox(int row, int col) {
		short bits = 0;
		int endRow = row + 3;
		int endCol = col + 3;
		
		for (int i = row ; i < endRow ; i++) {
			for (int j = col ; j < endCol ; j++)
				bits |= (1 << super.table[row][col].value - '0' - 1);
		}
		
		return bits == 511;
	}
}