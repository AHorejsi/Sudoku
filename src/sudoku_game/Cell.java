package sudoku_game;

class Cell {
	char value;
	boolean editable;
	
	Cell() {}
	
	Cell(boolean editable) {
		this.editable = editable;
	}
	
	Cell(char value, boolean editable) {
		this.value = value;
		this.editable = editable;
	}
	
	char getValue() {
		return this.value;
	}
	
	void setValue(char value) {
		if (!this.editable)
			throw new IllegalStateException();
		this.value = value;
	}
	
	void setEmptyValue() {
		this.value = '\u0000';
	}
	
	boolean isEditable() {
		return this.editable;
	}
}