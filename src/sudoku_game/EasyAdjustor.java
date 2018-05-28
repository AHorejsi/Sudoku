package sudoku_game;

import java.util.Random;

public class EasyAdjustor implements DifficultyAdjustor {
	private static EasyAdjustor easy = new EasyAdjustor();
	
	private EasyAdjustor() {}
	
	public static EasyAdjustor getInstance() {
		return EasyAdjustor.easy;
	}
}