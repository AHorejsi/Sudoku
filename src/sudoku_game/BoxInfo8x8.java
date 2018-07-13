package sudoku_game;

/**
 * This class describes the dimensions of
 * eight-by-eight Sudoku puzzle's boxes
 * @author Alex Horejsi
 */
public class BoxInfo8x8 implements BoxInfo {
	private static BoxInfo boxInfo = new BoxInfo8x8();
	
	private BoxInfo8x8() {}
	
	/**
	 * Returns the single instance of
	 * {@code BoxInfo8x8}
	 * @return The single instance of
	 * {@code BoxIngo8x8}
	 */
	public static BoxInfo getInstance() {
		return BoxInfo8x8.boxInfo;
	}
	
	@Override
	public int rowSize() {
		return 2;
	}
	
	@Override
	public int colSize() {
		return 4;
	}
}