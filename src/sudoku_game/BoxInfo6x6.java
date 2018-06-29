package sudoku_game;

/**
 * This class describes the
 * dimensions of six-by-six
 * Sudoku puzzle's boxes
 * @author Alex Horejsi
 */
class BoxInfo6x6 implements BoxInfo {
	private static BoxInfo boxInfo = new BoxInfo6x6();
	
	private BoxInfo6x6() {}
	
	/**
	 * Returns the single instance
	 * of {@code BoxInfo6x6}
	 * @return The single instance
	 * of {@code BoxInfo6x6}
	 */
	public static BoxInfo getInstance() {
		return BoxInfo6x6.boxInfo;
	}
	
	@Override
	public int rowSize() {
		return 2;
	}
	
	@Override
	public int colSize() {
		return 3;
	}
}