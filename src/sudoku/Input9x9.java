package sudoku;

public class Input9x9 extends Input {
	public Input9x9(boolean editable) {
		super(editable);
	}
		
	@Override
	public void setValue(char value) throws IllegalArgumentException, IllegalStateException {
		if (value < '1' || value > '9')
			throw new IllegalArgumentException();
		if (!this.editable)
			throw new IllegalStateException();
		super.value = value;
	}
	
	@Override
	public boolean isLegalValue(char value) {
		return value >= '1' && value <= '9';
	}
}