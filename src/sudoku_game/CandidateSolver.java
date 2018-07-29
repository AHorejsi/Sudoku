package sudoku_game;

import java.util.HashSet;
import java.util.Set;

public class CandidateSolver implements Solver {
	public static void main(String[] args) {
		Puzzle pzzl = LocalFactory.getInstance().createPuzzle("12x12 hard");
		System.out.println(pzzl);
	}
	
	private static Solver solver = new CandidateSolver();
	
	private CandidateSolver() {}
	
	/**
	 * Returns the single instance
	 * of {@code CandidateSolver}
	 * @return The single instance
	 * of {@code CandidateSolver}
	 */
	public static Solver getInstance() {
		return CandidateSolver.solver;
	}
	
	@Override
	public boolean hasUniqueSolution(Board board) {
		char[] legalValues = board.getLegalValues().getValues();
		CandidateCell[][] candidateCells = this.createCandidateCells(board, legalValues);
		this.eliminateCandidates(board, candidateCells);
		return this.solve(board, board.getTable(), candidateCells, 0, 0, 0) == 1;
	}
	
	private CandidateCell[][] createCandidateCells(Board board, char[] values) {
		Cell[][] table = board.getTable();
		int length = table.length;
		int rowSizeInBox = board.rowSizeInBox();
		int colSizeInBox = board.colSizeInBox();
		CandidateCell[][] candidateCells = new CandidateCell[length][length];
		
		for (int row = 0 ; row < length ; row++) {
			for (int col = 0 ; col < length ; col++) {
				if (table[row][col].getValue() == '\u0000') {
					Set<Character> candidates = new HashSet<Character>();
					
					for (char value : values)
						candidates.add(value);
					this.removeInvalidCandidates(row, col, candidates, table, rowSizeInBox, colSizeInBox);
					
					candidateCells[row][col] = new CandidateCell(candidates);
				}
			}
		}
		
		return candidateCells;
	}
	
	private void removeInvalidCandidates(int row, int col, Set<Character> candidates, Cell[][] table, 
										 int rowSizeInBox, int colSizeInBox) {
		this.checkRow(row, col, table, candidates);
		this.checkColumn(row, col, table, candidates);
		this.checkBox(row - row % rowSizeInBox, col - col % colSizeInBox, row, col, 
					  rowSizeInBox, colSizeInBox, table, candidates);
	}
	
	private void checkRow(int row, int col, Cell[][] table, Set<Character> candidates) {
		for (int j = 0 ; j < table.length ; j++) {
			if (j != col) {
				char value = table[row][j].getValue();
				
				if (value != '\u0000')
					candidates.remove(value);
			}
		}
	}
	
	private void checkColumn(int row, int col, Cell[][] table, Set<Character> candidates) {
		for (int i = 0 ; i < table.length ; i++) {
			if (i != row) {
				char value = table[i][col].getValue();
				
				if (value != '\u0000')
					candidates.remove(value);
			}
		}
	}
	
	private void checkBox(int row, int col, int targetRow, int targetCol, int rowSize, int colSize, 
						  Cell[][] table, Set<Character> candidates) {
		for (int i = 0 ; i < rowSize ; i++) {
			for (int j = 0 ; j < colSize ; j++) {
				int rowIndex = row + i;
				int colIndex = col + j;
				
				if (rowIndex != targetRow || colIndex != targetCol) {
					char value = table[rowIndex][colIndex].getValue();
					
					if (value != '\u0000')
						candidates.remove(value);
				}
			}
		}
	}
	
	private void eliminateCandidates(Board board, CandidateCell[][] candidates) {
		Cell[][] table = board.getTable();
	}
	
	private int solve(Board board, Cell[][] table, CandidateCell[][] candidateCells, int row, int col, int count) {
		if (count > 1)
			return count;
		
		if (col == table.length) {
			row++;
			col = 0;
		}
		
		if (row == table.length)
			count++;
		else if (candidateCells[row][col] == null)
			count = this.solve(board, table, candidateCells, row + 1, 0, count);
		else {
			for (char value : candidateCells[row][col].getCandidates()) {
				if (count > 1)
					return count;
				if (this.safe(board, table, row, col, value)) {
					table[row][col].setValueForSetUp(value);
					
					count = this.solve(board, table, candidateCells, count, row + 1, 0);
					
					table[row][col].setEmptyForSetUp();
				}
			}
		}
		
		return count;
	}
	
	private boolean safe(Board board, Cell[][] table, int i, int j, char digit) {
		int endRow = board.rowSizeInBox();
		int endCol = board.colSizeInBox();
		return this.safeRow(table, i, digit, table.length) && 
			   this.safeCol(table, j, digit, table.length) && 
			   this.safeBox(table, i - i % endRow, j - j % endCol, digit, endRow, endCol);
	}
	
	private boolean safeRow(Cell[][] table, int i, char digit, int end) {
		for (int j = 0 ; j < end ; j++) {
			if (table[i][j] != null) {
				if (table[i][j].getValue() == digit)
					return false;
			}
		}
		
		return true;
	}
	
	private boolean safeCol(Cell[][] table, int j, char digit, int end) {
		for (int i = 0 ; i < end ; i++) {
			if (table[i][j] != null) {
				if (table[i][j].getValue() == digit)
					return false;
			}
		}
		
		return true;
	}
	
	private boolean safeBox(Cell[][] table, int i, int j, char digit, int endRow, int endCol) {
		for (int row = 0 ; row < endRow ; row++) {
			for (int col = 0 ; col < endCol ; col++) {
				if (table[row + i][col + j] != null) {
					if (table[row + i][col + j].getValue() == digit)
						return false;
				}
			}
		}
		
		return true;
	}
	
	private static class CandidateCell {
		Set<Character> candidates = new HashSet<Character>();
		
		CandidateCell(Set<Character> candidates) {
			this.candidates = candidates;
		}
		
		public Set<Character> getCandidates() {
			return this.candidates;
		}
	}
}