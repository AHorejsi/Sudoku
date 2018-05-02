package sudoku;

public class Puzzle16x16 extends SudokuPuzzle {
	public Puzzle16x16() {
		super.board = new Input[16][16];
	}
	
	@Override
	public boolean isSolved() {
		for (int row = 0 ; row < 16 ; row++) {
			if (!this.rowIsValid(row))
				return false;
		}
		
		for (int col = 0 ; col < 16 ; col++) {
			if (!this.columnIsValid(col))
				return false;
		}
		
		for (int row = 0 ; row < 16 ; row += 4) {
			for (int col = 0 ; col < 16 ; col += 4) {
				if (!this.boxIsValid(row, col))
					return false;
			}
		}
		
		return true;
	}
	
	private boolean rowIsValid(int row) {
		short bits = 0;
		
		for (int col = 0 ; col < 16 ; col++)
			bits |= 1 << (super.board[row][col].value - '0' - 1);
		
		return bits == 65535;
	}
	
	private boolean columnIsValid(int col) {
		short bits = 0;
		
		for (int row = 0 ; row < 16 ; row++)
			bits |= 1 << (super.board[row][col].value - '0' - 1);
		
		return bits == 65535;
	}
	
	private boolean boxIsValid(int row, int col) {
		int rowEnd = row + 4;
		int colEnd = col + 4;
		short bits = 0;
		
		for (int row2 = row ; row2 < rowEnd ; row2++) {
			for (int col2 = col ; col2 < colEnd ; col2++)
				bits |= 1 << (super.board[row2][col2].value - '0' - 1);
		}
		
		return bits == 65535;
	}
}