package sudoku;

public abstract class SudokuPuzzle {
	protected Input[][] board;
	
	protected SudokuPuzzle() {}
	
	public int dimensions() {
		return this.board.length;
	}
	
	public void inputValue(char value, int row, int col) throws IndexOutOfBoundsException, IllegalArgumentException, IllegalStateException {
		this.board[row][col].setValue(value);
	}
	
	public abstract boolean isSolved();
	
	protected abstract void generatePuzzle();
}