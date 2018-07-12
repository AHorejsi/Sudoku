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
	private Cell[][] table;
	private int rows;
	private int cols;
	
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
		
		this.table = board.getTable();
		this.rows = dimensions * dimensions * dimensions;
		this.cols = 4 * dimensions * dimensions;
	}
	
	private static class Node {
		private ColumnNode head;
		private Node up;
		private Node down;
		private Node left;
		private Node right;
	}
	
	private static class ColumnNode extends Node {
		private int size;
		private ColumnID info;
	}
	
	private static class ColumnID {
		private int constraint = -1;
		private int number = -1;
		private int position = -1;
	}
	
	private static class Clue {
		private char val;
		private int row;
		private int col;
	}
}