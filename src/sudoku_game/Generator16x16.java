package sudoku_game;

import java.util.Random;

/**
 * Generates sixteen-by-sixteen Sudoku puzzles
 * @author Alex Horejsi
 */
class Generator16x16 implements Generator {
	private static Generator16x16 gen = new Generator16x16();
	private Cell[][] table;
	
	private Generator16x16() {}
	
	/**
	 * Returns the single accessible instance of
	 * {@code Generator16x16}
	 * @return An instance of {@code Generator16x16}
	 */
	public static Generator16x16 getInstance() {
		return Generator16x16.gen;
	}
	
	@Override
	public Cell[][] generate(Random rng) {
		this.table = new Cell[16][16];
		
		this.fillMajorDiagonal(rng);
		this.fillRemaining(0, 4);
		
		Cell[][] table = this.table;
		this.table = null;
		
		return table;
	}
	
	private void fillMajorDiagonal(Random rng) {
		for (int i = 0 ; i < 16 ; i += 4)
			this.fillBox(i, i, rng);
	}
	
	private void fillBox(int row, int col, Random rng) {
		int end = row + 4;
		int bits = 0;
		int n;
		
		for (int i = row ; i < end ; i++) {
			for (int j = col ; j < end ; j++) {
				do {
					n = rng.nextInt(16);
				} while ((bits & (1 << n)) != 0);
				
				bits |= (1 << n);
				int digit = n + 1;
				this.table[i][j] = new ConcreteCell((char)(digit < 10 ? digit + '0' : digit - 10 + 'A'));
			}
		}
	}
	
	private boolean fillRemaining(int i, int j) {
		if (j == 16) {
			i++;
			j = 0;
		}
		
		switch (i) {
			case 0:
			case 1:
			case 2:
			case 3:
				if (j == 0)
					j = 4;
				break;
			case 4:
			case 5:
			case 6:
			case 7:
				if (j == 4)
					j = 8;
				break;
			case 8:
			case 9:
			case 10:
			case 11:
				if (j == 8)
					j = 12;
				break;
			default:
				if (j == 12) {
					i++;
					j = 0;
				}
				
				if (i == 16)
					return true;
		}
		
		for (char digit = '1' ; digit <= 'G' ; digit++) {
			if (digit == ':')
				digit = 'A';
			if (this.safe(i, j, digit)) {
				this.table[i][j] = new ConcreteCell(digit);
				if (this.fillRemaining(i, j + 1))
					return true;
				this.table[i][j].setEmptyValue();
			}
		}
		
		return false;
	}
	
	private boolean safe(int i, int j, char digit) {
		return this.safeRow(i, digit) && this.safeCol(j, digit) && this.safeBox(i - i % 4, j - j % 4, digit);
	}
	
	private boolean safeRow(int i, char digit) {
		for (int j = 0 ; j < 16 ; j++) {
			if (this.table[i][j] != null) {
				if (this.table[i][j].getValue() == digit)
					return false;
			}
		}
		
		return true;
	}
	
	private boolean safeCol(int j, char digit) {
		for (int i = 0 ; i < 16 ; i++) {
			if (this.table[i][j] != null) {
				if (this.table[i][j].getValue() == digit)
					return false;
			}
		}
		
		return true;
	}
	
	private boolean safeBox(int i, int j, char digit) {
		for (int row = 0 ; row < 4 ; row++) {
			for (int col = 0 ; col < 4 ; col++) {
				if (this.table[row + i][col + j] != null) {
					if (this.table[row + i][col + j].getValue() == digit)
						return false;
				}
			}
		}
		
		return true;
	}
}