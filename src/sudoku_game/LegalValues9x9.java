package sudoku_game;

/**
 * This class defines the legal <tt>char</tt>
 * values for nine-by-nine Sudoku puzzles.<br>
 * Legal values: 1, 2, 3, 4, 5, 6, 7, 8, 9
 * @author Alex Horejsi
 */
public class LegalValues9x9 extends LegalValues {
	private static LegalValues val = new LegalValues9x9();
	
	private LegalValues9x9() {
		super(new char[] {'1', '2', '3', '4', '5', '6', '7', '8', '9'});
	}
	
	/**
	 * Returns the single instance of
	 * {@code LegalValues9x9}
	 * @return The single instance of
	 * {@code LegalValues9x9}
	 */
	public static LegalValues getInstance() {
		return LegalValues9x9.val;
	}
	
	@Override
	public boolean isLegal(char value) {
		return Character.isDigit(value);
	}
}