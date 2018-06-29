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
	
	private static class ColumnNode extends Node {
		private String name;
		
		public ColumnNode() {}
		
		public ColumnNode(String name) {
			this.name = name;
		}
	}
}