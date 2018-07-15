package sudoku_game;

import java.util.HashSet;

/**
 * Checks if a Sudoku board
 * has a unique solution by
 * reducing it to an exact
 * cover problem
 * @author Alex Horejsi
 */
public class ExactCoverSolver implements Solver {
	public static void main(String[] args) {
		ExactCoverSolver.getInstance().hasUniqueSolution(new Board9x9());
	}
	
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
		boolean[][] matrix = this.createMatrix(board);
		
		for (int i = 0 ; i < matrix.length ; i++) {
			for (int j = 0 ; j < matrix[i].length ; j++)
				System.out.print(matrix[i][j] + " ");
			System.out.println();
		}
	}
	
	private boolean[][] createMatrix(Board board) {
		HashSet<Clue> clues = new HashSet<Clue>();
		Cell[][] table = board.getTable();
		int dimensions = board.getDimensions();
		int boxRows = board.rowSizeInBox();
		int boxCols = board.colSizeInBox();
		
		for (int i = 0 ; i < dimensions ; i++) {
			for (int j = 0 ; j < dimensions ; j++) {
				if (table[i][j].getValue() != '\u0000')
					clues.add(new Clue(table[i][j].getValue(), i, j));
			}
		}
		
		boolean[][] matrix = new boolean[dimensions * dimensions * dimensions][4 * dimensions * dimensions];
		char[] values = board.getLegalValues().getValues();
		
		for (int valuesIndex = 0 ; valuesIndex < dimensions ; valuesIndex++) {
			for (int row = 0 ; row < dimensions ; row++) {
				for (int col = 0 ; col < dimensions ; col++) {
					if (clues.contains(new Clue(values[valuesIndex], row, col))) {
						int rowIndex = col + (dimensions * row) + (dimensions * dimensions * valuesIndex);
						int blockIndex = ((col / boxCols) + ((row / boxRows) * boxRows));
						
						matrix[rowIndex][3 * dimensions * valuesIndex + row] = true;
						matrix[rowIndex][3 * dimensions * valuesIndex + dimensions + col] = true;
						matrix[rowIndex][3 * dimensions * valuesIndex + 2 * dimensions + blockIndex] = true;
						matrix[rowIndex][3 * dimensions * dimensions + (col + dimensions * row)] = true;
					}
				}
			}
		}
		
		return matrix;
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
		char val;
		int row;
		int col;
		
		private Clue(char val, int row, int col) {
			this.val = val;
			this.row = row;
			this.col = col;
		}
		
		@Override
		public int hashCode() {
			int hash = 31 * this.val;
			hash += 31 * this.row;
			hash += 31 * this.col;
			
			return hash;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Clue))
				return false;
			Clue clue = (Clue)obj;
			return this.val == clue.val && this.row == clue.row && this.col == clue.col;
		}
	}
}