package sudoku_game;

/**
 * This class defines the dimensions of
 * sixteen-by-sixteen Sudoku puzzle's
 * boxes
 * @author Alex Horejsi
 */
class BoxInfo16x16 implements BoxInfo {
	private static BoxInfo boxInfo = new BoxInfo16x16();
	
	private BoxInfo16x16() {}
	
	/**
	 * Returns the single instance of
	 * {@code BoxInfo16x16}
	 * @return The single instance of
	 * {@code BoxInfo16x16}
	 */
	public static BoxInfo getInstance() {
		return BoxInfo16x16.boxInfo;
	}
	
	@Override
	public int rowSize() {
		return 4;
	}
	
	@Override
	public int colSize() {
		return 4;
	}
}