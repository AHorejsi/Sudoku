package sudoku_game;

import java.util.Random;


@FunctionalInterface
public interface DifficultyAdjustor {
	public void adjust(Board board, Random rng, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit);
	
	public default void adjust(Board board, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit) {
		this.adjust(board, DefaultRNG.getDefaultGenerator(), lowerRangeOnGivens, upperRangeOnGivens, lowerBoundOnGivensPerUnit);
	}
	
	public static void main(String[] args) {
		Puzzle puzzle = LocalFactory.getInstance().createPuzzle("9x9 hard");
		
		for (int i = 0 ; i < puzzle.getDimensions() ; i++) {
			for (int j = 0 ; j < puzzle.getDimensions() ; j++)
				System.out.print(puzzle.getValueAt(i, j) + " ");
			System.out.println();
		}
	}
}