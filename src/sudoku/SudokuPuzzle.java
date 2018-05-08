package sudoku;

public abstract class SudokuPuzzle {
	protected SudokuBoard board;
	
	protected SudokuPuzzle() {}
	
	public abstract Difficulty difficulty();
	
	public Dimensions dimensions() {
		return this.board.getDimensions();
	}
	
	public boolean isSolved() {
		return this.board.isSolved();
	}
	
	public void setValue(char value, int row, int col) {
		this.board.setValue(value, row, col);
	}
	
	public char getValue(int row, int col) {
		return this.board.getValue(row, col);
	}
}