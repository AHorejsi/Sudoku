package sudoku_game;

public class HardAdjustor implements DifficultyAdjustor {
	private static HardAdjustor hard = new HardAdjustor();
	
	private HardAdjustor() {}
	
	public static HardAdjustor getInstance() {
		return HardAdjustor.hard;
	}
}