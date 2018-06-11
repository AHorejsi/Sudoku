package sudoku_game;

class LegalValues9x9 implements LegalValues {
	private static LegalValues val = new LegalValues9x9();
	private char[] values = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	private LegalValues9x9() {}
	
	public static LegalValues getInstance() {
		return LegalValues9x9.val;
	}
	
	@Override
	public boolean isLegal(char value) {
		return (511 & (1 << value)) != 0;
	}
	
	@Override
	public char[] getLegalValues() {
		return this.values;
	}
}