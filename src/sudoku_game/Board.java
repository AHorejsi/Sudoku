package sudoku_game;

import adjustor.Cell;

public abstract class Board {
	protected Cell[][] table;
	protected LegalValues legalValues;
	protected Checker checker;
	
	protected Board(LegalValues legalValues, Checker checker) {
		this.legalValues = legalValues;
		this.checker = checker;
	}
	
	public char getValueAt(int row, int col) throws IndexOutOfBoundsException {
		this.outOfBounds(row, col);
		return this.table[row][col].getValue();
	}
	
	public void setValueAt(char value, int row, int col) throws IndexOutOfBoundsException, IllegalArgumentException {
		if (!this.isLegalValue(value))
			throw new IllegalArgumentException();
		this.outOfBounds(row, col);
		this.table[row][col].setValue(value);
	}
	
	public void deleteValueAt(int row, int col) throws IndexOutOfBoundsException, IllegalStateException {
		if (!this.table[row][col].isEditable())
			throw new IllegalStateException();
		this.outOfBounds(row, col);
		this.table[row][col].setEmptyValue();
	}
	
	public int getDimensions() {
		return this.table.length;
	}
	
	public boolean isLegalValue(char value) {
		return this.legalValues.isLegal(value);
	}
	
	public boolean isSolved() {
		return this.checker.isSolved(this);
	}
	
	private void outOfBounds(int row, int col) {
		if (row < 0 || row >= this.table.length || col < 0 || col >= this.table.length)
			throw new IndexOutOfBoundsException();
	}
}