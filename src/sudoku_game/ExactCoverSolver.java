package sudoku_game;

/**
 * Checks if a Sudoku board
 * has a unique solution by
 * modeling it as an exact
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
		Node head = this.createMatrix(board);
		return this.countSolutions(head) == 1;
	}
	
	private Node createMatrix(Board board) {
		int dimensions = board.getDimensions();
		Integer rows = dimensions * dimensions * dimensions;
		Integer cols = 4 * dimensions * dimensions;
		
		Node node = new ColumnNode(false, '\u0000');
		node.right = this.createMatrix(board.table, 0, 0, rows, cols);
		node.right.left = node;
		
		return node;
	}
	
	private Node createMatrix(Cell[][] table, int row, int col, Integer endRow, Integer endCol) {
		if (row == endRow || col == endCol)
			return null;
		else {
			Node node;
			
			if (row == 0) {
				
			}
			else {
				
			}
				
			
			node.right = this.createMatrix(table, row, col + 1, endRow, endCol);
			node.down = this.createMatrix(table, row + 1, col, endRow, endCol);
			
			if (node.right != null)
				node.right.left = node;
			if (node.down != null)
				node.down.up = node;
			
			
			return node;
		}
	}
	
	private static class Node {
		private Boolean filled;
		private Node up;
		private Node down;
		private Node left;
		private Node right;
		
		private Node(boolean filled) {
			this.filled = filled;
		}
	}
	
	private static class ColumnNode extends Node {
		private char name;
		
		private ColumnNode(boolean filled, char name) {
			super(filled);
			this.name = name;
		}
	}
}