package sudoku_game;

import java.util.ArrayList;

/**
 * Checks if a Sudoku board
 * has a unique solution by
 * reducing it to an exact
 * cover problem
 * @author Alex Horejsi
 */
class ExactCoverSolver implements Solver {
	private static Solver solver = new ExactCoverSolver();
	
	private ExactCoverSolver() {}
	
	/**
	 * Returns the single instance
	 * of {@code ExactCoverSolver}
	 * @return The single instance
	 * of {@code ExactCoverSolver}
	 */
	public static Solver getInstance() {
		return ExactCoverSolver.solver;
	}
	
	@Override
	public boolean hasUniqueSolution(Board board) {
		int dimensions = board.getDimensions();
		
		byte[][] matrix = this.createMatrix(board);
	}
	
	private byte[][] createMatrix(Board board) {
		Cell[][] table = board.getTable();
		ArrayList<Clue> cluesList = new ArrayList<Clue>();
		int dimensions = board.getDimensions();
		
		for (int i = 0 ; i < dimensions ; i++) {
			for (int j = 0 ; j < dimensions ; j++) {
				if (table[i][j].getValue() != '\u0000')
					cluesList.add(new Clue(table[i][j], i, j));
			}
		}
		
		
	}
	
	private static class Node {
		ColumnNode head;
		Node up;
		Node down;
		Node left;
		Node right;
	}
	
	private static class ColumnNode extends Node {
		int size;
		ColumnID info;
	}
	
	private static class ColumnID {
		int constraint = -1;
		int number = -1;
		int position = -1;
	}
	
	private static class Clue {
		Cell val;
		int row;
		int col;
		
		private Clue(Cell val, int row, int col) {
			this.val = val;
			this.row = row;
			this.col = col;
		}
	}
}