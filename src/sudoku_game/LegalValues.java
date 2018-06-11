package sudoku_game;

interface LegalValues {
	public boolean isLegal(char value);
	
	public char[] getLegalValues();
}