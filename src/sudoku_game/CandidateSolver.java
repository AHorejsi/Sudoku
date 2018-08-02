package sudoku_game;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CandidateSolver implements Solver {
	public static void main(String[] args) {
		Puzzle puzzle = LocalFactory.getInstance().createPuzzle("8x8 insane");
		System.out.println(puzzle);
	}
	
	private static Solver solver = new CandidateSolver();
	private SafeCellChecker checker = SimpleSafeCellChecker.getInstance();
	
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
		CandidateSet[][] candidateCells = this.createCandidateCells(board, board.getLegalValues().getValues());
		int count = this.solve(board, board.getTable(), candidateCells, 0, 0, 0);
		return count == 1;
	}
	
	private CandidateSet[][] createCandidateCells(Board board, char[] values) {
		Cell[][] table = board.getTable();
		int length = table.length;
		int rowSizeInBox = board.rowSizeInBox();
		int colSizeInBox = board.colSizeInBox();
		CandidateSet[][] candidateCells = new CandidateSet[length][length];
		
		for (int row = 0 ; row < length ; row++) {
			for (int col = 0 ; col < length ; col++) {
				if (table[row][col].getValue() == '\u0000') {
					Set<Character> candidates = new HashSet<Character>();
					
					for (char value : values)
						candidates.add(value);
					
					this.removeInvalidCandidates(row, col, candidates, table, rowSizeInBox, colSizeInBox);
					
					candidateCells[row][col] = new CandidateSet(candidates);
				}
			}
		}
		
		return candidateCells;
	}
	
	private void removeInvalidCandidates(int row, int col, Set<Character> candidates, Cell[][] table, 
										 int rowSizeInBox, int colSizeInBox) {
		this.checkRow(row, col, table, candidates);
		this.checkColumn(row, col, table, candidates);
		this.checkBox(row - row % rowSizeInBox, col - col % colSizeInBox,
					  row, col,
					  rowSizeInBox, colSizeInBox,
					  table, candidates);
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
	
	private void checkBox(int row, int col,
						  int targetRow, int targetCol,
						  int rowSize, int colSize,
						  Cell[][] table, Set<Character> candidates) {
		for (int i = 0 ; i < rowSize ; i++) {
			for (int j = 0 ; j < colSize ; j++) {
				int rowIndex = row + i;
				int colIndex = col + j;
				
				if (rowIndex != targetRow || colIndex != targetCol) {
					char value = table[rowIndex][colIndex].getValue();
					candidates.remove(value);
				}
			}
		}
	}
	
	private int solve(Board board, Cell[][] table, CandidateSet[][] candidateCells, int row, int col, int count) {
		if (count > 1)
			return count;
		
		if (row == table.length)
			count++;
		else {
			while (candidateCells[row][col] == null) {
				if (col == table.length - 1) {
					row++;
					col = 0;
				}
				else
					col++;
				
				if (row == 8) {
					count++;
					return count;
				}
			}
			
			for (char value : candidateCells[row][col]) {
				if (this.checker.safe(table, value, row, col, board.rowSizeInBox(), board.colSizeInBox())) {
					table[row][col].setValueForSetUp(value);
					
					if (col == table.length - 1)
						count = this.solve(board, table, candidateCells, row + 1, 0, count);
					else
						count = this.solve(board, table, candidateCells, row, col + 1, count);
					
					if (count > 1)
						return count;
					
					table[row][col].setEmptyForSetUp();
				}
			}
		}
		
		return count;
	}
	
	private static class CandidateSet implements Iterable<Character> {
		private Set<Character> candidates = new HashSet<Character>();
		
		CandidateSet(Set<Character> candidates) {
			this.candidates = candidates;
		}
		
		@Override
		public Iterator<Character> iterator() {
			return this.candidates.iterator();
		}
	}
}