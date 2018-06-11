package sudoku_game;

class SimpleColumnLowerBoundChecker implements LowerBoundChecker {
	private Board board;
	private int column;
	private int lowerBound;
	private boolean success;
	
	public SimpleColumnLowerBoundChecker(Board board, int column, int lowerBound) {
		this.board = board;
		this.column = column;
		this.lowerBound = lowerBound;
		this.success = true;
	}
	
	@Override
	public boolean isSuccessful() {
		return this.success;
	}
	
	@Override
	public void run() {
		int count = 0;
		int dimensions = this.board.getDimensions();
		
		for (int row = 0 ; row < dimensions ; row++) {
			if (this.board.getValueAt(row, this.column) != '\u0000')
				count++;
			if (count >= this.lowerBound) {
				this.success = false;
				return;
			}
		}
	}
}