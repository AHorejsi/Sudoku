package sudoku;

public class Puzzle9x9 extends SudokuPuzzle {
	public Puzzle9x9() {
		super.board = new Input[9][9];
	}
	
	@Override
	public Dimensions getDimensions() {
		return Dimensions.NINE_BY_NINE;
	}
	
	@Override
	public boolean isSolved() {
		for (int row = 0 ; row < 9 ; row++) {
			if (!this.rowIsValid(row))
				return false;
		}
		
		for (int col = 0 ; col < 9 ; col++) {
			if (!this.columnIsValid(col))
				return false;
		}
		
		for (int row = 0 ; row < 9 ; row += 3) {
			for (int col = 0 ; col < 9 ; col += 3) {
				if (!this.boxIsValid(row, col))
					return false;
			}
		}
		
		return true;
	}
	
	private boolean rowIsValid(int row) {
		short bits = 0;
		
		for (int col = 0 ; col < 9 ; col++)
			bits |= 1 << (super.board[row][col].value - '0' - 1);
		
		return bits == 511;
	}
	
	private boolean columnIsValid(int col) {
		short bits = 0;
		
		for (int row = 0 ; row < 9 ; row++)
			bits |= 1 << (super.board[row][col].value - '0' - 1);
		
		return bits == 511;
	}
	
	private boolean boxIsValid(int row, int col) {
		int rowEnd = row + 3;
		int colEnd = col + 3;
		short bits = 0;
		
		for (int row2 = row ; row2 < rowEnd ; row2++) {
			for (int col2 = col ; col2 < colEnd ; col2++)
				bits |= 1 << (super.board[row2][col2].value - '0' - 1);
		}
		
		return bits == 511;
	}
	
//	public static void main(String[] args) {
//		Puzzle9x9 pzzl = new Puzzle9x9();
//		char[][] mat = {{'9', '6', '3', '1', '7', '4', '2', '5', '8'},
//						{'1', '7', '8', '3', '2', '5', '6', '4', '9'},
//						{'2', '5', '4', '6', '8', '9', '7', '3', '1'},
//						{'8', '2', '1', '4', '3', '7', '5', '9', '6'},
//						{'4', '9', '6', '8', '5', '2', '3', '1', '7'},
//						{'7', '3', '5', '9', '6', '1', '8', '2', '4'},
//						{'5', '8', '9', '7', '1', '3', '4', '6', '2'},
//						{'3', '1', '7', '2', '4', '6', '9', '8', '5'},
//						{'6', '4', '2', '5', '9', '8', '1', '7', '3'}};
//		
//		for (int i = 0 ; i < 9 ; i++) {
//			for (int j = 0 ; j < 9 ; j++) {
//				pzzl.board[i][j] = new Input9x9(true);
//				pzzl.board[i][j].value = mat[i][j];
//			}
//		}
//		
//		System.out.println(pzzl.isSolved());
//	}
}