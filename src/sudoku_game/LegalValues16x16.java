package sudoku_game;

class LegalValues16x16 implements LegalValues {
	private static LegalValues val = new LegalValues16x16();
	private char[] values = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
	
	private LegalValues16x16() {}
	
	public static LegalValues getInstance() {
		return LegalValues16x16.val;
	}
	
	@Override
	public boolean isLegal(char value) {
		return (8323583 & (1 << value)) != 0;
	}
	
	@Override
	public char[] getLegalValues() {
		return this.values;
	}
}