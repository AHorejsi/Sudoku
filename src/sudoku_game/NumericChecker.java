package sudoku_game;

/**
 * This class checks if a
 * Sudoku puzzle is complete
 * and valid using bitwise
 * operations. This implementation
 * only works on Sudoku puzzles
 * whose legal values range from 1
 * to another single digit number
 * @author Alex Horejsi
 */
public class NumericChecker implements Checker {
	private static Checker checker = new NumericChecker();
	
	private NumericChecker() {}
	
	/**
	 * Returns the single instance
	 * of {@code NumericChecker}
	 * @return The single instance
	 * of {@code NumericChecker}
	 */
	public static Checker getInstance() {
		return NumericChecker.checker;
	}
	
	@Override
	public boolean isSolved(Board board) {
		Integer dimensions = board.getDimensions();
		Integer success = (1 << dimensions) - 1;
		
		for (int row = 0 ; row < dimensions ; row++) {
			if (!this.validRow(row, board, success, dimensions))
				return false;
		}
		
		for (int col = 0 ; col < dimensions ; col++) {
			if (!this.validCol(col, board, success, dimensions))
				return false;
		}
		
		int rowIncrement = board.rowSizeInBox();
		int columnIncrement = board.colSizeInBox(); 
		
		for (int row = 0 ; row < dimensions ; row += rowIncrement) {
			for (int col = 0 ; col < dimensions ; col += columnIncrement) {
				if (!this.validBox(row, col, board, success, dimensions))
					return false;
			}
		}
		
		return true;
	}
	
	private boolean validRow(int row, Board board, Integer success, Integer dimensions) {
		int bits = 0;
		char value;
		
		for (int col = 0 ; col < dimensions ; col++) {
			value = board.getValueAt(row, col);
			if (!board.isLegalValue(value))
				return false;
			bits |= 1 << (value - '0' - 1);
		}
		
		return bits == success;
	}
	
	private boolean validCol(int col, Board board, Integer success, Integer dimensions) {
		int bits = 0;
		char value;
		
		for (int row = 0 ; row < dimensions ; row++) {
			value = board.getValueAt(row, col);
			if (!board.isLegalValue(value))
				return false;
			bits |= 1 << (value - '0' - 1);
		}
		
		return bits == success;
	}
	
	private boolean validBox(int row, int col, Board board, Integer success, Integer dimensions) {
		int bits = 0;
		int endRow = row + board.rowSizeInBox();
		int endCol = col + board.colSizeInBox();
		char value;
		
		for (int i = row ; i < endRow ; i++) {
			for (int j = col ; j < endCol ; j++) {
				value = board.getValueAt(i, j);
				if (!board.isLegalValue(value))
					return false;
				bits |= 1 << (value - '0' - 1);
			}
		}
		
		return bits == success;
	}
}