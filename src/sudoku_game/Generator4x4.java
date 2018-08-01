package sudoku_game;

import java.util.Random;

/**
 * Generates four-by-four Sudoku puzzles
 * @author Alex Horejsi
 */
public class Generator4x4 implements Generator {
	private static Generator gen = new Generator4x4();
	private Cell[][] table;
	
	private Generator4x4() {}
	
	/**
	 * Returns the single instance
	 * of {@code Generator4x4}
	 * @return The single instance
	 * of {@code Generator4x4}
	 */
	public static Generator getInstance() {
		return Generator4x4.gen;
	}
	
	@Override
	public Cell[][] generate(Random rng) {
		this.table = new Cell[4][4];
		
		this.fillRemaining(0, 0, rng, SimpleSafeCellChecker.getInstance());
		
		Cell[][] table = this.table;
		this.table = null;
		
		return table;
	}
	
	private boolean fillRemaining(int i, int j, Random rng, SafeCellChecker checker) {
		if (j == 4) {
			i++;
			j = 0;
		}
		
		if (i == 4)
			return true;
		
		char[] shuffled = this.shuffle(rng);
		
		for (int entry = 0 ; entry < 4 ; entry++) {
			char digit = shuffled[entry];
			
			if (checker.safe(this.table, digit, i, j, 2, 2)) {
				if (this.table[i][j] == null)
					this.table[i][j] = new ConcreteCell(digit);
				else
					this.table[i][j].setValueForSetUp(digit);
					
				if (this.fillRemaining(i, j + 1, rng, checker))
					return true;
				
				this.table[i][j].setEmptyForSetUp();
			}
		}
		
		return false;
	}
	
	private char[] shuffle(Random rng) {
		char[] values = LegalValues4x4.getInstance().getValues();
		
		for (int i = values.length - 1 ; i > 0 ; i--) {
			int pos = rng.nextInt(i);
			char temp = values[pos];
			values[pos] = values[i];
			values[i] = temp;
		}
		
		return values;
	}
}