package sudoku;

public class Puzzle4x4 extends SudokuPuzzle {
	public Puzzle4x4() {
		super.board = new Input[4][4];
	}
	
	@Override
	public Dimensions getDimensions() {
		return Dimensions.FOUR_BY_FOUR;
	}
	
	@Override
	public boolean isSolved() {
		for (int row = 0 ; row < 4 ; row++) {
			if (!this.rowIsValid(row))
				return false;
		}
		
		for (int col = 0 ; col < 4 ; col++) {
			if (!this.columnIsValid(col))
				return false;
		}
		
		for (int row = 0 ; row < 4 ; row += 2) {
			for (int col = 0 ; col < 4 ; col += 2) {
				if (!this.boxIsValid(row, col))
					return false;
			}
		}
		
		return true;
	}
	
	private boolean rowIsValid(int row) {
		byte bits = 0;
		
		for (int col = 0 ; col < 4 ; col++)
			bits |= 1 << (super.board[row][col].value - '0' - 1);
		
		return bits == 15;
	}
	
	private boolean columnIsValid(int col) {
		byte bits = 0;
		
		for (int row = 0 ; row < 4 ; row++)
			bits |= 1 << (super.board[row][col].value - '0' - 1);
		
		return bits == 15;
	}
	
	private boolean boxIsValid(int row, int col) {
		int rowEnd = row + 2;
		int colEnd = col + 2;
		byte bits = 0;
		
		for (int row2 = row ; row2 < rowEnd ; row2++) {
			for (int col2 = col ; col2 < colEnd ; col2++)
				bits |= 1 << (super.board[row2][col2].value - '0' - 1);
		}
		
		return bits == 15;
	}
}