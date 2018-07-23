package sudoku_game;

import java.util.Random;

public class JumpCellDifficultyAdjustor implements DifficultyAdjustor {
	private static DifficultyAdjustor adjustor = new JumpCellDifficultyAdjustor();
	
	private JumpCellDifficultyAdjustor() {}
	
	public static DifficultyAdjustor getInstance() {
		return JumpCellDifficultyAdjustor.adjustor;
	}

	@Override
	public void adjust(Board board, Random rng, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit) {
		int amount = this.determineAmountOfGivens(rng, lowerRangeOnGivens, upperRangeOnGivens, 
				board.getDimensions() * board.getDimensions());
		int lowerBound = this.determineLowerBound(lowerBoundOnGivensPerUnit, board.getDimensions());
		this.performAdjustment(board, rng, amount, lowerBound);
		DifficultyAdjustor.setEditableCells(board.getTable());
	}
	
	private int determineAmountOfGivens(Random rng, int lower, int upper, int total) {
		int percent = rng.nextInt((upper - lower) + 1) + lower;
		return Math.round(total * (percent / 100.0f));
	}
	
	private int determineLowerBound(int lowerBoundOnGivensPerUnit, int dimensions) {
		return Math.round(dimensions * (lowerBoundOnGivensPerUnit / 100.0f));
	}
	
	private void performAdjustment(Board board, Random rng, int amount, int lowerBound) {
		Cell[][] table = board.getTable();
		int length = table.length;
		int current = length * length;
		Solver solver = this.getSolver(board.getDimensions());
		LowerBoundChecker checker = SimpleLowerBoundChecker.getInstance();
		
		outerloop:
		for (int i = 0 ; i < length ; i++) {
			for (int j = ((i & 1) == 0) ? 1 : 0 ; j < length ; j += 2) {
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
					break outerloop;
			}
		}
		
		if (current > amount) {
			for (int i = 0 ; i < length ; i++) {
				for (int j = ((i & 1) == 0) ? 0 : 1 ; j < length ; j += 2) {
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
	
	private Solver getSolver(int dimensions) {
		double sqrt = Math.sqrt(dimensions);
		int roundedSqrt = (int)sqrt;
		
		System.out.println(sqrt + " " + roundedSqrt);
		
		if (sqrt == roundedSqrt)
			return ExactCoverSolver.getInstance();
		else
			return CandidateSolver.getInstance();
	}
}