package sudoku_game;

import java.util.Random;

public class RandomizedDifficultyAdjustor implements DifficultyAdjustor {
	private static RandomizedDifficultyAdjustor adjustor = new RandomizedDifficultyAdjustor();
	
	private RandomizedDifficultyAdjustor() {}
	
	public static DifficultyAdjustor getInstance() {
		return RandomizedDifficultyAdjustor.adjustor;
	}

	
}