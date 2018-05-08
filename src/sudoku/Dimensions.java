package sudoku;

public enum Dimensions {
	NINE_BY_NINE (9, 81),
	SIXTEEN_BY_SIXTEEN (16, 256);
	
	private int totalNumberOfSpaces;
	private int perBoxRowColumn;
	
	private Dimensions(int perBoxRowColumn, int total) {
		this.totalNumberOfSpaces = total;
		this.perBoxRowColumn = perBoxRowColumn;
	}
	
	public int getTotalNumberOfSpaces() {
		return this.totalNumberOfSpaces;
	}
	
	public int getPerBoxRowColumn() {
		return this.perBoxRowColumn;
	}
}