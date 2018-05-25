package sudoku_game;

abstract class Board {
	protected Cell[][] table;
	
	protected Board(int dimensions) {
		this.table = new Cell[dimensions][dimensions];
	}
	
	void setValue(char value, int row, int col) {
		this.table[row][col].setValue(value);
	}
	
	char getValue(int row, int col) {
		return this.table[row][col].value;
	}
	
	abstract boolean isSolved();
}