package sudoku_game;

class Cell16x16 extends Cell {
	Cell16x16(char value) {
		super(value);
	}
	
	Cell16x16(boolean editable) {
		super(editable);
	}
	
	Cell16x16(char value, boolean editable) {
		super(value, editable);
	}
	
	@Override
	void setValue(char value) {
		super.setValue(Character.toUpperCase(value));
	}
	
	@Override
	boolean isLegalValue(char value) {
		value = Character.toUpperCase(value);
		return (8323583 & (1 << (value - '0' - 1))) != 0;
	}
}