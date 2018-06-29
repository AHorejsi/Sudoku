package sudoku_game;

/**
 * This class defines the legal <tt>char</tt>
 * values for four-by-four Sudoku puzzles.<br>
 * Legal values: 1, 2, 3, 4
 * @author Alex Horejsi
 */
class LegalValues4x4 extends LegalValues {
	private static LegalValues val = new LegalValues4x4();
	
	private LegalValues4x4() {
		super(new char[] {'1', '2', '3', '4'});
	}
	
	/**
	 * Returns the single instance of
	 * {@code LegalValues4x4}
	 * @return The single instance of
	 * {@code LegalValues4x4}
	 */
	public static LegalValues getInstance() {
		return LegalValues4x4.val;
	}
	
	@Override
	public boolean isLegal(char value) {
		return value >= '1' && value <= '4';
	}
}