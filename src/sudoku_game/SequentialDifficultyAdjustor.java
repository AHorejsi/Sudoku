package sudoku_game;

import java.util.Random;

class SequentialDifficultyAdjustor implements DifficultyAdjustor {
	private static DifficultyAdjustor adjustor = new SequentialDifficultyAdjustor();
	
	private SequentialDifficultyAdjustor() {}
	
	public static DifficultyAdjustor getInstance() {
		return SequentialDifficultyAdjustor.adjustor;
	}

	@Override
	public void adjust(Board board, Random rng, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit) {
		int amount = this.determineAmountOfGivens(rng, lowerRangeOnGivens, upperRangeOnGivens, 
				board.getDimensions() * board.getDimensions());
		int lowerBound = this.determineLowerBound(lowerBoundOnGivensPerUnit, board.getDimensions());
		this.performAdjustment(board, amount, lowerBound);
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
		Solver solver = SimpleSolver.getInstance();
		LowerBoundCheckersRunner runner = SimpleLowerBoundCheckersRunner.getInstance();
		
		for (int i = 0 ; i < table.length ; i++) {
			for (int j = 0 ; j < table.length ; j++) {
				if (runner.check(board, i, j, lowerBound)) {
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