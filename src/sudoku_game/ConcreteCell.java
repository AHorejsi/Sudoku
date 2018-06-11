package sudoku_game;

public class ConcreteCell extends Cell {
	private char value;
	private boolean editable;
	
	ConcreteCell() {}

	ConcreteCell(char value) {
		this.value = value;
		this.editable = true;
	}
	
	ConcreteCell(Cell cell) {
		this.value = cell.getValue();
		this.editable = cell.isEditable();
	}
	
	@Override
	public char getValue() {
		return this.value;
	}
	
	@Override
	public void setValue(char value) throws IllegalStateException {
		if (!this.editable)
			throw new IllegalStateException();
		this.value = value;
	}
	
	@Override
	public void setEmptyValue() throws IllegalStateException {
		if (!this.editable)
			throw new IllegalStateException();
		this.value = '\u0000';
	}
	
	@Override
	public boolean isEditable() {
		return this.editable;
	}

	@Override
	void setEmptyForSetUp() {
		this.value = '\u0000';
	}
	
	@Override
	void setValueForSetUp(char value) {
		this.value = value;
	}
	
	@Override
	void setEditable(boolean editable) {
		this.editable = editable;
	}
}