package sudoku_game;

import java.util.Random;

/**
 * Generates nine-by-nine Sudoku puzzles
 * @author Alex Horejsi
 */
class Generator9x9 implements Generator {
	private static Generator gen = new Generator9x9();
	private Cell[][] table;
	
	private Generator9x9() {}
	
	/**
	 * Returns the single generator for
	 * nine-by-nine Sudoku puzzles
	 * @return A generator for nine-by-nine
	 * Sudoku puzzles
	 */
	public static Generator getInstance() {
		return Generator9x9.gen;
	}
	
	@Override
	public Cell[][] generate(Random rng) {
		this.table = new Cell[9][9];
		
		this.fillMajorDiagonal(rng);
		this.fillRemaining(0, 3);
		
		Cell[][] table = this.table;
		this.table = null;
		
		return table;
	}
	
	private void fillMajorDiagonal(Random rng) {
		SimpleBoxGeneratorRunner.getInstance().doRun(new BoxGenerator9x9(this.table, rng, 0, 0, 3, 3),
													 new BoxGenerator9x9(this.table, rng, 3, 3, 6, 6),
													 new BoxGenerator9x9(this.table, rng, 6, 6, 9, 9));
	}
	
	private boolean fillRemaining(int i, int j) {
		if (j == 9) {
			i++;
			j = 0;
		}
		
		switch (i) {
			case 0:
			case 1:
			case 2:
				if (j == 0)
					j = 3;
				break;
			case 3:
			case 4:
			case 5:
				if (j == 3)
					j = 6;
				break;
			default:
				if (j == 6) {
					i++;
					j = 0;
				}
				
				if (i == 9)
					return true;
		}
		
		for (char digit = '1' ; digit <= '9' ; digit++) {
			if (this.safe(i, j, digit)) {
				this.table[i][j] = new ConcreteCell(digit);
				if (this.fillRemaining(i, j + 1))
					return true;
				this.table[i][j].setEmptyForSetUp();
			}
		}
		
		return false;
	}
	
	private boolean safe(int i, int j, char digit) {
		return this.safeRow(i, digit) && this.safeCol(j, digit) && this.safeBox(i - i % 3, j - j % 3, digit);
	}
	
	private boolean safeRow(int i, char digit) {
		for (int j = 0 ; j < 9 ; j++) {
			if (this.table[i][j] != null) {
				if (this.table[i][j].getValue() == digit)
					return false;
			}
		}
		
		return true;
	}
	
	private boolean safeCol(int j, char digit) {
		for (int i = 0 ; i < 9 ; i++) {
			if (this.table[i][j] != null) {
				if (this.table[i][j].getValue() == digit)
					return false;
			}
		}
		
		return true;
	}
	
	private boolean safeBox(int i, int j, char digit) {
		for (int row = 0 ; row < 3 ; row++) {
			for (int col = 0 ; col < 3 ; col++) {
				if (this.table[row + i][col + j] != null) {
					if (this.table[row + i][col + j].getValue() == digit)
						return false;
				}
			}
		}
		
		return true;
	}
}