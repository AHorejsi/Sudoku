package sudoku_game;

public abstract class Puzzle {
	Board board;
	
	protected Puzzle(Board board) {
		this.board = board;
	}
	
	public abstract String getDifficulty();
}