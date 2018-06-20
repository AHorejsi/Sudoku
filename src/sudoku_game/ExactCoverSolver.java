package sudoku_game;

/**
 * This class calculates whether or not a Sudoku
 * puzzle has a unique solution by using an exact
 * cover algorithm
 * @author Alex Horejsi
 */
class ExactCoverSolver implements Solver {
	private static Solver solver = new ExactCoverSolver();
	
	private ExactCoverSolver() {}
	
	/**
	 * Returns the single instance of {@code ExactCoverSolver}
	 * @return The single instance of {@code ExactCoverSolver}
	 */
	public static Solver getInstance() {
		return ExactCoverSolver.solver;
	}
}