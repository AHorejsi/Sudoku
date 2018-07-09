package sudoku_game;

/**
 * This class defines the legal <tt>char</tt>
 * values for six-by-six Sudoku puzzles.<br>
 * Legal values: 1, 2, 3, 4, 5, 6
 * @author Alex Horejsi
 */
public class LegalValues6x6 extends LegalValues {
	private static LegalValues val = new LegalValues6x6();
	
	private LegalValues6x6() {
		super(new char[] {'1', '2', '3', '4', '5', '6'});
	}
	
	/**
	 * Returns the single instance
	 * of {@code LegalValues6x6}
	 * @return The single instance
	 * of {@code LegalValues6x6}
	 */
	public static LegalValues getInstance() {
		return LegalValues6x6.val;
	}
	
	@Override
	public boolean isLegal(char value) {
		return value >= '1' && value <= '6';
	}
}