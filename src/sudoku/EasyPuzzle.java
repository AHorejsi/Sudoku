package sudoku;

public class EasyPuzzle extends SudokuPuzzle {
	protected EasyPuzzle(SudokuBoard board) {
		super.board = board;
	}
	
	@Override
	public Difficulty difficulty() {
		return Difficulty.EASY;
	}
}