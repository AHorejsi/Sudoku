package sudoku;

public class HardPuzzle extends SudokuPuzzle {
	protected HardPuzzle(SudokuBoard board) {
		super.board = board;
	}
	
	@Override
	public Difficulty difficulty() {
		return Difficulty.HARD;
	}
}