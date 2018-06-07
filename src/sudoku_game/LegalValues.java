package sudoku_game;

public interface LegalValues {
	public boolean isLegal(char value);
	
	public char[] getLegalValues();
}