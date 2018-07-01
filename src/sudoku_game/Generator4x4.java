package sudoku_game;

import java.util.Random;

/**
 * Generates four-by-four Sudoku puzzles
 * @author Alex Horejsi
 */
class Generator4x4 implements Generator {
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
		
		this.fillMajorDiagonal(rng);
		this.fillRemaining(0, 2);
		
		Cell[][] table = this.table;
		this.table = null;
		
		return table;
	}
	
	private void fillMajorDiagonal(Random rng) {
		this.fillBox(0, 0, rng);
		this.fillBox(2, 2, rng);
	}
	
	private void fillBox(int i, int j, Random rng) {
		int range = this.table.length;
		int end = i + 2;
		int bits = 0;
		int n;
		
		for (int row = i ; row < end ; row++) {
			for (int col = j ; col < end ; col++) {
				do {
					n = rng.nextInt(range);
				} while ((bits & (1 << n)) != 0);
				
				bits |= (1 << n);
				int digit = n + 1;
				this.table[row][col] = new ConcreteCell((char)(digit + '0'));
			}
		}
	}
	
	private boolean fillRemaining(int i, int j) {
		if (j == 4) {
			i++;
			j = 0;
		}
		
		switch (i) {
			case 0:
			case 1:
				if (j == 0)
					j = 2;
				break;
			default:
				if (j == 2) {
					i++;
					j = 0;
				}
				
				if (i == 4)
					return false;
		}
		
		for (char digit = '1' ; digit <= '4' ; digit++) {
			if (this.table[i][j] == null)
				this.table[i][j] = new ConcreteCell(digit);
			else
				this.table[i][j].setValueForSetUp(digit);
				
			if (this.fillRemaining(i, j + 1))
				return true;
			this.table[i][j].setEmptyForSetUp();
		}
		
		return false;
	}
}