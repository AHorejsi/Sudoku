package sudoku_game;

public abstract class Cell {
	public abstract char getValue();
	
	public abstract void setValue(char value);
	
	public abstract void setEmptyValue();
	
	public abstract boolean isEditable();
	
	abstract void setEmptyForSetUp();
	
	abstract void setValueForSetUp(char value);
	
	abstract void setEditable(boolean editable);
}