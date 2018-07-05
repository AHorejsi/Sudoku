package sudoku_game;

/**
 * This class defines the legal <tt>char</tt>
 * values for twelve-by-twelve Sudoku puzzles.<br>
 * Legal values: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B
 * @author Alex Horejsi
 */
public class LegalValues12x12 extends LegalValues {
	private static LegalValues val = new LegalValues12x12();
	
	private LegalValues12x12() {
		super(new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B'});
	}
	
	/**
	 * Returns the single instance of
	 * {@code LegalValues12x12}
	 * @return The single instance of
	 * {@code LegalValues12x12}
	 */
	public static LegalValues getInstance() {
		return LegalValues12x12.val;
	}
}