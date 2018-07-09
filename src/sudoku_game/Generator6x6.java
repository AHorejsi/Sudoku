package sudoku_game;

import java.util.Random;

/**
 * Generates six-by-six Sudoku puzzle
 * @author Alex Horejsi
 */
class Generator6x6 implements Generator {
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
		
		this.fillRemaining(0, 0, rng);
		
		Cell[][] table = this.table;
		this.table = null;
		
		return table;
	}
	
	private boolean fillRemaining(int i, int j, Random rng) {
		if (j == 6) {
			i++;
			j = 0;
		}
		
		if (i == 6)
			return true;
		
		char[] shuffled = this.shuffle(rng);
		
		for (int entry = 0 ; entry < 6 ; entry++) {
			char digit = shuffled[entry];
			
			if (this.safe(i, j, digit)) {
				if (this.table[i][j] == null)
					this.table[i][j] = new ConcreteCell(digit);
				else
					this.table[i][j].setValueForSetUp(digit);
					
				if (this.fillRemaining(i, j + 1, rng))
					return true;
				this.table[i][j].setEmptyForSetUp();
			}
		}
		
		return false;
	}
	
	private boolean safe(int i, int j, char digit) {
		return this.safeRow(i, digit) &&
			   this.safeCol(j, digit) &&
			   this.safeBox(i - i % 2, j - j % 3, digit);
	}
	
	private boolean safeRow(int i, char digit) {
		for (int j = 0 ; j < 6 ; j++) {
			if (this.table[i][j] != null) {
				if (this.table[i][j].getValue() == digit)
					return false;
			}
		}
		
		return true;
	}
	
	private boolean safeCol(int j, char digit) {
		for (int i = 0 ; i < 6 ; i++) {
			if (this.table[i][j] != null) {
				if (this.table[i][j].getValue() == digit)
					return false;
			}
		}
		
		return true;
	}
	
	private boolean safeBox(int i, int j, char digit) {
		for (int row = 0 ; row < 2 ; row++) {
			for (int col = 0 ; col < 3 ; col++) {
				if (this.table[row + i][col + j] != null) {
					if (this.table[row + i][col + j].getValue() == digit)
						return false;
				}
			}
		}
		
		return true;
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