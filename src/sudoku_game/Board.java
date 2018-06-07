package sudoku_game;

import java.util.Objects;
import java.util.Random;

public abstract class Board implements Cloneable {
	Cell[][] table;
	LegalValues legalValues;
	Checker checker;
	
	protected Board(LegalValues legalValues, Checker checker, Cell[][] table) {
		this.legalValues = Objects.requireNonNull(legalValues);
		this.checker = Objects.requireNonNull(checker);
		this.table = Objects.requireNonNull(table);
	}
	
	protected Board(LegalValues legalValues, Checker checker, Cell[][] table, Random rng) {
		this.legalValues = Objects.requireNonNull(legalValues);
		this.checker = Objects.requireNonNull(checker);
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
	
	void doMixing(Mixer mixer) {
		mixer.mix(this.table);
	}
	
	void doMixing(Mixer mixer, Random rng) {
		mixer.mix(this.table, rng);
	}
	
	@Override
	protected Board clone() {
		try {
			Board board = (Board)super.clone();
			board.table = new Cell[board.getDimensions()][board.getDimensions()];
			for (int i = 0 ; i < this.table.length ; i++) {
				for (int j = 0 ; j < this.table.length ; j++)
					board.table[i][j] = this.table[i][j].clone();
			}
			
			return board;
		} catch (CloneNotSupportedException ex) {
			throw new InternalError();
		}
	}
}