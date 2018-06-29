package sudoku_game;

@FunctionalInterface
interface LowerBoundChecker {
	boolean check(Board board, int row, int col, int lowerBound);
}