package sudoku_game;

import java.util.HashSet;
import java.util.Set;

public class CrookSolver implements Solver {
	private static Solver solver = new CrookSolver();
	
	private CrookSolver() {}
	
	public static Solver getInstance() {
		return CrookSolver.solver;
	}
	
	@Override
	public boolean hasUniqueSolution(Board board) {
		CandidateCell[][] candidateCells = this.createCandidateCells(board);
		
	}
	
	private CandidateCell[][] createCandidateCells(Board board) {
		Cell[][] table = board.getTable();
		CandidateCell[][] candidates = new CandidateCell[table.length][table.length];
		char[] values = board.getLegalValues().getValues();
		int boxRow = board.rowSizeInBox();
		int boxCol = board.colSizeInBox();
		
		for (int i = 0 ; i < table.length ; i++) {
			for (int j = 0 ; j < table.length ; j++) {
				if (table[i][j].getValue() == '\u0000') {
					Set<Character> open = this.findCandidates(i, j, values, table, boxRow, boxCol);
					candidates[i][j] = new CandidateCell(table[i][j], open);
				}
				else
					candidates[i][j] = new CandidateCell(table[i][j], null);
			}
		}
		
		return candidates;
	}
	
	private Set<Character> findCandidates(int i, int j, char[] values, Cell[][] table, int boxRow, int boxCol) {
		Set<Character> open = new HashSet<Character>();
		
		for (char elem : values)
			open.add(elem);
		
		this.checkRows(i, open, table);
		this.checkColumns(j, open, table);
		this.checkBox(i - i % boxRow, j - j % boxCol, open, table, boxRow, boxCol);
		
		return open;
	}
	
	private void checkRows(int i, Set<Character> open, Cell[][] table) {
		for (int j = 0 ; j < table.length ; j++) {
			if (table[i][j].getValue() != '\u0000')
				open.remove(table[i][j].getValue());
		}
	}
	
	private void checkColumns(int j, Set<Character> open, Cell[][] table) {
		for (int i = 0 ; i < table.length ; i++) {
			if (table[i][j].getValue() != '\u0000')
				open.remove(table[i][j].getValue());
		}
	}
	
	private void checkBox(int i, int j, Set<Character> open, Cell[][] table, int boxRow, int boxCol) {
		for (int row = 0 ; row < boxRow ; row++) {
			for (int col = 0 ; col < boxCol ; col++) {
				if (table[row + i][col + j].getValue() != '\u0000')
					open.remove(table[row + i][col + j].getValue());
			}
		}
	}
	
	private static class CandidateCell {
		private Cell cell;
		private Set<Character> candidates;
		
		public CandidateCell(Cell cell, Set<Character> open) {
			this.cell = cell;
			this.candidates = open;
		}
		
		public void addCandidate(Character value) {
			this.candidates.add(value);
		}
		
		public boolean containsCandidate(Character value) {
			return this.candidates.contains(value);
		}
		
		public void removeCandidate(Character value) {
			this.candidates.remove(value);
		}
	}
}