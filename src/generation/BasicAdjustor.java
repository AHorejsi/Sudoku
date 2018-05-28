package generation;

import java.util.Random;

public class BasicAdjustor implements DifficultyAdjustor {
	private static BasicAdjustor basic = new BasicAdjustor();
	
	private BasicAdjustor() {}
	
	public static BasicAdjustor getInstance() {
		return BasicAdjustor.basic;
	}
	
	@Override
	public Cell[][] adjust(char[][] table, Random rng) {
		int givens = this.determineAmountOfGivens(rng, table.length);
		int current = table.length * table.length;
					
	}
	
	private int determineAmountOfGivens(Random rng, int n) {
		float percent = (rng.nextInt((68 - 58) + 1) + 58) / 100.0f;
		return Math.round(percent * n);
	}
}