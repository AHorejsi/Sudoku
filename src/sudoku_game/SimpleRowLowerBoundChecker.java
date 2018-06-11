package sudoku_game;

class SimpleRowLowerBoundChecker implements LowerBoundChecker {
	private Board board;
	private int row;
	private int lowerBound;
	private boolean success;
	
	public SimpleRowLowerBoundChecker(Board board, int row, int lowerBound) {
		this.board = board;
		this.row = row;
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
		
		for (int col = 0 ; col < dimensions ; col++) {
			if (this.board.getValueAt(this.row, col) != '\u0000')
				count++;
			if (count >= this.lowerBound) {
				this.success = false;
				return;
			}
		}
	}
}