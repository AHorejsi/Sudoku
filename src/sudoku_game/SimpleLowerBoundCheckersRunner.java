package sudoku_game;

class SimpleLowerBoundCheckersRunner implements LowerBoundCheckersRunner {
	private static LowerBoundCheckersRunner runner = new SimpleLowerBoundCheckersRunner();
	
	private SimpleLowerBoundCheckersRunner() {}
	
	public static LowerBoundCheckersRunner getInstance() {
		return SimpleLowerBoundCheckersRunner.runner;
	}
	
	@Override
	public boolean check(Board board, int row, int col, int lowerBound) {
		int rowEnd = board.rowSizeInBox();
		int colEnd = board.colSizeInBox();
		LowerBoundChecker rows = new SimpleRowLowerBoundChecker(board, row, lowerBound);
		LowerBoundChecker cols = new SimpleColumnLowerBoundChecker(board, col, lowerBound);
		LowerBoundChecker boxes = new SimpleBoxLowerBoundChecker(board, row - row % rowEnd, col - col % colEnd, 
																 rowEnd, colEnd, 
																 lowerBound);
		
		Thread t1 = new Thread(rows);
		Thread t2 = new Thread(cols);
		Thread t3 = new Thread(boxes);
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException ex) {
			throw new InternalError();
		}
		
		return rows.isSuccessful() && cols.isSuccessful() && boxes.isSuccessful();
	}
}