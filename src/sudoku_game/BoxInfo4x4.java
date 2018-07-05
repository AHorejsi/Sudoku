package sudoku_game;

/**
 * This class describes the dimensions of
 * four-by-four Sudoku puzzle's boxes
 * @author Alex Horejsi
 */
public class BoxInfo4x4 implements BoxInfo {
	private static BoxInfo boxInfo = new BoxInfo4x4();
	
	private BoxInfo4x4() {}
	
	/**
	 * Returns the single instance
	 * of {@code BoxInfo4x4}
	 * @return The single instance
	 * of {@code BoxInfo4x4}
	 */
	public static BoxInfo getInstance() {
		return BoxInfo4x4.boxInfo;
	}
	
	@Override
	public int rowSize() {
		return 2;
	}
	
	@Override
	public int colSize() {
		return 2;
	}
}