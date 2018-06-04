package sudoku_game;

@FunctionalInterface
interface Checker {
	public boolean isSolved(Board board);
}