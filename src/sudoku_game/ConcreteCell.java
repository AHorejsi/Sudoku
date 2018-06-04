package sudoku_game;

class ConcreteCell extends Cell {
	private char value;
	private boolean editable;
	
	ConcreteCell() {}

	ConcreteCell(char value) {
		this.value = value;
		this.editable = true;
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
		this.setValue('\u0000');
	}
	
	@Override
	public boolean isEditable() {
		return this.editable;
	}

	@Override
	void setEmptyForDifficultyAdjustment() {
		this.value = '\u0000';
	}
}