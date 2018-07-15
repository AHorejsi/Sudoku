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
		return this.checkRow(board, row, lowerBound) &&
			   this.checkCol(board, col, lowerBound) &&
			   this.checkBox(board, row - row % board.rowSizeInBox(), col - col % board.colSizeInBox(), lowerBound);
	}
	
	private boolean checkRow(Board board, int row, int lowerBound) {
		int count = 0;
		int dimensions = board.getDimensions();
		
		for (int col = 0 ; col < dimensions ; col++) {
			if (board.getValueAt(row, col) != '\u0000')
				count++;
		}
		
		return count >= lowerBound;
	}
	
	private boolean checkCol(Board board, int col, int lowerBound) {
		int count = 0;
		int dimensions = board.getDimensions();
		
		for (int row = 0 ; row < dimensions ; row++) {
			if (board.getValueAt(row, col) != '\u0000')
				count++;
		}
		
		return count >= lowerBound;
	}
	
	private boolean checkBox(Board board, int row, int col, int lowerBound) {
		int count = 0;
		int endRow = row + board.rowSizeInBox();
		int endCol = col + board.colSizeInBox();
		
		for (int i = row ; i < endRow ; i++) {
			for (int j = col ; j < endCol ; j++) {
				if (board.getValueAt(i, j) != '\u0000')
					count++;
			}
		}
		
		return count >= lowerBound;
	}
}