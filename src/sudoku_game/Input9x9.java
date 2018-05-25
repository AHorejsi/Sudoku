package sudoku_game;

class Input9x9 extends Input {
	Input9x9(char value) {
		super(value);
	}
	
	Input9x9(boolean editable) {
		super(editable);
	}
	
	Input9x9(char value, boolean editable) {
		super(value, editable);
	}
	
	@Override
	boolean isLegalValue(char value) {
		return value >= '1' && value <= '9';
	}
}