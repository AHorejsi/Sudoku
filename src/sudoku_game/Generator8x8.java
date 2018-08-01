package sudoku_game;

import java.util.Random;

/**
 * Generates eight-by-eight Sudoku puzzles
 * @author Alex Horejsi
 */
public class Generator8x8 implements Generator {
	private static Generator gen = new Generator8x8();
	private Cell[][] table;
	
	private Generator8x8() {}
	
	/**
	 * Returns the single generator for
	 * eight-by-eight Sudoku puzzles
	 * @return A generator for eight-by-eight
	 * Sudoku puzzles
	 */
	public static Generator getInstance() {
		return Generator8x8.gen;
	}
	
	@Override
	public Cell[][] generate(Random rng) {
		this.table = new Cell[8][8];
		
		this.fillInitialCells(rng);
		this.fillRemaining(1, 4, SimpleSafeCellChecker.getInstance());
		
		Cell[][] table = this.table;
		this.table = null;
		
		return table;
	}
	
	private void fillInitialCells(Random rng) {
		int[] bits = this.fillBox(rng);
		this.fillRow(rng, bits[0]);
		this.fillColumn(rng, bits[1]);
	}
	
	private int[] fillBox(Random rng) {
		int[] bits = new int[2];
		char[] values = LegalValues8x8.getInstance().getValues();
		this.shuffle(values, rng);
		int index = 0;
		
		for (int i = 0 ; i < 2 ; i++) {
			for (int j = 0 ; j < 4 ; j++) {
				if (i == 0)
					bits[0] |= 1 << (values[index] - '0' - 1);
				if (j == 0)
					bits[1] |= 1 << (values[index] - '0' - 1);
				
				this.table[i][j] = new ConcreteCell(values[index]);
				index++;
			}
		}
		
		return bits;
	}
	
	private void fillRow(Random rng, int bits) {
		char[] values = LegalValues8x8.getInstance().getValues();
		this.shuffle(values, rng);
		int j = 4;
		
		for (int index = 0 ; index < 8 ; index++) {
			int bit = (values[index] - '0' - 1);
			
			if ((bits & (1 << bit)) == 0) {
				this.table[0][j] = new ConcreteCell(values[index]);
				j++;
			}
		}
	}
	
	private void fillColumn(Random rng, int bits) {
		char[] values = LegalValues8x8.getInstance().getValues();
		this.shuffle(values, rng);
		int i = 2;
		
		for (int index = 0 ; index < 8 ; index++) {
			int bit = (values[index] - '0' - 1);
			
			if ((bits & (1 << bit)) == 0) {
				this.table[i][0] = new ConcreteCell(values[index]);
				i++;
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
		if (j == 8) {
			i++;
			j = 1;
		}
		
		if (i == 8)
			return true;
		
		for (char digit = '1' ; digit <= '8' ; digit++) {
			if (checker.safe(this.table, digit, i, j, 2, 4)) {
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