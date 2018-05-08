package sudoku;

public abstract class SudokuBoard {
	protected Input[][] table;
	
	protected SudokuBoard() {}
	
	public void setValue(char value, int row, int col) {
		this.table[row][col].setValue(value);
	}
	
	public char getValue(int row, int col) {
		return this.table[row][col].value;
	}
	
	public abstract Dimensions getDimensions();
	
	public abstract boolean isSolved();
}