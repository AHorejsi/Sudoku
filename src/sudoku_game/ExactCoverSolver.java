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
	 * Returns the single instance of
	 * {@code ExactCoverSolver}
	 * @return The single instance of
	 * {@code ExactCoverSolver}
	 */
	public static Solver getInstance() {
		return ExactCoverSolver.solver;
	}
	
	@Override
	public boolean hasUniqueSolution(Board board) {
		
	}
	
	private static class BinaryMatrix {
		private Node head;
		private int rows;
		private int cols;
		
		public BinaryMatrix(Board board) {
			this.rows = (int)Math.pow(board.getDimensions(), 3);
			this.cols = 4 * (int)Math.pow(board.getDimensions(), 2);
			this.head = this.setUpNodes(board, 0, 0);
		}
		
		private Node setUpNodes(Board board, int row, int col) {
			if (row == this.rows || col == this.cols)
				return null;
			else {
				Node node = new Node();
				node.right = this.setUpNodes(board, row, col + 1);
				node.down =  this.setUpNodes(board, row + 1, col);
				
				return node;
			}
		}
		
		private static class Node {
			private Boolean filled;
			private Node up;
			private Node down;
			private Node left;
			private Node right;
			
			public Node() {}
			
			public Node(Boolean filled) {
				this.filled = filled;
			}
		}
	}
}