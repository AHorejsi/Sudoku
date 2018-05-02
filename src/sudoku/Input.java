package sudoku;

public abstract class Input {
	protected char value;
	protected boolean editable;
	
	protected Input() {}
	
	protected Input(boolean editable) {
		this.editable = editable;
	}
	
	public char getValue() {
		return this.value;
	}
	
	public abstract void setValue(char value);
	
	public void setEmptyValue() {
		this.value = '\u0000';
	}
	
	public boolean isEditable() {
		return this.editable;
	}
	
	public abstract boolean isLegalValue(char value);
}