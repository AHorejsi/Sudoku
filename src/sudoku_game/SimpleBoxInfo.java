package sudoku_game;

/**
 * Simple implementation
 * of {@code BoxInfo}
 * @author Alex Horejsi
 */
public class SimpleBoxInfo implements BoxInfo {
	private int rows;
	private int cols;
	
	/**
	 * Defines the row and column
	 * sizes for this {@code BoxInfo}
	 * @param rowSize The number of
	 * rows for the boxes
	 * @param colSize The number of
	 * columns for the boxes
	 */
	public SimpleBoxInfo(int rowSize, int colSize) {
		this.rows = rowSize;
		this.cols = colSize;
	}
	
	@Override
	public int rowSize() {
		return this.rows;
	}
	
	@Override
	public int colSize() {
		return this.cols;
	}
}