package sudoku_game;

import java.util.Random;

/**
 * Selects values to remove from a Sudoku puzzle randomly
 * @author Alex Horejsi
 */
class RandomizedDifficultyAdjustor implements DifficultyAdjustor {
	private static DifficultyAdjustor adjustor = new RandomizedDifficultyAdjustor();
	
	private RandomizedDifficultyAdjustor() {}
	
	/**
	 * Returns the single instance
	 * of {@code RandomizedDifficultyAdjustor}
	 * @return The single instance
	 * of {@code RandomizedDifficultyAdjustor}
	 */
	public static DifficultyAdjustor getInstance() {
		return RandomizedDifficultyAdjustor.adjustor;
	}

	@Override
	public void adjust(Board board, Random rng, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit) {
		int amount = this.determineAmountOfGivens(rng, lowerRangeOnGivens, upperRangeOnGivens, 
				board.getDimensions() * board.getDimensions());
		int lowerBound = this.determineLowerBound(lowerBoundOnGivensPerUnit, board.getDimensions());
		this.performAdjustment(board, rng, amount, lowerBound);
		DifficultyAdjustor.setEditableCells(board.table);
	}
	
	private int determineAmountOfGivens(Random rng, int lower, int upper, int total) {
		int percent = rng.nextInt((upper - lower) + 1) + lower;
		return (int)Math.round(total * (percent / 100.0));
	}
	
	private int determineLowerBound(int lowerBoundOnGivensPerUnit, int dimensions) {
		return (int)Math.round(dimensions * (lowerBoundOnGivensPerUnit / 100.0));
	}
	
	private void performAdjustment(Board board, Random rng, int amount, int lowerBound) {
		Cell[][] table = board.table;
		int length = table.length;
		int current = length * length;
		Solver solver = BacktrackingSolver.getInstance();
		LowerBoundCheckersRunner runner = SimpleLowerBoundCheckersRunner.getInstance();
		
		while (current > amount) {
			int row;
			int col;
			
			do {
				row = rng.nextInt(length);
				col = rng.nextInt(length);
			} while (table[row][col].getValue() == '\u0000');
			
			if (runner.check(board, row, col, lowerBound)) {
				char value = table[row][col].getValue();
				table[row][col].setEmptyForSetUp();
				current--;
				
				if (!solver.hasUniqueSolution(board)) {
					table[row][col].setValueForSetUp(value);
					current++;
				}
			}
		}
	}
}