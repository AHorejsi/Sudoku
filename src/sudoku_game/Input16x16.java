package sudoku_game;

class Input16x16 extends Input {
	Input16x16(char value) {
		super(value);
	}
	
	Input16x16(boolean editable) {
		super(editable);
	}
	
	Input16x16(char value, boolean editable) {
		super(value, editable);
	}
	
	@Override
	public void setValue(char value) throws IllegalArgumentException, IllegalStateException {
		super.setValue(Character.toUpperCase(value));
	}
	
	@Override
	boolean isLegalValue(char value) {
		value = Character.toUpperCase(value);
		return value >= '1' && value <= 'G';
	}
}