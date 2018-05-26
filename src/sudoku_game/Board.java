package sudoku_game;

abstract class Board {
	Cell[][] table;
	
	Board() {}
	
	char getValueAt(int row, int col) throws IndexOutOfBoundsException {
		if (row < 0 || row >= this.table.length || col < 0 || col >= this.table[0].length)
			throw new IndexOutOfBoundsException();
		return this.table[row][col].getValue();
	}
	
	void setValueAt(char value, int row, int col) throws IndexOutOfBoundsException {
		if (row < 0 || row >= this.table.length || col < 0 || col >= this.table[0].length)
			throw new IndexOutOfBoundsException();
		this.table[row][col].setValue(value);
	}
	
	abstract boolean isLegalValue(char value);
	
	int dimensions() {
		return this.table.length;
	}
	
	abstract boolean isSolved();
}