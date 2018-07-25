package sudoku_game;

/**
 * Simple implementation
 * of {@code LowerBoundChecker}
 * @author Alex Horejsi
 */
public class SimpleLowerBoundChecker implements LowerBoundChecker {
	private static LowerBoundChecker checker = new SimpleLowerBoundChecker();
	
	private SimpleLowerBoundChecker() {}
	
	/**
	 * Returns the single instance
	 * of {@code SimpleLowerBoundChecker}
	 * @return The single instance
	 * of {@code SimpleLowerBoundChecker}
	 */
	public static LowerBoundChecker getInstance() {
		return SimpleLowerBoundChecker.checker;
	}
	
	@Override
	public boolean check(Board board, int row, int col, int lowerBound) {
		Integer dimensions = board.getDimensions();
		Integer boxRow = board.rowSizeInBox();
		Integer boxCol = board.colSizeInBox();
		
		return this.checkRow(board, row, dimensions, lowerBound) &&
			   this.checkCol(board, col, dimensions, lowerBound) &&
			   this.checkBox(board, row - row % boxRow, col - col % boxCol, lowerBound, boxRow, boxCol);
	}
	
	private boolean checkRow(Board board, int row, Integer dimensions, int lowerBound) {
		int count = 0;
		
		for (int col = 0 ; col < dimensions ; col++) {
			if (board.getValueAt(row, col) != '\u0000')
				count++;
		}
		
		return count >= lowerBound;
	}
	
	private boolean checkCol(Board board, int col, Integer dimensions, int lowerBound) {
		int count = 0;
		
		for (int row = 0 ; row < dimensions ; row++) {
			if (board.getValueAt(row, col) != '\u0000')
				count++;
		}
		
		return count >= lowerBound;
	}
	
	private boolean checkBox(Board board, int row, int col, int lowerBound, Integer boxRow, Integer boxCol) {
		int count = 0;
		
		for (int i = 0 ; i < boxRow ; i++) {
			for (int j = 0 ; j < boxCol ; j++) {
				if (board.getValueAt(i + row, j + col) != '\u0000')
					count++;
			}
		}
		
		return count >= lowerBound;
	}
}