package sudoku_game;

/**
 * Checks if a Sudoku with nine-by-nine
 * dimensions is solved
 * @author Alex Horejsi
 */
class Checker9x9 implements Checker {
	private static Checker val = new Checker9x9();
	
	private Checker9x9() {}
	
	/**
	 * Returns the single instance of
	 * {@code Checker9x9}
	 * @return The single instance of
	 * {@code Checker9x9}
	 */
	public static Checker getInstance() {
		return Checker9x9.val;
	}
	
	@Override
	public boolean isSolved(Board board) {
		RowChecker9x9 rows = new RowChecker9x9(board);
		ColumnChecker9x9 cols = new ColumnChecker9x9(board);
		BoxChecker9x9 boxes = new BoxChecker9x9(board);
		
		Thread t1 = new Thread(rows);
		Thread t2 = new Thread(cols);
		Thread t3 = new Thread(boxes);
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException ex) {
			throw new InternalError(ex);
		}
		
		return rows.isSuccessful() && cols.isSuccessful() && boxes.isSuccessful();
	}
	
	private static class RowChecker9x9 implements Runnable {
		private Board board;
		private boolean success;
		
		public RowChecker9x9(Board board) {
			this.board = board;
			this.success = true;
		}
		
		public boolean isSuccessful() {
			return this.success;
		}
		
		@Override
		public void run() {
			for (int row = 0 ; row < 9 ; row++) {
				if (!this.validRow(row)) {
					this.success = false;
					return;
				}
			}
		}
		
		private boolean validRow(int row) {
			short bits = 0;
			char value;
			
			for (int col = 0 ; col < 9 ; col++) {
				value = this.board.getValueAt(row, col);
				if (value == '\u0000')
					return false;
				bits |= 1 << (value - '0' - 1);
			}
			
			return bits == 511;
		}
	}
	
	private static class ColumnChecker9x9 implements Runnable {
		private Board board;
		private boolean success;
		
		public ColumnChecker9x9(Board board) {
			this.board = board;
			this.success = true;
		}
		
		public boolean isSuccessful() {
			return this.success;
		}
		
		@Override
		public void run() {
			for (int col = 0 ; col < 9 ; col++) {
				if (!this.validCol(col)) {
					this.success = false;
					return;
				}
			}
		}
		
		private boolean validCol(int col) {
			short bits = 0;
			char value;
			
			for (int row = 0 ; row < 9 ; row++) {
				value = this.board.getValueAt(row, col);
				if (value == '\u0000')
					return false;
				bits |= 1 << (value - '0' - 1);
			}
			
			return bits == 511;
		}
	}
	
	private static class BoxChecker9x9 implements Runnable {
		private Board board;
		private boolean success;
		
		public BoxChecker9x9(Board board) {
			this.board = board;
			this.success = true;
		}
		
		public boolean isSuccessful() {
			return this.success;
		}
		
		@Override
		public void run() {
			for (int row = 0 ; row < 9 ; row += 3) {
				for (int col = 0 ; col < 9 ; col += 3) {
					if (!this.validBox(row, col)) {
						this.success = false;
						return;
					}
				}
			}
		}
		
		private boolean validBox(int row, int col) {
			short bits = 0;
			int endRow = row + 3;
			int endCol = col + 3;
			char value;
			
			for (int i = row ; i < endRow ; i++) {
				for (int j = 0 ; j < endCol ; j++) {
					value = this.board.getValueAt(i, j);
					if (value == '\u0000')
						return false;
					bits |= 1 << (value - '0' - 1);
				}
			}
			
			return bits == 511;
		}
	}
}