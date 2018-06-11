package sudoku_game;

@FunctionalInterface
interface Solver {
	public boolean hasUniqueSolution(Board board);
}