package sudoku_game;

@FunctionalInterface
public interface Solver {
	public boolean hasUniqueSolution(Board board);
}