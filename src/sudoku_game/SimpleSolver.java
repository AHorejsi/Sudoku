package sudoku_game;

public class SimpleSolver implements Solver {
	private static Solver solver = new SimpleSolver();
	
	private SimpleSolver() {}
	
	public static Solver getInstance() {
		return SimpleSolver.solver;
	}
	
	public boolean hasUniqueSolution(Board board) {
		return this.solve(board.table, board.legalValues.getLegalValues(), 0, 0, 0, board.getDimensions()) == 1;
	}
	
	private int solve(Cell[][] table, char[] legalValues, int count, int row, int col, int length) {
		if (row == length)
			count++;
		else if (table[row][col].getValue() != '\u0000') {
			if (col == length - 1)
				count = this.solve(table, legalValues, count, row + 1, 0, length);
			else
				count = this.solve(table, legalValues, count, row, col + 1, length);
		}
		else {
			for (int i = 0 ; i < length ; i++) {
				if (this.safe(table, row, col, legalValues[i])) {
					table[row][col].setValueForSetUp(legalValues[i]);
					
					if (col == length - 1)
						count = this.solve(table, legalValues, count, row + 1, 0, length);
					else
						count = this.solve(table, legalValues, count, row, col + 1, length);
					
					table[row][col].setEmptyForSetUp();
				}
			}
		}
		
		return count;
	}
	
	private boolean safe(Cell[][] table, int i, int j, char digit) {
		int end = (int)Math.sqrt(table.length);
		return this.safeRow(table, i, digit, table.length) && this.safeCol(table, j, digit, table.length) && 
				this.safeBox(table, i - i % end, j - j % end, digit, end);
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
	
	private boolean safeBox(Cell[][] table, int i, int j, char digit, int end) {
		for (int row = 0 ; row < end ; row++) {
			for (int col = 0 ; col < end ; col++) {
				if (table[row + i][col + j] != null) {
					if (table[row + i][col + j].getValue() == digit)
						return false;
				}
			}
		}
		
		return true;
	}
}