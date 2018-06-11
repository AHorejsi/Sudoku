package sudoku_game;

/**
 * Concrete implementation of the {@code Cell} class
 * @author Alex Horejsi
 */
class ConcreteCell extends Cell {
	private char value;
	private boolean editable;
	
	/**
	 * Creates a new {@code ConcreteCell} and
	 * gives it the given initial value
	 * @param value The initial value for this
	 * cell
	 */
	ConcreteCell(char value) {
		this.value = value;
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