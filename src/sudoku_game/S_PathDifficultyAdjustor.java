package sudoku_game;

import java.util.Random;

/**
 * Moves through the Sudoku puzzle in an
 * S-like pattern. As the Sudoku puzzle
 * is being traversed, it is determined
 * which values should be removed
 * @author Alex Horejsi
 */
class S_PathDifficultyAdjustor implements DifficultyAdjustor {
	private static DifficultyAdjustor adjustor = new S_PathDifficultyAdjustor();
	
	private S_PathDifficultyAdjustor() {}
	
	/**
	 * Returns the single instance
	 * of {@code S_PathDifficultyAdjustor}
	 * @return The single instance
	 * of {@code S_PathDifficultyAdjustor}
	 */
	public static DifficultyAdjustor getInstance() {
		return S_PathDifficultyAdjustor.adjustor;
	}
	
	@Override
	public void adjust(Board board, Random rng, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit) {
		int amount = this.determineAmountOfGivens(rng, lowerRangeOnGivens, upperRangeOnGivens, 
				board.getDimensions() * board.getDimensions());
		int lowerBound = this.determineLowerBound(lowerBoundOnGivensPerUnit, board.getDimensions());
		this.performAdjustment(board, amount, lowerBound);
		DifficultyAdjustor.setEditableCells(board.table);
	}
	
	private int determineAmountOfGivens(Random rng, int lower, int upper, int total) {
		int percent = rng.nextInt((upper - lower) + 1) + lower;
		return (int)Math.round(total * (percent / 100.0));
	}
	
	private int determineLowerBound(int lowerBoundOnGivensPerUnit, int dimensions) {
		return (int)Math.round(dimensions * (lowerBoundOnGivensPerUnit / 100.0));
	}
	
	private void performAdjustment(Board board, int amount, int lowerBound) {
		Cell[][] table = board.table;
		int current = table.length * table.length;
		Solver solver = BacktrackingSolver.getInstance();
		LowerBoundCheckersRunner runner = SimpleLowerBoundCheckersRunner.getInstance();
		
		for (int i = 0 ; i < table.length ; i++) {
			for (int j = 0 ; j < table.length ; j++) {
				if (runner.check(board, i, j, lowerBound)) {
					char value1 = table[i][j].getValue();
					char value2 = table[table.length - i - 1][table.length - j - 1].getValue();
					table[i][j].setEmptyForSetUp();
					table[table.length - i - 1][table.length - j - 1].setEmptyForSetUp();
					current -= 2;
					
					if (!solver.hasUniqueSolution(board)) {
						table[i][j].setValueForSetUp(value1);
						table[table.length - i - 1][table.length - j - 1].setValueForSetUp(value2);
						current += 2;
					}
				}
				
				if (current <= amount)
					return;
			}
		}
	}
}