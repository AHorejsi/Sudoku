package sudoku_game;

import java.util.Random;

public class RandomizedDifficultyAdjustor implements DifficultyAdjustor {
	private static DifficultyAdjustor adjustor = new RandomizedDifficultyAdjustor();
	
	private RandomizedDifficultyAdjustor() {}
	
	public static DifficultyAdjustor getInstance() {
		return RandomizedDifficultyAdjustor.adjustor;
	}

	@Override
	public void adjust(Board board, Random rng, int lowerRangeOnGivens, int upperRangeOnGivens,int lowerBoundOnGivensPerUnit) {
		int amount = this.determineAmountOfGivens(rng, lowerRangeOnGivens, upperRangeOnGivens, 
				board.getDimensions() * board.getDimensions());
		int lowerBound = this.determineLowerBound(lowerBoundOnGivensPerUnit, board.getDimensions());
		this.performAdjustment(board, rng, amount, lowerBound);
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
		Solver solver = SimpleSolver.getInstance();
		
		while (current > amount) {
			int row;
			int col;
			
			do {
				row = rng.nextInt(length);
				col = rng.nextInt(length);
			} while (table[row][col].getValue() == '\u0000');
			
			if (this.canBeDug(table, row, col, lowerBound)) {
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
	
	private boolean canBeDug(Cell[][] table, int row, int col, int lowerBound) {
		int end = (int)Math.sqrt(table.length);
		return this.checkRow(table, row, lowerBound) && this.checkCol(table, col, lowerBound) && 
				this.checkBox(table, row - row % end, col - col % end, lowerBound, end);
	}
	
	private boolean checkRow(Cell[][] table, int row, int lowerBound) {
		int count = 0;
		
		for (int col = 0 ; col < table.length ; col++) {
			if (table[row][col].getValue() != '\u0000')
				count++;
		}
		
		return count >= lowerBound;
	}
	
	private boolean checkCol(Cell[][] table, int col, int lowerBound) {
		int count = 0;
		
		for (int row = 0 ; row < table.length ; row++) {
			if (table[row][col].getValue() != '\u0000')
				count++;
		}
		
		return count >= lowerBound;
	}
	
	private boolean checkBox(Cell[][] table, int i, int j, int lowerBound, int end) {
		int endRow = i + end;
		int endCol = j + end;
		int count = 0;
		
		for (int row = i ; row < endRow ; row++) {
			for (int col = j ; col < endCol ; col++) {
				if (table[row][col].getValue() != '\u0000')
					count++;
			}
		}
		
		return count >= lowerBound;
	}
}