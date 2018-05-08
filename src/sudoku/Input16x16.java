package sudoku;

public class Input16x16 extends Input {
	protected Input16x16() {}
	
	protected Input16x16(boolean editable) {
		super(editable);
	}
	
	@Override
	public void setValue(char value) throws IllegalArgumentException, IllegalStateException {
		value = Character.toUpperCase(value);
		if (value < '1' || value > 'G')
			throw new IllegalArgumentException();
		if (!this.editable)
			throw new IllegalStateException();
		super.value = value;
	}
	
	@Override
	public boolean isLegalValue(char value) {
		value = Character.toUpperCase(value);
		return value >= '1' && value <= 'G';
	}
}