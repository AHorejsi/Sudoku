package sudoku_game;

import java.util.Random;

public class RandomizedDifficultyAdjustor implements DifficultyAdjustor {
	private static RandomizedDifficultyAdjustor adjustor = new RandomizedDifficultyAdjustor();
	
	private RandomizedDifficultyAdjustor() {}
	
	public static DifficultyAdjustor getInstance() {
		return RandomizedDifficultyAdjustor.adjustor;
	}

	@Override
	public void adjust(Board board, Random rng, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit) {
		Cell[][] table = board.table;
		int amountOfGivens = this.determineAmountOfGivens(rng, lowerRangeOnGivens, upperRangeOnGivens, table.length * table.length);
		this.performAdjustment(table, rng, amountOfGivens, lowerBoundOnGivensPerUnit);
	}
	
	private int determineAmountOfGivens(Random rng, int lower, int upper, int total) {
		int percent = rng.nextInt((upper - lower) + 1) + lower;
		return (int)(total * (percent / 100.0));
	}
	
	private void performAdjustment(Cell[][] table, Random rng, int amount, int lowerBound) {
		int length = table.length;
		int current = length * length;
		
		while (current > amount) {
			int row = rng.nextInt(length) + 1;
			int col = rng.nextInt(length) + 1;
			
			if (this.canBeDug(table, row, col, lowerBound)) {
				table[row][col].setEmptyForDifficultyAdjustment();
				current--;
			}
		}
	}
	
	private boolean canBeDug(Cell[][] table, int row, int col, int lowerBound) {
		
	}
}