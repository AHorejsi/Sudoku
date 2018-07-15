package sudoku_game;

import java.util.Random;

/**
 * Moves through a given Sudoku puzzle
 * row-by-row. As the Sudoku puzzle
 * is being traversed, it is determined
 * which values should be removed
 * @author Alex Horejsi
 */
public class SequentialDifficultyAdjustor implements DifficultyAdjustor {
	private static DifficultyAdjustor adjustor = new SequentialDifficultyAdjustor();
	
	private SequentialDifficultyAdjustor() {}
	
	/**
	 * Returns the single instance
	 * of {@code SequentialDifficultyAdjustor}
	 * @return The single instance
	 * of {@code SequentialDifficultyAdjustor}
	 */
	public static DifficultyAdjustor getInstance() {
		return SequentialDifficultyAdjustor.adjustor;
	}

	@Override
	public void adjust(Board board, Random rng, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit) {
		int amount = this.determineAmountOfGivens(rng, lowerRangeOnGivens, upperRangeOnGivens, 
				board.getDimensions() * board.getDimensions());
		int lowerBound = this.determineLowerBound(lowerBoundOnGivensPerUnit, board.getDimensions());
		this.performAdjustment(board, amount, lowerBound);
		DifficultyAdjustor.setEditableCells(board.getTable());
	}
	
	private int determineAmountOfGivens(Random rng, int lower, int upper, int total) {
		int percent = rng.nextInt((upper - lower) + 1) + lower;
		return Math.round(total * (percent / 100.0f));
	}
	
	private int determineLowerBound(int lowerBoundOnGivensPerUnit, int dimensions) {
		return Math.round(dimensions * (lowerBoundOnGivensPerUnit / 100.0f));
	}
	
	private void performAdjustment(Board board, int amount, int lowerBound) {
		Cell[][] table = board.getTable();
		int current = table.length * table.length;
		Solver solver = BacktrackingSolver.getInstance();
		LowerBoundChecker checker = SimpleLowerBoundChecker.getInstance();
		
		for (int i = 0 ; i < table.length ; i++) {
			for (int j = 0 ; j < table.length ; j++) {
				if (checker.check(board, i, j, lowerBound)) {
					char value = table[i][j].getValue();
					table[i][j].setEmptyForSetUp();
					current--;
					
					if (!solver.hasUniqueSolution(board)) {
						table[i][j].setValueForSetUp(value);
						current++;
					}
				}
				
				if (current == amount)
					return;
			}
		}
	}
}