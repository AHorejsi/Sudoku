package sudoku_game;

public class BoxInfo9x9 implements BoxInfo {
	private static BoxInfo boxInfo = new BoxInfo9x9();
	
	private BoxInfo9x9() {}
	
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