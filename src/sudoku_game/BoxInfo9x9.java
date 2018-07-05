package sudoku_game;

/**
 * This class describes the dimensions of
 * nine-by-nine Sudoku puzzle's boxes
 * @author Alex Horejsi
 */
public class BoxInfo9x9 implements BoxInfo {
	private static BoxInfo boxInfo = new BoxInfo9x9();
	
	private BoxInfo9x9() {}
	
	/**
	 * Returns the single instance of
	 * {@code BoxInfo9x9}
	 * @return The single instance of
	 * {@code BoxIngo9x9}
	 */
	public static BoxInfo getInstance() {
		return BoxInfo9x9.boxInfo;
	}
	
	@Override
	public int rowSize() {
		return 3;
	}
	
	@Override
	public int colSize() {
		return 3;
	}
}