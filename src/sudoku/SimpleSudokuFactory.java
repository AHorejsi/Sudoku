package sudoku;

import java.util.Random;

public class SimpleSudokuFactory implements SudokuFactory {
	@Override
	public SudokuPuzzle generatePuzzle(Dimensions dimensions, Difficulty difficulty, Random rand) throws NullPointerException {
		if (dimensions == null || difficulty == null || rand == null)
			throw new NullPointerException();
		
		SudokuPuzzle puzzle = null;
		
		if (dimensions == Dimensions.NINE_BY_NINE) {
			if (difficulty == Difficulty.EASY) {
				puzzle = new EasyPuzzle(new Board9x9());
			}
			else if (difficulty == Difficulty.HARD) {
				puzzle = new HardPuzzle(new Board9x9());
			}
		}
		else if (dimensions == Dimensions.SIXTEEN_BY_SIXTEEN) {
			if (difficulty == Difficulty.EASY) {
				puzzle = new EasyPuzzle(new Board16x16());
			}
			else if (difficulty == Difficulty.HARD) {
				puzzle = new HardPuzzle(new Board16x16());
			}
		}
		
		return puzzle;
	}
}