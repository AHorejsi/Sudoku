package sudoku_game;

public class JumpCellDifficultyAdjustor implements DifficultyAdjustor {
	private static JumpCellDifficultyAdjustor adjustor = new JumpCellDifficultyAdjustor();
	
	private JumpCellDifficultyAdjustor() {}
	
	public static JumpCellDifficultyAdjustor getInstance() {
		return JumpCellDifficultyAdjustor.adjustor;
	}
}