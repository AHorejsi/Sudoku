package sudoku_game;

/**
 * This class defines the legal <tt>char</tt>
 * values for eight-by-eight Sudoku puzzles.<br>
 * Legal values: 1, 2, 3, 4, 5, 6, 7, 8, 9
 * @author Alex Horejsi
 */
public class LegalValues8x8 extends LegalValues {
	private static LegalValues val = new LegalValues8x8();
	
	private LegalValues8x8() {
		super(new char[] {'1', '2', '3', '4', '5', '6', '7', '8'});
	}
	
	/**
	 * Returns the single instance of
	 * {@code LegalValues8x8}
	 * @return The single instance of
	 * {@code LegalValues8x8}
	 */
	public static LegalValues getInstance() {
		return LegalValues8x8.val;
	}
	
	@Override
	public boolean isLegal(char value) {
		return value >= '1' && value <= '8';
	}
}