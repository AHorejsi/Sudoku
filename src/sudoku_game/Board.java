package sudoku_game;

import java.util.Random;

public abstract class Board {
	Cell[][] table;
	LegalValues legalValues;
	Checker checker;
	
	protected Board(LegalValues legalValues, Checker checker, Cell[][] table) {
		this.legalValues = legalValues;
		this.checker = checker;
		this.table = table;
		RotateFlipSwapMixer.getInstance().mix(table);
	}
	
	protected Board(LegalValues legalValues, Checker checker, Cell[][] table, Random rng) {
		this(legalValues, checker, table);
		RotateFlipSwapMixer.getInstance().mix(table, rng);
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