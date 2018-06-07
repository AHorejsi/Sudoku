package sudoku_game;

public class S_PathDifficultyAdjustor implements DifficultyAdjustor {
	private static S_PathDifficultyAdjustor adjustor = new S_PathDifficultyAdjustor();
	
	private S_PathDifficultyAdjustor() {}
	
	public static S_PathDifficultyAdjustor getInstance() {
		return S_PathDifficultyAdjustor.adjustor;
	}
}