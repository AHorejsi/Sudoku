package sudoku_game;

/**
 * Simple implementation
 * of {@code SafeCellChecker}
 * @author Alex Horejsi
 */
public class SimpleSafeCellChecker implements SafeCellChecker {
	private static SafeCellChecker checker = new SimpleSafeCellChecker();
	
	private SimpleSafeCellChecker() {}
	
	/**
	 * Returns the single instance
	 * of {@code SimpleSafeCellChecker}
	 * @return The single instance
	 * of {@code SimpleSafeCellChecker}
	 */
	public static SafeCellChecker getInstance() {
		return SimpleSafeCellChecker.checker;
	}
	
	@Override
	public boolean safe(Cell[][] table, char value, int row, int col, int boxRow, int boxCol) {
		return this.safeRow(table, value, row) &&
			   this.safeCol(table, value, col) &&
			   this.safeBox(table, value, row - row % boxRow , col - col % boxCol, boxRow, boxCol);
	}
	
	private boolean safeRow(Cell[][] table, char value, int row) {
		for (int col = 0 ; col < table.length ; col++) {
			if (table[row][col] != null) {
				if (table[row][col].getValue() == value)
					return false;
			}
		}
		
		return true;
	}
	
	private boolean safeCol(Cell[][] table, char value, int col) {
		for (int row = 0 ; row < table.length ; row++) {
			if (table[row][col] != null) {
				if (table[row][col].getValue() == value)
					return false;
			}
		}
		
		return true;
	}
	
	private boolean safeBox(Cell[][] table, char value, int row, int col, int boxRow, int boxCol) {
		for (int i = 0 ; i < boxRow ; i++) {
			for (int j = 0 ; j < boxCol ; j++) {
				if (table[row + i][col + j] != null) {
					if (table[row + i][col + j].getValue() == value)
						return false;
				}
			}
		}
		
		return true;
	}
}