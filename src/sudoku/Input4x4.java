package sudoku;

public class Input4x4 extends Input {
	protected Input4x4() {}
	
	protected Input4x4(boolean editable) {
		super(editable);
	}
	
	@Override
	public void setValue(char value) throws IllegalArgumentException, IllegalStateException {
		if (value < '1' || value > '4')
			throw new IllegalArgumentException();
		if (!this.editable)
			throw new IllegalStateException();
		super.value = value;
	}
	
	@Override
	public boolean isLegalValue(char value) {
		return value >= '1' && value <= '4';
	}
}