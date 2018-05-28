package sudoku_game;

public class LegalValues16x16 implements LegalValues {
	private static LegalValues16x16 val = new LegalValues16x16();
	
	private LegalValues16x16() {}
	
	public static LegalValues16x16 getInstance() {
		return LegalValues16x16.val;
	}
	
	@Override
	public boolean isLegal(char value) {
		return (8323583 & (1 << value)) != 0;
	}
}