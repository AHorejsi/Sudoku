package sudoku_game;

import java.util.Objects;

public abstract class Puzzle {
	Board board;
	
	protected Puzzle(Board board) {
		this.board = Objects.requireNonNull(board);
	}
	
	public char getValueAt(int row, int col) throws IndexOutOfBoundsException {
		return this.board.getValueAt(row, col);
	}
	
	public void setValueAt(char value, int row, int col) throws IndexOutOfBoundsException, IllegalArgumentException {
		this.board.setValueAt(value, row, col);
	}
	
	public void deleteValueAt(int row, int col) throws IllegalStateException, IndexOutOfBoundsException {
		this.board.deleteValueAt(row, col);
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
	
	public abstract String getDifficulty();
}