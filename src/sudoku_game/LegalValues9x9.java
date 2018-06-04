package sudoku_game;

class LegalValues9x9 implements LegalValues {
	private static LegalValues9x9 val = new LegalValues9x9();
	
	private LegalValues9x9() {}
	
	public static LegalValues9x9 getInstance() {
		return LegalValues9x9.val;
	}
	
	@Override
	public boolean isLegal(char value) {
		return (511 & (1 << value)) != 0;
	}
}