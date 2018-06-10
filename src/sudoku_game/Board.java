package sudoku_game;

import java.util.Objects;

public abstract class Board {
	Cell[][] table;
	LegalValues legalValues;
	Checker checker;
	BoxInfo boxInfo;
	
	protected Board(LegalValues legalValues, Checker checker, BoxInfo boxInfo, Cell[][] table) {
		this.legalValues = Objects.requireNonNull(legalValues);
		this.checker = Objects.requireNonNull(checker);
		this.boxInfo = boxInfo;
		this.table = Objects.requireNonNull(table);
	}
	
	public char getValueAt(int row, int col) throws IndexOutOfBoundsException {
		this.outOfBounds(row, col);
		return this.table[row][col].getValue();
	}
	
	public boolean editableCellAt(int row, int col) throws IndexOutOfBoundsException {
		this.outOfBounds(row, col);
		return this.table[row][col].isEditable();
	}
	
	public void setValueAt(char value, int row, int col) throws IndexOutOfBoundsException, IllegalArgumentException, IllegalStateException {
		if (!this.isLegalValue(value))
			throw new IllegalArgumentException("Illegal value: " + value);
		if (!this.table[row][col].isEditable())
			throw new IllegalStateException("Non-editable cell");
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
	
	public int rowSizeInBox() {
		return this.boxInfo.rowSize();
	}
	
	public int colSizeInBox() {
		return this.boxInfo.colSize();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0 ; i < table.length ; i++) {
			for (int j = 0 ; j < table.length ; j++) {
				sb.append(this.table[i][j].getValue());
				sb.append(' ');
			}
			
			sb.append('\n');
		}
		
		return sb.toString();
	}
}