package sudoku_game;

import java.util.Random;

public class S_PathDifficultyAdjustor implements DifficultyAdjustor {
	private static DifficultyAdjustor adjustor = new S_PathDifficultyAdjustor();
	
	private S_PathDifficultyAdjustor() {}
	
	public static DifficultyAdjustor getInstance() {
		return S_PathDifficultyAdjustor.adjustor;
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
		int end = (table.length & 1) == 0 ? table.length >>> 1 : (table.length >>> 1) + 1;
		
		
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