package sudoku_game;

class SimpleBoxLowerBoundChecker implements LowerBoundChecker {
	private Board board;
	private int row;
	private int column;
	private int endRow;
	private int endCol;
	private int lowerBound;
	private boolean success;
	
	public SimpleBoxLowerBoundChecker(Board board, int row, int column, int endRow, int endCol, int lowerBound) {
		this.board = board;
		this.row = row;
		this.column = column;
		this.endRow = endRow;
		this.endCol = endCol;
		this.lowerBound = lowerBound;
		this.success = true;
	}
	
	@Override
	public boolean isSuccessful() {
		return this.success;
	}
	
	@Override
	public void run() {
		int endRow = this.row + this.endRow;
		int endCol = this.row + this.endCol;
		int count = 0;
		
		for (int i = this.row ; i < endRow ; i++) {
			for (int j = this.column ; j < endCol ; j++) {
				if (this.board.getValueAt(this.row, this.column) != '\u0000')
					count++;
				if (count >= this.lowerBound) {
					this.success = false;
					return;
				}
			}
		}
	}
}