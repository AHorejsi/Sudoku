package sudoku_game;

@FunctionalInterface
public interface LegalValues {
	public boolean isLegal(char value);
}