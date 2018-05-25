package sudoku_game;

abstract class Cell {
	char value;
	boolean editable;
	
	Cell() {}
	
	Cell(char value) {
		this.value = value;
	}
	
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
		if (!this.isLegalValue(value))
			throw new IllegalArgumentException();
		if (!this.editable)
			throw new IllegalStateException();
		this.value = value;
	}
	
	void setEmptyValue() {
		this.value = '\u0000';
		this.editable = true;
	}
	
	boolean isEditable() {
		return this.editable;
	}
	
	abstract boolean isLegalValue(char value);
}