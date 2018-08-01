package sudoku_game;

public interface SafeCellChecker {
	public boolean safe(Cell[][] table, char value, int row, int col, int boxRow, int boxCol);
}