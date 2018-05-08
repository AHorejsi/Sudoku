package sudoku;

import java.util.Random;

public interface SudokuFactory {
	public SudokuPuzzle generatePuzzle(Dimensions dimensions, Difficulty difficulty, Random rand);
}