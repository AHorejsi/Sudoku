package sudoku_game;

interface Cell {
	public char getValue();
	
	public void setValue(char value);
	
	public void setEmptyValue();
	
	public boolean isEditable();
}