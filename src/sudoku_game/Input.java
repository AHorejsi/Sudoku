package sudoku_game;

abstract class Input {
	char value;
	boolean editable;
	
	Input() {}
	
	Input(char value) {
		this.value = value;
	}
	
	Input(boolean editable) {
		this.editable = editable;
	}
	
	Input(char value, boolean editable) {
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
	}
	
	boolean isEditable() {
		return this.editable;
	}
	
	void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	abstract boolean isLegalValue(char value);
}