package sudoku_game;

/**
 * This class defines the legal <tt>char</tt>
 * values for nine-by-nine Sudoku puzzles.
 * <br>
 * Legal values: 1, 2, 3, 4, 5, 6, 7, 8, 9
 * @author Alex Horejsi
 */
class LegalValues9x9 implements LegalValues {
	private static LegalValues val = new LegalValues9x9();
	private char[] values = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	private LegalValues9x9() {}
	
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
		return (511 & (1 << (value - 1))) == 0;
	}
	
	@Override
	public char[] getLegalValues() {
		return this.values;
	}
	
	public static void main(String[] args) {
		char[] values = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
		Puzzle p = LocalFactory.getInstance().createPuzzle("9x9 basic");
		
		for (int i = 0 ; i < values.length ; i++) {
			if (!p.isLegalValue(values[i])) {
				System.out.println("Fail " + values[i]);
				System.exit(1);
			}
		}
		
		System.out.println("Success");
		
//		int a = 0;
//		for (char val = '1' ; val <= 'G' ; val++)
//			a |= (1 << (val - '0' - 1));
//		
//		System.out.println(a);
	}
}