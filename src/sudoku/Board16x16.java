package sudoku;

public class Board16x16 extends SudokuBoard {
	protected Board16x16() {
		super.table = new Input[16][16];
	}
	
	@Override
	public Dimensions getDimensions() {
		return Dimensions.SIXTEEN_BY_SIXTEEN;
	}
	
	@Override
	public boolean isSolved() {
		for (int row = 0 ; row < 16 ; row++) {
			if (!this.validRow(row))
				return false;
		}
		
		for (int col = 0 ; col < 16 ; col++) {
			if (!this.validCol(col))
				return false;
		}
		
		for (int i = 0 ; i < 16 ; i += 4) {
			for (int j = 0 ; j < 16 ; j += 4) {
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
		
		return bits == 65535;
	}
	
	private boolean validCol(int col) {
		byte bits = 0;
		
		for (int row = 0 ; row < super.table.length ; row++)
			bits |= 1 << (super.table[row][col].value - '0' - 1);	
		
		return bits == 65535;
	}
	
	private boolean validBox(int i, int j) {
		byte bits = 0;
		
		int endRow = i + 4;
		int endCol = j + 4;
		
		for (int row = i ; row < endRow ; row++) {
			for (int col = j ; col < endCol ; col++)
				bits |= 1 << (super.table[row][col].value - '0' - 1);
		}
		
		return bits == 65535;
	}
}