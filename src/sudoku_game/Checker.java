package sudoku_game;

@FunctionalInterface
public interface Checker {
	public boolean isSolved(Board board);
}