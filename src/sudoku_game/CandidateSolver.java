package sudoku_game;

public class CandidateSolver implements Solver {
	private static Solver solver = new CandidateSolver();
	
	private CandidateSolver() {}
	
	public static Solver getInstance() {
		return CandidateSolver.solver;
	}
}