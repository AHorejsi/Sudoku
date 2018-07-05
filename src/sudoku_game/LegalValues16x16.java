package sudoku_game;

/**
 * This class defines the legal <tt>char</tt>
 * values for sixteen-by-sixteen Sudoku puzzles.<br>
 * Legal values: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F
 * @author Alex Horejsi
 */
public class LegalValues16x16 extends LegalValues {
	private static LegalValues val = new LegalValues16x16();
	
	private LegalValues16x16() {
		super(new char[] {'0', '1', '2', '3', '4', '5', '6' ,'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'});
	}
	
	/**
	 * Returns the single instance of
	 * {@code LegalValues16x16}
	 * @return The single instance of
	 * {@code LegalValues16x16}
	 */
	public static LegalValues getInstance() {
		return LegalValues16x16.val;
	}
}