package sudoku_game;

public class ExactCoverSolver implements Solver {
	private static Solver solver = new ExactCoverSolver();
	
	private ExactCoverSolver() {}
	
	public static Solver getInstance() {
		return ExactCoverSolver.solver;
	}
}