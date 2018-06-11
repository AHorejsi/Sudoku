package sudoku_game;

import java.util.Random;

public abstract class Puzzle {
	Board board;
	
	Puzzle(Board board, DifficultyAdjustor adjustor, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit) {
		this(board, adjustor, lowerRangeOnGivens, upperRangeOnGivens, lowerBoundOnGivensPerUnit, DefaultRNG.getDefaultGenerator());
	}
	
	Puzzle(Board board, DifficultyAdjustor adjustor, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit,
			Random rng) {
		adjustor.adjust(board, rng, lowerRangeOnGivens, upperRangeOnGivens, lowerBoundOnGivensPerUnit);
		this.board = board;
	}
	
	public char getValueAt(int row, int col) throws IndexOutOfBoundsException {
		return this.board.getValueAt(row, col);
	}
	
	public void setValueAt(char value, int row, int col) throws IndexOutOfBoundsException, IllegalArgumentException, IllegalStateException {
		this.board.setValueAt(value, row, col);
	}
	
	public void deleteValueAt(int row, int col) throws IllegalStateException, IndexOutOfBoundsException {
		this.board.deleteValueAt(row, col);
	}
	
	public boolean editableCellAt(int row, int col) {
		return this.board.editableCellAt(row, col);
	}
	
	public int getDimensions() {
		return this.board.getDimensions();
	}
	
	public boolean isLegalValue(char value) {
		return this.board.isLegalValue(value);
	}
	
	public boolean isSolved() {
		return this.board.isSolved();
	}
	
	public int rowSizeInBox() {
		return this.board.rowSizeInBox();
	}
	
	public int colSizeInBox() {
		return this.board.colSizeInBox();
	}
	
	public abstract String getDifficulty();
}