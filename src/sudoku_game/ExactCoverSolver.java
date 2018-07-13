package sudoku_game;

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
		
		Cell[][] table = board.getTable();
		int rows = dimensions * dimensions * dimensions;
		int cols = 4 * dimensions * dimensions;
		
		
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
	}
}