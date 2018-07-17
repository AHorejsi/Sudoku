package sudoku_game;

import java.util.Random;
//TODO This needs work
/**
 * Moves through the Sudoku puzzle in an
 * S-like pattern. As the Sudoku puzzle
 * is being traversed, it is determined
 * which values should be removed
 * @author Alex Horejsi
 */
public class S_PathDifficultyAdjustor implements DifficultyAdjustor {
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
		this.performAdjustment(board, amount, lowerBound, rng);
		DifficultyAdjustor.setEditableCells(board.getTable());
	}
	
	private int determineAmountOfGivens(Random rng, int lower, int upper, int total) {
		int percent = rng.nextInt((upper - lower) + 1) + lower;
		return Math.round(total * (percent / 100.0f));
	}
	
	private int determineLowerBound(int lowerBoundOnGivensPerUnit, int dimensions) {
		return Math.round(dimensions * (lowerBoundOnGivensPerUnit / 100.0f));
	}
	
	private void performAdjustment(Board board, int amount, int lowerBound, Random rng) {
		Cell[][] table = board.getTable();
		int length = table.length;
		int current = length * length;
		Solver solver = ExactCoverSolver.getInstance();
		LowerBoundChecker checker = SimpleLowerBoundChecker.getInstance();
		
		for (int i = 0 ; i < length ; i++) {
			for (int j = 0 ; j < length ; j++) {
				if (checker.check(board, i, j, lowerBound)) {
					char value1 = table[i][j].getValue();
					char value2 = table[length - i - 1][length - j - 1].getValue();
					table[i][j].setEmptyForSetUp();
					table[length - i - 1][length - j - 1].setEmptyForSetUp();
					current -= 2;
					
					if (!solver.hasUniqueSolution(board)) {
						table[i][j].setValueForSetUp(value1);
						table[length - i - 1][length - j - 1].setValueForSetUp(value2);
						current += 2;
					}
				}
				
				if (current <= amount)
					return;
			}
		}
	}
}