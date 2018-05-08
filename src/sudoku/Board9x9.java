package sudoku;

public class Board9x9 extends SudokuBoard {
	protected Board9x9() {
		super.table = new Input[9][9];
	}
	
	@Override
	public Dimensions getDimensions() {
		return Dimensions.NINE_BY_NINE;
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
		
		for (int i = 0 ; i < 9 ; i += 3) {
			for (int j = 0 ; j < 9 ; j += 3) {
				if (!this.validBox(i, j))
					return false;
			}
		}
		
		return true;
	}
	
	private boolean validRow(int row) {
		byte bits = 0;
		
		for (int col = 0 ; col < super.table.length ; col++)
			bits |= 1 << (super.table[row][col].value - '0' - 1);
		
		return bits == 511;
	}
	
	private boolean validCol(int col) {
		byte bits = 0;
		
		for (int row = 0 ; row < super.table.length ; row++)
			bits |= 1 << (super.table[row][col].value - '0' - 1);	
		
		return bits == 511;
	}
	
	private boolean validBox(int i, int j) {
		byte bits = 0;
		
		int endRow = i + 3;
		int endCol = j + 3;
		
		for (int row = i ; row < endRow ; row++) {
			for (int col = j ; col < endCol ; col++)
				bits |= 1 << (super.table[row][col].value - '0' - 1);
		}
		
		return bits == 511;
	}
}