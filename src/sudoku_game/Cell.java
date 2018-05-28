package sudoku_game;

public interface Cell {
	public char getValue();
	
	public void setValue(char value);
	
	public void setEmptyValue();
	
	public boolean isEditable();
}