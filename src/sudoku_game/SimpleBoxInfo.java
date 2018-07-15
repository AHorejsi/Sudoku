package sudoku_game;

public class SimpleBoxInfo implements BoxInfo {
	private int rows;
	private int cols;
	
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