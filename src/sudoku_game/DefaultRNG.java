package sudoku_game;

import java.util.Random;

public class DefaultRNG {
	private static Random defaultGenerator = new Random();
	
	public static Random getDefaultGenerator() {
		return DefaultRNG.defaultGenerator;
	}
}