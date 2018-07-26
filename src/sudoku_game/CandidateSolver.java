package sudoku_game;

/**
 * Computes a solution to the given puzzle by
 * keeping track of candidates for empty cells
 * @author Alex Horejsi
 */
public class CandidateSolver implements Solver {
	private static Solver solver = new CandidateSolver();
	
	private CandidateSolver() {}
	
	/**
	 * Returns the single instance
	 * of {@code CandidateSolver}
	 * @return The single instance
	 * of {@code CandidateSolver}
	 */
	public static Solver getInstance() {
		return CandidateSolver.solver;
	}
	
	@Override
	public boolean hasUniqueSolution(Board board) {
		
	}
}