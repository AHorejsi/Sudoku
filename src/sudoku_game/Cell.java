package sudoku_game;

public abstract class Cell implements Cloneable {
	public abstract char getValue();
	
	public abstract void setValue(char value);
	
	public abstract void setEmptyValue();
	
	public abstract boolean isEditable();
	
	abstract void setEmptyForDifficultyAdjustment();
	
	abstract void setValueForDifficultyAdjustment(char value);
	
	@Override
	protected Cell clone() {
		try {
			return (Cell)super.clone();
		} catch (CloneNotSupportedException ex) {
			throw new InternalError();
		}
	}
}