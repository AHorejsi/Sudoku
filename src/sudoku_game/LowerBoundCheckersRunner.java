package sudoku_game;

interface LowerBoundCheckersRunner {
	public boolean check(Board board, int row, int col, int lowerBound);
}