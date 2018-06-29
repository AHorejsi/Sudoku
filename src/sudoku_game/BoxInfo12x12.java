package sudoku_game;

/**
 * This class describes
 * the dimensions of
 * twelve-by-twelve Sudoku
 * puzzle's boxes
 * @author Alex Horejsi
 */
class BoxInfo12x12 implements BoxInfo {
	private static BoxInfo boxInfo = new BoxInfo12x12();
	
	private BoxInfo12x12() {}
	
	/**
	 * Returns the single instance
	 * of {@code BoxInfo12x12}
	 * @return The single instance
	 * of {@code BoxInfo12x12}
	 */
	public static BoxInfo getInstance() {
		return BoxInfo12x12.boxInfo;
	}
	
	@Override
	public int rowSize() {
		return 3;
	}
	
	@Override
	public int colSize() {
		return 4;
	}
}