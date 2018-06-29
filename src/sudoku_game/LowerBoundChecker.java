package sudoku_game;

@FunctionalInterface
interface LowerBoundChecker {
	public boolean check(Board board, int row, int col, int lowerBound);
}