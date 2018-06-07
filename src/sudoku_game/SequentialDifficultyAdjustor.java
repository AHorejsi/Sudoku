package sudoku_game;

public class SequentialDifficultyAdjustor implements DifficultyAdjustor {
	private static SequentialDifficultyAdjustor adjustor = new SequentialDifficultyAdjustor();
	
	private SequentialDifficultyAdjustor() {}
	
	public static SequentialDifficultyAdjustor getInstance() {
		return SequentialDifficultyAdjustor.adjustor;
	}
}