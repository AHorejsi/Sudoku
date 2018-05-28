package sudoku_game;

abstract class Board {
	Cell[][] table;
	
	protected Board() {}
	
	protected Board(int dimensions, char[][] table) {
		this.table = new Cell[dimensions][dimensions];
		
		for (int row = 0 ; row < dimensions ; row++) {
			for (int col = 0 ; col < dimensions ; col++) {
				if (table[row][col] == '\u0000')
					this.table[row][col] = new ConcreteCell(true);
				else
					this.table[row][col] = new ConcreteCell(table[row][col], false);
			}
		}
	}
	
	public char getValueAt(int row, int col) throws IndexOutOfBoundsException {
		if (row < 0 || row >= this.table.length || col < 0 || col >= this.table[0].length)
			throw new IndexOutOfBoundsException();
		return this.table[row][col].getValue();
	}
	
	public void setValueAt(char value, int row, int col) throws IndexOutOfBoundsException {
		if (row < 0 || row >= this.table.length || col < 0 || col >= this.table[0].length)
			throw new IndexOutOfBoundsException();
		this.table[row][col].setValue(value);
	}
	
	public abstract boolean isLegalValue(char value);
	
	public int dimensions() {
		return this.table.length;
	}
	
	public abstract boolean isSolved();
}