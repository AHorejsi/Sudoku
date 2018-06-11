package sudoku_game;

/**
 * Checks if a Sudoku puzzle with
 * sixteen-by-sixteen dimensions is
 * solved
 * @author Alex Horejsi
 *
 */
class Checker16x16 implements Checker {
	private static Checker val = new Checker16x16();
	
	private Checker16x16() {}
	
	/**
	 * Returns the single instance
	 * of {@code Checker16x16}
	 * @return The single instance
	 * of {@code Checker16x16}
	 */
	public static Checker getInstance() {
		return Checker16x16.val;
	}
	
	@Override
	public boolean isSolved(Board board) {		
		RowChecker16x16 rows = new RowChecker16x16(board);
		ColumnChecker16x16 cols = new ColumnChecker16x16(board);
		BoxChecker16x16 boxes = new BoxChecker16x16(board);
		
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
			throw new InternalError();
		}
		
		return rows.isSuccessful() && cols.isSuccessful() && boxes.isSuccessful();
	}
	
	private static class RowChecker16x16 implements Runnable {
		private Board board;
		private boolean success;
		
		public RowChecker16x16(Board board) {
			this.board = board;
			this.success = true;
		}
		
		public boolean isSuccessful() {
			return this.success;
		}
		
		@Override
		public void run() {
			for (int row = 0 ; row < 16 ; row++) {
				if (!this.validRow(row)) {
					this.success = false;
					return;
				}
			}
		}
		
		private boolean validRow(int row) {
			int bits = 0;
			char value;
			
			for (int col = 0 ; col < 16 ; col++) {
				value = this.board.getValueAt(row, col);
				if (value == '\u0000')
					return false;
				bits |= 1 << (value - '0' - 1);
			}
			
			return bits == 8323583;
		}
	}
	
	private static class ColumnChecker16x16 implements Runnable {
		private Board board;
		private boolean success;
		
		public ColumnChecker16x16(Board board) {
			this.board = board;
			this.success = true;
		}
		
		public boolean isSuccessful() {
			return this.success;
		}
		
		@Override
		public void run() {
			for (int col = 0 ; col < 16 ; col++) {
				if (!this.validCol(col)) {
					this.success = false;
					return;
				}
			}
		}
		
		private boolean validCol(int col) {
			int bits = 0;
			char value;
			
			for (int row = 0 ; row < 16 ; row++) {
				value = this.board.getValueAt(row, col);
				if (value == '\u0000')
					return false;
				bits |= 1 << (value - '0' - 1);
			}
			
			return bits == 8323583;
		}
	}
	
	private static class BoxChecker16x16 implements Runnable {
		private Board board;
		private boolean success;
		
		public BoxChecker16x16(Board board) {
			this.board = board;
			this.success = true;
		}
		
		public boolean isSuccessful() {
			return this.success;
		}
		
		@Override
		public void run() {
			for (int row = 0 ; row < 16 ; row += 4) {
				for (int col = 0 ; col < 16 ; col += 4) {
					if (!this.validBox(row, col)) {
						this.success = false;
						return;
					}
				}
			}
		}
		
		private boolean validBox(int row, int col) {
			int bits = 0;
			int endRow = row + 4;
			int endCol = col + 4;
			char value;
			
			for (int i = row ; i < endRow ; i++) {
				for (int j = col ; j < endCol ; j++) {
					value = this.board.getValueAt(i, j);
					if (value == '\u0000')
						return false;
					bits |= 1 << (value - '0' - 1);
				}
			}
			
			return bits == 8323583;
		}
	}
}