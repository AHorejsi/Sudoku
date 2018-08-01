package sudoku_game;

import java.util.Random;

/**
 * Generates nine-by-nine
 * Sudoku puzzles
 * @author Alex Horejsi
 */
public class Generator9x9 implements Generator {
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
		this.fillRemaining(0, 3, SimpleSafeCellChecker.getInstance());
		
		Cell[][] table = this.table;
		this.table = null;
		
		return table;
	}
	
	private void fillMajorDiagonal(Random rng) {
		for (int box = 0 ; box < 9 ; box += 3)
			this.fillBox(box, box, rng);
	}
	
	private void fillBox(int i, int j, Random rng) {
		char[] values = LegalValues9x9.getInstance().getValues();
		this.shuffle(values, rng);
		
		int end = i + 3;
		int index = 0;
		
		for (int row = i ; row < end ; row++) {
			for (int col = j ; col < end ; col++) {
				this.table[row][col] = new ConcreteCell(values[index]);
				index++;
			}
		}
	}
	
	private void shuffle(char[] values, Random rng) {
		for (int i = values.length - 1 ; i > 0 ; i--) {
			int pos = rng.nextInt(i);
			char temp = values[pos];
			values[pos] = values[i];
			values[i] = temp;
		}
	}
	
	private boolean fillRemaining(int i, int j, SafeCellChecker checker) {
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
			if (checker.safe(this.table, digit, i, j, 3, 3)) {
				if (this.table[i][j] == null)
					this.table[i][j] = new ConcreteCell(digit);
				else
					this.table[i][j].setValueForSetUp(digit);
					
				if (this.fillRemaining(i, j + 1, checker))
					return true;
				this.table[i][j].setEmptyForSetUp();
			}
		}
		
		return false;
	}
}