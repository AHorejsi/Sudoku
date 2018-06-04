package sudoku_game;

@FunctionalInterface
interface LegalValues {
	public boolean isLegal(char value);
}