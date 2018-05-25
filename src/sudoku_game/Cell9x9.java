package sudoku_game;

class Cell9x9 extends Cell {
	Cell9x9(char value) {
		super(value);
	}
	
	Cell9x9(boolean editable) {
		super(editable);
	}
	
	Cell9x9(char value, boolean editable) {
		super(value, editable);
	}
	
	@Override
	boolean isLegalValue(char value) {
		return value >= '1' && value <= '9';
	}
}