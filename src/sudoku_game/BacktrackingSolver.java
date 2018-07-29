package sudoku_game;

/**
 * Checks if a Sudoku board has
 * a unique solution with a
 * backtracking algorithm
 * @author Alex Horejsi
 */
public class BacktrackingSolver implements Solver {
	private static Solver solver = new BacktrackingSolver();
	
	private BacktrackingSolver() {}
	
	/**
	 * Returns the single instance
	 * of {@code BacktrackingSolver}
	 * @return The single instance
	 * of {@code BacktrackingSolver}
	 */
	public static Solver getInstance() {
		return BacktrackingSolver.solver;
	}
	
	@Override
	public boolean hasUniqueSolution(Board board) {
		return this.solve(board, board.getTable(), board.getLegalValues().getValues(), 0, 0, 0, board.getDimensions()) == 1;
	}
	
	private int solve(Board board, Cell[][] table, char[] legalValues, int count, int row, int col, int length) {
		if (count > 1)
			return count;
		
		if (row == length)
			count++;
		else if (table[row][col].getValue() != '\u0000') {
			if (col == length - 1)
				count = this.solve(board, table, legalValues, count, row + 1, 0, length);
			else
				count = this.solve(board, table, legalValues, count, row, col + 1, length);
		}
		else {
			for (int i = 0 ; i < length ; i++) {
				if (count > 1)
					return count;
				if (this.safe(board, table, row, col, legalValues[i])) {
					table[row][col].setValueForSetUp(legalValues[i]);
					
					if (col == length - 1)
						count = this.solve(board, table, legalValues, count, row + 1, 0, length);
					else
						count = this.solve(board, table, legalValues, count, row, col + 1, length);
					
					table[row][col].setEmptyForSetUp();
				}
			}
		}
		
		return count;
	}
	
	private boolean safe(Board board, Cell[][] table, int i, int j, char digit) {
		int endRow = board.rowSizeInBox();
		int endCol = board.colSizeInBox();
		return this.safeRow(table, i, digit, table.length) && 
			   this.safeCol(table, j, digit, table.length) && 
			   this.safeBox(table, i - i % endRow, j - j % endCol, digit, endRow, endCol);
	}
	
	private boolean safeRow(Cell[][] table, int i, char digit, int end) {
		for (int j = 0 ; j < end ; j++) {
			if (table[i][j] != null) {
				if (table[i][j].getValue() == digit)
					return false;
			}
		}
		
		return true;
	}
	
	private boolean safeCol(Cell[][] table, int j, char digit, int end) {
		for (int i = 0 ; i < end ; i++) {
			if (table[i][j] != null) {
				if (table[i][j].getValue() == digit)
					return false;
			}
		}
		
		return true;
	}
	
	private boolean safeBox(Cell[][] table, int i, int j, char digit, int endRow, int endCol) {
		for (int row = 0 ; row < endRow ; row++) {
			for (int col = 0 ; col < endCol ; col++) {
				if (table[row + i][col + j] != null) {
					if (table[row + i][col + j].getValue() == digit)
						return false;
				}
			}
		}
		
		return true;
	}
}