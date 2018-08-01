package sudoku_game;

import java.util.Random;

/**
 * Generates six-by-six Sudoku puzzles
 * @author Alex Horejsi
 */
public class Generator6x6 implements Generator {	
	private static Generator gen = new Generator6x6();
	private Cell[][] table;
	
	private Generator6x6() {}
	
	/**
	 * Returns the single instance
	 * of {@code Generator6x6}
	 * @return The single instance
	 * of {@code Generator6x6}
	 */
	public static Generator getInstance() {
		return Generator6x6.gen;
	}
	
	@Override
	public Cell[][] generate(Random rng) {
		this.table = new Cell[6][6];
		
		this.fillRemaining(0, 0, rng, SimpleSafeCellChecker.getInstance());
		
		Cell[][] table = this.table;
		this.table = null;
		
		return table;
	}
	
	private boolean fillRemaining(int i, int j, Random rng, SafeCellChecker checker) {
		if (j == 6) {
			i++;
			j = 0;
		}
		
		if (i == 6)
			return true;
		
		char[] shuffled = this.shuffle(rng);
		
		for (int entry = 0 ; entry < 6 ; entry++) {
			char digit = shuffled[entry];
			
			if (checker.safe(this.table, digit, i, j, 2, 3)) {
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
		char[] values = LegalValues6x6.getInstance().getValues();
		
		for (int i = values.length - 1 ; i > 0 ; i--) {
			int pos = rng.nextInt(i);
			char temp = values[pos];
			values[pos] = values[i];
			values[i] = temp;
		}
		
		return values;
	}
}