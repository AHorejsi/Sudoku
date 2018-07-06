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
		Node head = this.constructMatrix(board.getDimensions());
	}
	
	private Node constructMatrix(int dimensions) {
		
	}
	
	private static class Node {
		
	}
	
	private static class ColumnNode extends Node {
		
	}
}